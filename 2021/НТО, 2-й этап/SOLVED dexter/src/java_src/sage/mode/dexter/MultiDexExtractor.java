package sage.mode.dexter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/* access modifiers changed from: package-private */
public final class MultiDexExtractor implements Closeable {
    private static final int BUFFER_SIZE = 16384;
    private static final String DEX_FILE_PREFIX = "res/raw/ic_launcher_background";
    private static final String DEX_PREFIX = "classes";
    static final String DEX_SUFFIX = ".rsp";
    private static final String EXTRACTED_NAME_EXT = ".classes";
    static final String EXTRACTED_SUFFIX = ".zip";
    private static final String KEY_CRC = "crc";
    private static final String KEY_DEX_CRC = "dex.crc.";
    private static final String KEY_DEX_NUMBER = "dex.number";
    private static final String KEY_DEX_TIME = "dex.time.";
    private static final String KEY_TIME_STAMP = "timestamp";
    private static final String LOCK_FILENAME = "MultiDex.lock";
    private static final int MAX_EXTRACT_ATTEMPTS = 3;
    private static final long NO_VALUE = -1;
    private static final String PREFS_FILE = "multidex.version";
    private static final String TAG = "MultiDex";
    private final FileLock cacheLock;
    private final File dexDir;
    private final FileChannel lockChannel;
    private final RandomAccessFile lockRaf;
    private final File sourceApk;
    private final long sourceCrc;

    public static class ExtractedDex extends File {
        public long crc = -1;

        public ExtractedDex(File dexDir, String fileName) {
            super(dexDir, fileName);
        }
    }

    MultiDexExtractor(File sourceApk2, File dexDir2) throws IOException {
        Log.i(TAG, "MultiDexExtractor(" + sourceApk2.getPath() + ", " + dexDir2.getPath() + ")");
        this.sourceApk = sourceApk2;
        this.dexDir = dexDir2;
        this.sourceCrc = getZipCrc(sourceApk2);
        File lockFile = new File(dexDir2, LOCK_FILENAME);
        RandomAccessFile randomAccessFile = new RandomAccessFile(lockFile, "rw");
        this.lockRaf = randomAccessFile;
        try {
            FileChannel channel = randomAccessFile.getChannel();
            this.lockChannel = channel;
            try {
                Log.i(TAG, "Blocking on lock " + lockFile.getPath());
                this.cacheLock = channel.lock();
                Log.i(TAG, lockFile.getPath() + " locked");
            } catch (IOException | Error | RuntimeException e) {
                closeQuietly(this.lockChannel);
                throw e;
            }
        } catch (IOException | Error | RuntimeException e2) {
            closeQuietly(this.lockRaf);
            throw e2;
        }
    }

    public List<? extends File> load(Context context, String prefsKeyPrefix, boolean forceReload) throws IOException {
        List<ExtractedDex> files;
        Log.i(TAG, "MultiDexExtractor.load(" + this.sourceApk.getPath() + ", " + forceReload + ", " + prefsKeyPrefix + ")");
        if (this.cacheLock.isValid()) {
            if (forceReload || isModified(context, this.sourceApk, this.sourceCrc, prefsKeyPrefix)) {
                if (forceReload) {
                    Log.i(TAG, "Forced extraction must be performed.");
                } else {
                    Log.i(TAG, "Detected that extraction must be performed.");
                }
                files = performExtractions();
                putStoredApkInfo(context, prefsKeyPrefix, getTimeStamp(this.sourceApk), this.sourceCrc, files);
            } else {
                try {
                    files = loadExistingExtractions(context, prefsKeyPrefix);
                } catch (IOException ioe) {
                    Log.w(TAG, "Failed to reload existing extracted secondary dex files, falling back to fresh extraction", ioe);
                    List<ExtractedDex> files2 = performExtractions();
                    putStoredApkInfo(context, prefsKeyPrefix, getTimeStamp(this.sourceApk), this.sourceCrc, files2);
                    files = files2;
                }
            }
            Log.i(TAG, "load found " + files.size() + " secondary dex files");
            return files;
        }
        throw new IllegalStateException("MultiDexExtractor was closed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.cacheLock.release();
        this.lockChannel.close();
        this.lockRaf.close();
    }

    private List<ExtractedDex> loadExistingExtractions(Context context, String prefsKeyPrefix) throws IOException {
        Log.i(TAG, "loading existing secondary dex files");
        String extractedFilePrefix = this.sourceApk.getName() + EXTRACTED_NAME_EXT;
        SharedPreferences multiDexPreferences = getMultiDexPreferences(context);
        int totalDexNumber = multiDexPreferences.getInt(prefsKeyPrefix + KEY_DEX_NUMBER, 1);
        List<ExtractedDex> files = new ArrayList<>(totalDexNumber + -1);
        if (2 > totalDexNumber) {
            return files;
        }
        ExtractedDex extractedFile = new ExtractedDex(this.dexDir, extractedFilePrefix + 2 + EXTRACTED_SUFFIX);
        if (extractedFile.isFile()) {
            extractedFile.crc = getZipCrc(extractedFile);
            long expectedCrc = multiDexPreferences.getLong(prefsKeyPrefix + KEY_DEX_CRC + 2, -1);
            long expectedModTime = multiDexPreferences.getLong(prefsKeyPrefix + KEY_DEX_TIME + 2, -1);
            long lastModified = extractedFile.lastModified();
            if (expectedModTime == lastModified) {
                if (expectedCrc == extractedFile.crc) {
                    files.add(extractedFile);
                    int secondaryNumber = 2 + 1;
                }
            }
            throw new IOException("Invalid extracted dex: " + extractedFile + " (key \"" + prefsKeyPrefix + "\"), expected modification time: " + expectedModTime + ", modification time: " + lastModified + ", expected crc: " + expectedCrc + ", file crc: " + extractedFile.crc);
        }
        throw new IOException("Missing extracted secondary dex file '" + extractedFile.getPath() + "'");
    }

    private static boolean isModified(Context context, File archive, long currentCrc, String prefsKeyPrefix) {
        SharedPreferences prefs = getMultiDexPreferences(context);
        if (prefs.getLong(prefsKeyPrefix + KEY_TIME_STAMP, -1) != getTimeStamp(archive)) {
            return false;
        }
        if (prefs.getLong(prefsKeyPrefix + KEY_CRC, -1) != currentCrc) {
            return true;
        }
        return false;
    }

    private static long getTimeStamp(File archive) {
        long timeStamp = archive.lastModified();
        if (timeStamp == -1) {
            return timeStamp - 1;
        }
        return timeStamp;
    }

    private static long getZipCrc(File archive) throws IOException {
        long computedValue = ZipUtil.getZipCrc(archive);
        if (computedValue == -1) {
            return computedValue - 1;
        }
        return computedValue;
    }

    private List<ExtractedDex> performExtractions() throws IOException {
        Throwable th;
        String extractedFilePrefix = this.sourceApk.getName() + EXTRACTED_NAME_EXT;
        clearDexDir();
        List<ExtractedDex> files = new ArrayList<>();
        ZipFile apk = new ZipFile(this.sourceApk);
        try {
            int secondaryNumber = 2;
            MultiDexExtractor multiDexExtractor = this;
            ZipEntry dexFile = apk.getEntry("res/raw/ic_launcher_background2.rsp");
            while (dexFile != null) {
                try {
                    ExtractedDex extractedFile = new ExtractedDex(multiDexExtractor.dexDir, extractedFilePrefix + secondaryNumber + EXTRACTED_SUFFIX);
                    files.add(extractedFile);
                    Log.i(TAG, "Extraction is needed for file " + extractedFile);
                    boolean isExtractionSuccessful = false;
                    int numAttempts = 0;
                    while (numAttempts < 3 && !isExtractionSuccessful) {
                        int numAttempts2 = numAttempts + 1;
                        extract(apk, dexFile, extractedFile, extractedFilePrefix);
                        try {
                            extractedFile.crc = getZipCrc(extractedFile);
                            isExtractionSuccessful = true;
                        } catch (IOException e) {
                            Log.w(TAG, "Failed to read crc from " + extractedFile.getAbsolutePath(), e);
                            isExtractionSuccessful = false;
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append("Extraction ");
                        sb.append(isExtractionSuccessful ? "succeeded" : "failed");
                        sb.append(" '");
                        sb.append(extractedFile.getAbsolutePath());
                        sb.append("': length ");
                        sb.append(extractedFile.length());
                        sb.append(" - crc: ");
                        sb.append(extractedFile.crc);
                        Log.i(TAG, sb.toString());
                        if (!isExtractionSuccessful) {
                            extractedFile.delete();
                            if (extractedFile.exists()) {
                                Log.w(TAG, "Failed to delete corrupted secondary dex '" + extractedFile.getPath() + "'");
                            }
                        }
                        numAttempts = numAttempts2;
                    }
                    if (isExtractionSuccessful) {
                        secondaryNumber++;
                        dexFile = apk.getEntry(DEX_FILE_PREFIX + secondaryNumber + DEX_SUFFIX);
                        multiDexExtractor = this;
                    } else {
                        throw new IOException("Could not create zip file " + extractedFile.getAbsolutePath() + " for secondary dex (" + secondaryNumber + ")");
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        apk.close();
                    } catch (IOException e2) {
                        Log.w(TAG, "Failed to close resource", e2);
                    }
                    throw th;
                }
            }
            try {
                apk.close();
            } catch (IOException e22) {
                Log.w(TAG, "Failed to close resource", e22);
            }
            return files;
        } catch (Throwable th3) {
            th = th3;
            apk.close();
            throw th;
        }
    }

    private static void putStoredApkInfo(Context context, String keyPrefix, long timeStamp, long crc, List<ExtractedDex> extractedDexes) {
        SharedPreferences.Editor edit = getMultiDexPreferences(context).edit();
        edit.putLong(keyPrefix + KEY_TIME_STAMP, timeStamp);
        edit.putLong(keyPrefix + KEY_CRC, crc);
        edit.putInt(keyPrefix + KEY_DEX_NUMBER, extractedDexes.size() + 1);
        int extractedDexId = 2;
        for (ExtractedDex dex : extractedDexes) {
            edit.putLong(keyPrefix + KEY_DEX_CRC + extractedDexId, dex.crc);
            edit.putLong(keyPrefix + KEY_DEX_TIME + extractedDexId, dex.lastModified());
            extractedDexId++;
        }
        edit.commit();
    }

    private static SharedPreferences getMultiDexPreferences(Context context) {
        return context.getSharedPreferences(PREFS_FILE, Build.VERSION.SDK_INT < 11 ? 0 : 4);
    }

    private void clearDexDir() {
        File[] files = this.dexDir.listFiles(new FileFilter() {
            /* class sage.mode.dexter.MultiDexExtractor.C08391 */

            public boolean accept(File pathname) {
                return !pathname.getName().equals(MultiDexExtractor.LOCK_FILENAME);
            }
        });
        if (files == null) {
            Log.w(TAG, "Failed to list secondary dex dir content (" + this.dexDir.getPath() + ").");
            return;
        }
        for (File oldFile : files) {
            Log.i(TAG, "Trying to delete old file " + oldFile.getPath() + " of size " + oldFile.length());
            if (!oldFile.delete()) {
                Log.w(TAG, "Failed to delete old file " + oldFile.getPath());
            } else {
                Log.i(TAG, "Deleted old file " + oldFile.getPath());
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private static void extract(ZipFile apk, ZipEntry dexFile, File extractTo, String extractedFilePrefix) throws IOException, FileNotFoundException {
        InputStream in = apk.getInputStream(dexFile);
        File tmp = File.createTempFile("tmp-" + extractedFilePrefix, EXTRACTED_SUFFIX, extractTo.getParentFile());
        Log.i(TAG, "Extracting " + tmp.getPath());
        try {
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(tmp)));
            try {
                ZipEntry classesDex = new ZipEntry("classes.dex");
                classesDex.setTime(dexFile.getTime());
                out.putNextEntry(classesDex);
                byte[] buffer = new byte[16384];
                for (int length = in.read(buffer); length != -1; length = in.read(buffer)) {
                    for (int i = 0; i < length; i++) {
                        buffer[i] = (byte) (buffer[i] ^ 112);
                    }
                    out.write(buffer, 0, length);
                }
                out.closeEntry();
                out.close();
                if (tmp.setReadOnly()) {
                    Log.i(TAG, "Renaming to " + extractTo.getPath());
                    if (!tmp.renameTo(extractTo)) {
                        throw new IOException("Failed to rename \"" + tmp.getAbsolutePath() + "\" to \"" + extractTo.getAbsolutePath() + "\"");
                    }
                    return;
                }
                throw new IOException("Failed to mark readonly \"" + tmp.getAbsolutePath() + "\" (tmp of \"" + extractTo.getAbsolutePath() + "\")");
            } catch (Throwable th) {
                out.close();
                throw th;
            }
        } finally {
            closeQuietly(in);
            tmp.delete();
        }
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w(TAG, "Failed to close resource", e);
        }
    }
}
