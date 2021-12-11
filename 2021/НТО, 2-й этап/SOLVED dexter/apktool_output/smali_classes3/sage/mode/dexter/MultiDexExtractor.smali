.class final Lsage/mode/dexter/MultiDexExtractor;
.super Ljava/lang/Object;
.source "MultiDexExtractor.java"

# interfaces
.implements Ljava/io/Closeable;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;
    }
.end annotation


# static fields
.field private static final BUFFER_SIZE:I = 0x4000

.field private static final DEX_FILE_PREFIX:Ljava/lang/String; = "res/raw/ic_launcher_background"

.field private static final DEX_PREFIX:Ljava/lang/String; = "classes"

.field static final DEX_SUFFIX:Ljava/lang/String; = ".rsp"

.field private static final EXTRACTED_NAME_EXT:Ljava/lang/String; = ".classes"

.field static final EXTRACTED_SUFFIX:Ljava/lang/String; = ".zip"

.field private static final KEY_CRC:Ljava/lang/String; = "crc"

.field private static final KEY_DEX_CRC:Ljava/lang/String; = "dex.crc."

.field private static final KEY_DEX_NUMBER:Ljava/lang/String; = "dex.number"

.field private static final KEY_DEX_TIME:Ljava/lang/String; = "dex.time."

.field private static final KEY_TIME_STAMP:Ljava/lang/String; = "timestamp"

.field private static final LOCK_FILENAME:Ljava/lang/String; = "MultiDex.lock"

.field private static final MAX_EXTRACT_ATTEMPTS:I = 0x3

.field private static final NO_VALUE:J = -0x1L

.field private static final PREFS_FILE:Ljava/lang/String; = "multidex.version"

.field private static final TAG:Ljava/lang/String; = "MultiDex"


# instance fields
.field private final cacheLock:Ljava/nio/channels/FileLock;

.field private final dexDir:Ljava/io/File;

.field private final lockChannel:Ljava/nio/channels/FileChannel;

.field private final lockRaf:Ljava/io/RandomAccessFile;

.field private final sourceApk:Ljava/io/File;

.field private final sourceCrc:J


# direct methods
.method constructor <init>(Ljava/io/File;Ljava/io/File;)V
    .locals 6
    .param p1, "sourceApk2"    # Ljava/io/File;
    .param p2, "dexDir2"    # Ljava/io/File;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 60
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 61
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "MultiDexExtractor("

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ")"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "MultiDex"

    invoke-static {v1, v0}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 62
    iput-object p1, p0, Lsage/mode/dexter/MultiDexExtractor;->sourceApk:Ljava/io/File;

    .line 63
    iput-object p2, p0, Lsage/mode/dexter/MultiDexExtractor;->dexDir:Ljava/io/File;

    .line 64
    invoke-static {p1}, Lsage/mode/dexter/MultiDexExtractor;->getZipCrc(Ljava/io/File;)J

    move-result-wide v2

    iput-wide v2, p0, Lsage/mode/dexter/MultiDexExtractor;->sourceCrc:J

    .line 65
    new-instance v0, Ljava/io/File;

    const-string v2, "MultiDex.lock"

    invoke-direct {v0, p2, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 66
    .local v0, "lockFile":Ljava/io/File;
    new-instance v2, Ljava/io/RandomAccessFile;

    const-string v3, "rw"

    invoke-direct {v2, v0, v3}, Ljava/io/RandomAccessFile;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 67
    .local v2, "randomAccessFile":Ljava/io/RandomAccessFile;
    iput-object v2, p0, Lsage/mode/dexter/MultiDexExtractor;->lockRaf:Ljava/io/RandomAccessFile;

    .line 69
    :try_start_0
    invoke-virtual {v2}, Ljava/io/RandomAccessFile;->getChannel()Ljava/nio/channels/FileChannel;

    move-result-object v3

    .line 70
    .local v3, "channel":Ljava/nio/channels/FileChannel;
    iput-object v3, p0, Lsage/mode/dexter/MultiDexExtractor;->lockChannel:Ljava/nio/channels/FileChannel;
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_5
    .catch Ljava/lang/Error; {:try_start_0 .. :try_end_0} :catch_4
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_3

    .line 72
    :try_start_1
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "Blocking on lock "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v1, v4}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 73
    invoke-virtual {v3}, Ljava/nio/channels/FileChannel;->lock()Ljava/nio/channels/FileLock;

    move-result-object v4

    iput-object v4, p0, Lsage/mode/dexter/MultiDexExtractor;->cacheLock:Ljava/nio/channels/FileLock;

    .line 74
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, " locked"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v1, v4}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_2
    .catch Ljava/lang/Error; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/lang/RuntimeException; {:try_start_1 .. :try_end_1} :catch_0

    .line 78
    nop

    .line 82
    .end local v3    # "channel":Ljava/nio/channels/FileChannel;
    nop

    .line 83
    return-void

    .line 75
    .restart local v3    # "channel":Ljava/nio/channels/FileChannel;
    :catch_0
    move-exception v1

    goto :goto_0

    :catch_1
    move-exception v1

    goto :goto_0

    :catch_2
    move-exception v1

    .line 76
    .local v1, "e":Ljava/lang/Throwable;
    :goto_0
    :try_start_2
    iget-object v4, p0, Lsage/mode/dexter/MultiDexExtractor;->lockChannel:Ljava/nio/channels/FileChannel;

    invoke-static {v4}, Lsage/mode/dexter/MultiDexExtractor;->closeQuietly(Ljava/io/Closeable;)V

    .line 77
    nop

    .end local v0    # "lockFile":Ljava/io/File;
    .end local v2    # "randomAccessFile":Ljava/io/RandomAccessFile;
    .end local p1    # "sourceApk2":Ljava/io/File;
    .end local p2    # "dexDir2":Ljava/io/File;
    throw v1
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_5
    .catch Ljava/lang/Error; {:try_start_2 .. :try_end_2} :catch_4
    .catch Ljava/lang/RuntimeException; {:try_start_2 .. :try_end_2} :catch_3

    .line 79
    .end local v1    # "e":Ljava/lang/Throwable;
    .end local v3    # "channel":Ljava/nio/channels/FileChannel;
    .restart local v0    # "lockFile":Ljava/io/File;
    .restart local v2    # "randomAccessFile":Ljava/io/RandomAccessFile;
    .restart local p1    # "sourceApk2":Ljava/io/File;
    .restart local p2    # "dexDir2":Ljava/io/File;
    :catch_3
    move-exception v1

    goto :goto_1

    :catch_4
    move-exception v1

    goto :goto_1

    :catch_5
    move-exception v1

    .line 80
    .local v1, "e2":Ljava/lang/Throwable;
    :goto_1
    iget-object v3, p0, Lsage/mode/dexter/MultiDexExtractor;->lockRaf:Ljava/io/RandomAccessFile;

    invoke-static {v3}, Lsage/mode/dexter/MultiDexExtractor;->closeQuietly(Ljava/io/Closeable;)V

    .line 81
    throw v1
.end method

.method private clearDexDir()V
    .locals 8

    .line 260
    iget-object v0, p0, Lsage/mode/dexter/MultiDexExtractor;->dexDir:Ljava/io/File;

    new-instance v1, Lsage/mode/dexter/MultiDexExtractor$1;

    invoke-direct {v1, p0}, Lsage/mode/dexter/MultiDexExtractor$1;-><init>(Lsage/mode/dexter/MultiDexExtractor;)V

    invoke-virtual {v0, v1}, Ljava/io/File;->listFiles(Ljava/io/FileFilter;)[Ljava/io/File;

    move-result-object v0

    .line 267
    .local v0, "files":[Ljava/io/File;
    const-string v1, "MultiDex"

    if-nez v0, :cond_0

    .line 268
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Failed to list secondary dex dir content ("

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v3, p0, Lsage/mode/dexter/MultiDexExtractor;->dexDir:Ljava/io/File;

    invoke-virtual {v3}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, ")."

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 269
    return-void

    .line 271
    :cond_0
    array-length v2, v0

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_2

    aget-object v4, v0, v3

    .line 272
    .local v4, "oldFile":Ljava/io/File;
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "Trying to delete old file "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, " of size "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/io/File;->length()J

    move-result-wide v6

    invoke-virtual {v5, v6, v7}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v1, v5}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 273
    invoke-virtual {v4}, Ljava/io/File;->delete()Z

    move-result v5

    if-nez v5, :cond_1

    .line 274
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "Failed to delete old file "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v1, v5}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_1

    .line 276
    :cond_1
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "Deleted old file "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v1, v5}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 271
    .end local v4    # "oldFile":Ljava/io/File;
    :goto_1
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 279
    :cond_2
    return-void
.end method

.method private static closeQuietly(Ljava/io/Closeable;)V
    .locals 3
    .param p0, "closeable"    # Ljava/io/Closeable;

    .line 322
    :try_start_0
    invoke-interface {p0}, Ljava/io/Closeable;->close()V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 325
    goto :goto_0

    .line 323
    :catch_0
    move-exception v0

    .line 324
    .local v0, "e":Ljava/io/IOException;
    const-string v1, "MultiDex"

    const-string v2, "Failed to close resource"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 326
    .end local v0    # "e":Ljava/io/IOException;
    :goto_0
    return-void
.end method

.method private static extract(Ljava/util/zip/ZipFile;Ljava/util/zip/ZipEntry;Ljava/io/File;Ljava/lang/String;)V
    .locals 9
    .param p0, "apk"    # Ljava/util/zip/ZipFile;
    .param p1, "dexFile"    # Ljava/util/zip/ZipEntry;
    .param p2, "extractTo"    # Ljava/io/File;
    .param p3, "extractedFilePrefix"    # Ljava/lang/String;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;,
            Ljava/io/FileNotFoundException;
        }
    .end annotation

    .line 283
    invoke-virtual {p0, p1}, Ljava/util/zip/ZipFile;->getInputStream(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;

    move-result-object v0

    .line 284
    .local v0, "in":Ljava/io/InputStream;
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "tmp-"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p2}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v2

    const-string v3, ".zip"

    invoke-static {v1, v3, v2}, Ljava/io/File;->createTempFile(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)Ljava/io/File;

    move-result-object v1

    .line 285
    .local v1, "tmp":Ljava/io/File;
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Extracting "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    const-string v3, "MultiDex"

    invoke-static {v3, v2}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 287
    :try_start_0
    new-instance v2, Ljava/util/zip/ZipOutputStream;

    new-instance v4, Ljava/io/BufferedOutputStream;

    new-instance v5, Ljava/io/FileOutputStream;

    invoke-direct {v5, v1}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    invoke-direct {v4, v5}, Ljava/io/BufferedOutputStream;-><init>(Ljava/io/OutputStream;)V

    invoke-direct {v2, v4}, Ljava/util/zip/ZipOutputStream;-><init>(Ljava/io/OutputStream;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 289
    .local v2, "out":Ljava/util/zip/ZipOutputStream;
    :try_start_1
    new-instance v4, Ljava/util/zip/ZipEntry;

    const-string v5, "classes.dex"

    invoke-direct {v4, v5}, Ljava/util/zip/ZipEntry;-><init>(Ljava/lang/String;)V

    .line 290
    .local v4, "classesDex":Ljava/util/zip/ZipEntry;
    invoke-virtual {p1}, Ljava/util/zip/ZipEntry;->getTime()J

    move-result-wide v5

    invoke-virtual {v4, v5, v6}, Ljava/util/zip/ZipEntry;->setTime(J)V

    .line 291
    invoke-virtual {v2, v4}, Ljava/util/zip/ZipOutputStream;->putNextEntry(Ljava/util/zip/ZipEntry;)V

    .line 292
    const/16 v5, 0x4000

    new-array v5, v5, [B

    .line 293
    .local v5, "buffer":[B
    invoke-virtual {v0, v5}, Ljava/io/InputStream;->read([B)I

    move-result v6

    .local v6, "length":I
    :goto_0
    const/4 v7, -0x1

    if-eq v6, v7, :cond_1

    .line 294
    const/4 v7, 0x0

    .local v7, "i":I
    :goto_1
    if-ge v7, v6, :cond_0

    .line 296
    aget-byte v8, v5, v7

    xor-int/lit8 v8, v8, 0x70

    int-to-byte v8, v8

    aput-byte v8, v5, v7

    .line 294
    add-int/lit8 v7, v7, 0x1

    goto :goto_1

    .line 298
    .end local v7    # "i":I
    :cond_0
    const/4 v7, 0x0

    invoke-virtual {v2, v5, v7, v6}, Ljava/util/zip/ZipOutputStream;->write([BII)V

    .line 293
    invoke-virtual {v0, v5}, Ljava/io/InputStream;->read([B)I

    move-result v7

    move v6, v7

    goto :goto_0

    .line 300
    .end local v6    # "length":I
    :cond_1
    invoke-virtual {v2}, Ljava/util/zip/ZipOutputStream;->closeEntry()V

    .line 301
    invoke-virtual {v2}, Ljava/util/zip/ZipOutputStream;->close()V

    .line 302
    invoke-virtual {v1}, Ljava/io/File;->setReadOnly()Z

    move-result v6

    if-eqz v6, :cond_3

    .line 303
    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "Renaming to "

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-static {v3, v6}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 304
    invoke-virtual {v1, p2}, Ljava/io/File;->renameTo(Ljava/io/File;)Z

    move-result v3
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-eqz v3, :cond_2

    .line 315
    invoke-static {v0}, Lsage/mode/dexter/MultiDexExtractor;->closeQuietly(Ljava/io/Closeable;)V

    .line 316
    invoke-virtual {v1}, Ljava/io/File;->delete()Z

    .line 307
    return-void

    .line 305
    :cond_2
    :try_start_2
    new-instance v3, Ljava/io/IOException;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "Failed to rename \""

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v7, "\" to \""

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v7, "\""

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-direct {v3, v6}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    .end local v0    # "in":Ljava/io/InputStream;
    .end local v1    # "tmp":Ljava/io/File;
    .end local v2    # "out":Ljava/util/zip/ZipOutputStream;
    .end local p0    # "apk":Ljava/util/zip/ZipFile;
    .end local p1    # "dexFile":Ljava/util/zip/ZipEntry;
    .end local p2    # "extractTo":Ljava/io/File;
    .end local p3    # "extractedFilePrefix":Ljava/lang/String;
    throw v3

    .line 309
    .restart local v0    # "in":Ljava/io/InputStream;
    .restart local v1    # "tmp":Ljava/io/File;
    .restart local v2    # "out":Ljava/util/zip/ZipOutputStream;
    .restart local p0    # "apk":Ljava/util/zip/ZipFile;
    .restart local p1    # "dexFile":Ljava/util/zip/ZipEntry;
    .restart local p2    # "extractTo":Ljava/io/File;
    .restart local p3    # "extractedFilePrefix":Ljava/lang/String;
    :cond_3
    new-instance v3, Ljava/io/IOException;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "Failed to mark readonly \""

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v7, "\" (tmp of \""

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v7, "\")"

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-direct {v3, v6}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    .end local v0    # "in":Ljava/io/InputStream;
    .end local v1    # "tmp":Ljava/io/File;
    .end local v2    # "out":Ljava/util/zip/ZipOutputStream;
    .end local p0    # "apk":Ljava/util/zip/ZipFile;
    .end local p1    # "dexFile":Ljava/util/zip/ZipEntry;
    .end local p2    # "extractTo":Ljava/io/File;
    .end local p3    # "extractedFilePrefix":Ljava/lang/String;
    throw v3
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 310
    .end local v4    # "classesDex":Ljava/util/zip/ZipEntry;
    .end local v5    # "buffer":[B
    .restart local v0    # "in":Ljava/io/InputStream;
    .restart local v1    # "tmp":Ljava/io/File;
    .restart local v2    # "out":Ljava/util/zip/ZipOutputStream;
    .restart local p0    # "apk":Ljava/util/zip/ZipFile;
    .restart local p1    # "dexFile":Ljava/util/zip/ZipEntry;
    .restart local p2    # "extractTo":Ljava/io/File;
    .restart local p3    # "extractedFilePrefix":Ljava/lang/String;
    :catchall_0
    move-exception v3

    .line 311
    .local v3, "th":Ljava/lang/Throwable;
    :try_start_3
    invoke-virtual {v2}, Ljava/util/zip/ZipOutputStream;->close()V

    .line 312
    nop

    .end local v0    # "in":Ljava/io/InputStream;
    .end local v1    # "tmp":Ljava/io/File;
    .end local p0    # "apk":Ljava/util/zip/ZipFile;
    .end local p1    # "dexFile":Ljava/util/zip/ZipEntry;
    .end local p2    # "extractTo":Ljava/io/File;
    .end local p3    # "extractedFilePrefix":Ljava/lang/String;
    throw v3
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    .line 315
    .end local v2    # "out":Ljava/util/zip/ZipOutputStream;
    .end local v3    # "th":Ljava/lang/Throwable;
    .restart local v0    # "in":Ljava/io/InputStream;
    .restart local v1    # "tmp":Ljava/io/File;
    .restart local p0    # "apk":Ljava/util/zip/ZipFile;
    .restart local p1    # "dexFile":Ljava/util/zip/ZipEntry;
    .restart local p2    # "extractTo":Ljava/io/File;
    .restart local p3    # "extractedFilePrefix":Ljava/lang/String;
    :catchall_1
    move-exception v2

    invoke-static {v0}, Lsage/mode/dexter/MultiDexExtractor;->closeQuietly(Ljava/io/Closeable;)V

    .line 316
    invoke-virtual {v1}, Ljava/io/File;->delete()Z

    .line 317
    throw v2
.end method

.method private static getMultiDexPreferences(Landroid/content/Context;)Landroid/content/SharedPreferences;
    .locals 2
    .param p0, "context"    # Landroid/content/Context;

    .line 256
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0xb

    if-ge v0, v1, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    :cond_0
    const/4 v0, 0x4

    :goto_0
    const-string v1, "multidex.version"

    invoke-virtual {p0, v1, v0}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    return-object v0
.end method

.method private static getTimeStamp(Ljava/io/File;)J
    .locals 5
    .param p0, "archive"    # Ljava/io/File;

    .line 162
    invoke-virtual {p0}, Ljava/io/File;->lastModified()J

    move-result-wide v0

    .line 163
    .local v0, "timeStamp":J
    const-wide/16 v2, -0x1

    cmp-long v4, v0, v2

    if-nez v4, :cond_0

    .line 164
    const-wide/16 v2, 0x1

    sub-long v2, v0, v2

    return-wide v2

    .line 166
    :cond_0
    return-wide v0
.end method

.method private static getZipCrc(Ljava/io/File;)J
    .locals 5
    .param p0, "archive"    # Ljava/io/File;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 170
    invoke-static {p0}, Lsage/mode/dexter/ZipUtil;->getZipCrc(Ljava/io/File;)J

    move-result-wide v0

    .line 171
    .local v0, "computedValue":J
    const-wide/16 v2, -0x1

    cmp-long v4, v0, v2

    if-nez v4, :cond_0

    .line 172
    const-wide/16 v2, 0x1

    sub-long v2, v0, v2

    return-wide v2

    .line 174
    :cond_0
    return-wide v0
.end method

.method private static isModified(Landroid/content/Context;Ljava/io/File;JLjava/lang/String;)Z
    .locals 9
    .param p0, "context"    # Landroid/content/Context;
    .param p1, "archive"    # Ljava/io/File;
    .param p2, "currentCrc"    # J
    .param p4, "prefsKeyPrefix"    # Ljava/lang/String;

    .line 151
    invoke-static {p0}, Lsage/mode/dexter/MultiDexExtractor;->getMultiDexPreferences(Landroid/content/Context;)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 152
    .local v0, "prefs":Landroid/content/SharedPreferences;
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "timestamp"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    const-wide/16 v2, -0x1

    invoke-interface {v0, v1, v2, v3}, Landroid/content/SharedPreferences;->getLong(Ljava/lang/String;J)J

    move-result-wide v4

    invoke-static {p1}, Lsage/mode/dexter/MultiDexExtractor;->getTimeStamp(Ljava/io/File;)J

    move-result-wide v6

    const/4 v1, 0x0

    cmp-long v8, v4, v6

    if-nez v8, :cond_1

    .line 153
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    .line 154
    .local v4, "sb":Ljava/lang/StringBuilder;
    invoke-virtual {v4, p4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 155
    const-string v5, "crc"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 156
    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-interface {v0, v5, v2, v3}, Landroid/content/SharedPreferences;->getLong(Ljava/lang/String;J)J

    move-result-wide v2

    cmp-long v5, v2, p2

    if-eqz v5, :cond_0

    const/4 v1, 0x1

    :cond_0
    return v1

    .line 158
    .end local v4    # "sb":Ljava/lang/StringBuilder;
    :cond_1
    return v1
.end method

.method private loadExistingExtractions(Landroid/content/Context;Ljava/lang/String;)Ljava/util/List;
    .locals 17
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "prefsKeyPrefix"    # Ljava/lang/String;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Ljava/lang/String;",
            ")",
            "Ljava/util/List<",
            "Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;",
            ">;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 122
    move-object/from16 v0, p0

    move-object/from16 v1, p2

    const-string v2, "MultiDex"

    const-string v3, "loading existing secondary dex files"

    invoke-static {v2, v3}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 123
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v3, v0, Lsage/mode/dexter/MultiDexExtractor;->sourceApk:Ljava/io/File;

    invoke-virtual {v3}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, ".classes"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    .line 124
    .local v2, "extractedFilePrefix":Ljava/lang/String;
    invoke-static/range {p1 .. p1}, Lsage/mode/dexter/MultiDexExtractor;->getMultiDexPreferences(Landroid/content/Context;)Landroid/content/SharedPreferences;

    move-result-object v3

    .line 125
    .local v3, "multiDexPreferences":Landroid/content/SharedPreferences;
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, "dex.number"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    const/4 v5, 0x1

    invoke-interface {v3, v4, v5}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result v4

    .line 126
    .local v4, "totalDexNumber":I
    new-instance v5, Ljava/util/ArrayList;

    add-int/lit8 v6, v4, -0x1

    invoke-direct {v5, v6}, Ljava/util/ArrayList;-><init>(I)V

    .line 127
    .local v5, "files":Ljava/util/List;, "Ljava/util/List<Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;>;"
    const/4 v6, 0x2

    .line 128
    .local v6, "secondaryNumber":I
    if-gt v6, v4, :cond_3

    .line 129
    new-instance v7, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;

    iget-object v8, v0, Lsage/mode/dexter/MultiDexExtractor;->dexDir:Ljava/io/File;

    new-instance v9, Ljava/lang/StringBuilder;

    invoke-direct {v9}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v9, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v10, ".zip"

    invoke-virtual {v9, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v9

    invoke-direct {v7, v8, v9}, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 130
    .local v7, "extractedFile":Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;
    invoke-virtual {v7}, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;->isFile()Z

    move-result v8

    if-eqz v8, :cond_2

    .line 131
    invoke-static {v7}, Lsage/mode/dexter/MultiDexExtractor;->getZipCrc(Ljava/io/File;)J

    move-result-wide v8

    iput-wide v8, v7, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;->crc:J

    .line 132
    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v8, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v9, "dex.crc."

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    const-wide/16 v9, -0x1

    invoke-interface {v3, v8, v9, v10}, Landroid/content/SharedPreferences;->getLong(Ljava/lang/String;J)J

    move-result-wide v11

    .line 133
    .local v11, "expectedCrc":J
    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v8, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v13, "dex.time."

    invoke-virtual {v8, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-interface {v3, v8, v9, v10}, Landroid/content/SharedPreferences;->getLong(Ljava/lang/String;J)J

    move-result-wide v8

    .line 134
    .local v8, "expectedModTime":J
    invoke-virtual {v7}, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;->lastModified()J

    move-result-wide v13

    .line 135
    .local v13, "lastModified":J
    cmp-long v10, v8, v13

    if-nez v10, :cond_0

    .line 136
    move-object v10, v2

    move-object v15, v3

    .end local v2    # "extractedFilePrefix":Ljava/lang/String;
    .end local v3    # "multiDexPreferences":Landroid/content/SharedPreferences;
    .local v10, "extractedFilePrefix":Ljava/lang/String;
    .local v15, "multiDexPreferences":Landroid/content/SharedPreferences;
    iget-wide v2, v7, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;->crc:J

    cmp-long v16, v11, v2

    if-nez v16, :cond_1

    .line 137
    invoke-interface {v5, v7}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 138
    add-int/lit8 v6, v6, 0x1

    .line 139
    nop

    .line 140
    goto :goto_0

    .line 135
    .end local v10    # "extractedFilePrefix":Ljava/lang/String;
    .end local v15    # "multiDexPreferences":Landroid/content/SharedPreferences;
    .restart local v2    # "extractedFilePrefix":Ljava/lang/String;
    .restart local v3    # "multiDexPreferences":Landroid/content/SharedPreferences;
    :cond_0
    move-object v10, v2

    move-object v15, v3

    .line 143
    .end local v2    # "extractedFilePrefix":Ljava/lang/String;
    .end local v3    # "multiDexPreferences":Landroid/content/SharedPreferences;
    .restart local v10    # "extractedFilePrefix":Ljava/lang/String;
    .restart local v15    # "multiDexPreferences":Landroid/content/SharedPreferences;
    :cond_1
    :goto_0
    new-instance v2, Ljava/io/IOException;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v0, "Invalid extracted dex: "

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, " (key \""

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, "\"), expected modification time: "

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v8, v9}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ", modification time: "

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v13, v14}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ", expected crc: "

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v11, v12}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ", file crc: "

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v0, v7, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;->crc:J

    invoke-virtual {v3, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v2, v0}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v2

    .line 145
    .end local v8    # "expectedModTime":J
    .end local v10    # "extractedFilePrefix":Ljava/lang/String;
    .end local v11    # "expectedCrc":J
    .end local v13    # "lastModified":J
    .end local v15    # "multiDexPreferences":Landroid/content/SharedPreferences;
    .restart local v2    # "extractedFilePrefix":Ljava/lang/String;
    .restart local v3    # "multiDexPreferences":Landroid/content/SharedPreferences;
    :cond_2
    move-object v10, v2

    .end local v2    # "extractedFilePrefix":Ljava/lang/String;
    .restart local v10    # "extractedFilePrefix":Ljava/lang/String;
    new-instance v0, Ljava/io/IOException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Missing extracted secondary dex file \'"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;->getPath()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "\'"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 147
    .end local v7    # "extractedFile":Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;
    .end local v10    # "extractedFilePrefix":Ljava/lang/String;
    .restart local v2    # "extractedFilePrefix":Ljava/lang/String;
    :cond_3
    return-object v5
.end method

.method private performExtractions()Ljava/util/List;
    .locals 16
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;",
            ">;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 178
    const-string v1, "Failed to close resource"

    const-string v2, "MultiDex"

    move-object/from16 v3, p0

    .line 179
    .local v3, "multiDexExtractor":Lsage/mode/dexter/MultiDexExtractor;
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v4, v3, Lsage/mode/dexter/MultiDexExtractor;->sourceApk:Ljava/io/File;

    invoke-virtual {v4}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, ".classes"

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    .line 180
    .local v4, "extractedFilePrefix":Ljava/lang/String;
    invoke-direct/range {p0 .. p0}, Lsage/mode/dexter/MultiDexExtractor;->clearDexDir()V

    .line 181
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    move-object v5, v0

    .line 182
    .local v5, "files":Ljava/util/List;, "Ljava/util/List<Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;>;"
    new-instance v0, Ljava/util/zip/ZipFile;

    iget-object v6, v3, Lsage/mode/dexter/MultiDexExtractor;->sourceApk:Ljava/io/File;

    invoke-direct {v0, v6}, Ljava/util/zip/ZipFile;-><init>(Ljava/io/File;)V

    move-object v6, v0

    .line 184
    .local v6, "apk":Ljava/util/zip/ZipFile;
    :try_start_0
    const-string v0, "res/raw/ic_launcher_background2.rsp"

    invoke-virtual {v6, v0}, Ljava/util/zip/ZipFile;->getEntry(Ljava/lang/String;)Ljava/util/zip/ZipEntry;

    move-result-object v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 185
    .local v0, "dexFile":Ljava/util/zip/ZipEntry;
    const/4 v7, 0x2

    move v8, v7

    move-object v7, v3

    move-object v3, v0

    .line 186
    .end local v0    # "dexFile":Ljava/util/zip/ZipEntry;
    .local v3, "dexFile":Ljava/util/zip/ZipEntry;
    .local v7, "multiDexExtractor":Lsage/mode/dexter/MultiDexExtractor;
    .local v8, "secondaryNumber":I
    :goto_0
    if-eqz v3, :cond_4

    .line 187
    :try_start_1
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v8}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v9, ".zip"

    invoke-virtual {v0, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    move-object v9, v0

    .line 188
    .local v9, "fileName":Ljava/lang/String;
    new-instance v0, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;

    iget-object v10, v7, Lsage/mode/dexter/MultiDexExtractor;->dexDir:Ljava/io/File;

    invoke-direct {v0, v10, v9}, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;-><init>(Ljava/io/File;Ljava/lang/String;)V

    move-object v10, v0

    .line 189
    .local v10, "extractedFile":Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;
    invoke-interface {v5, v10}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 190
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v11, "Extraction is needed for file "

    invoke-virtual {v0, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v2, v0}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 191
    const/4 v0, 0x0

    .line 192
    .local v0, "numAttempts":I
    const/4 v11, 0x0

    move v12, v11

    move v11, v0

    .line 193
    .end local v0    # "numAttempts":I
    .local v11, "numAttempts":I
    .local v12, "isExtractionSuccessful":Z
    :goto_1
    const/4 v0, 0x3

    if-ge v11, v0, :cond_2

    if-nez v12, :cond_2

    .line 194
    add-int/lit8 v13, v11, 0x1

    .line 195
    .local v13, "numAttempts2":I
    invoke-static {v6, v3, v10, v4}, Lsage/mode/dexter/MultiDexExtractor;->extract(Ljava/util/zip/ZipFile;Ljava/util/zip/ZipEntry;Ljava/io/File;Ljava/lang/String;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 197
    :try_start_2
    invoke-static {v10}, Lsage/mode/dexter/MultiDexExtractor;->getZipCrc(Ljava/io/File;)J

    move-result-wide v14

    iput-wide v14, v10, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;->crc:J
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 198
    const/4 v0, 0x1

    .line 202
    .end local v12    # "isExtractionSuccessful":Z
    .local v0, "isExtractionSuccessful":Z
    move v12, v0

    goto :goto_2

    .line 199
    .end local v0    # "isExtractionSuccessful":Z
    .restart local v12    # "isExtractionSuccessful":Z
    :catch_0
    move-exception v0

    .line 200
    .local v0, "e":Ljava/io/IOException;
    :try_start_3
    new-instance v14, Ljava/lang/StringBuilder;

    invoke-direct {v14}, Ljava/lang/StringBuilder;-><init>()V

    const-string v15, "Failed to read crc from "

    invoke-virtual {v14, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v10}, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;->getAbsolutePath()Ljava/lang/String;

    move-result-object v15

    invoke-virtual {v14, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v14}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v14

    invoke-static {v2, v14, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 201
    const/4 v12, 0x0

    .line 203
    .end local v0    # "e":Ljava/io/IOException;
    :goto_2
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    .line 204
    .local v0, "sb":Ljava/lang/StringBuilder;
    const-string v14, "Extraction "

    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 205
    if-eqz v12, :cond_0

    const-string v14, "succeeded"

    goto :goto_3

    :cond_0
    const-string v14, "failed"

    :goto_3
    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 206
    const-string v14, " \'"

    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 207
    invoke-virtual {v10}, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;->getAbsolutePath()Ljava/lang/String;

    move-result-object v14

    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 208
    const-string v14, "\': length "

    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 209
    invoke-virtual {v10}, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;->length()J

    move-result-wide v14

    invoke-virtual {v0, v14, v15}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    .line 210
    const-string v14, " - crc: "

    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 211
    iget-wide v14, v10, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;->crc:J

    invoke-virtual {v0, v14, v15}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    .line 212
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v14

    invoke-static {v2, v14}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 213
    if-nez v12, :cond_1

    .line 214
    invoke-virtual {v10}, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;->delete()Z

    .line 215
    invoke-virtual {v10}, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;->exists()Z

    move-result v14

    if-eqz v14, :cond_1

    .line 216
    new-instance v14, Ljava/lang/StringBuilder;

    invoke-direct {v14}, Ljava/lang/StringBuilder;-><init>()V

    const-string v15, "Failed to delete corrupted secondary dex \'"

    invoke-virtual {v14, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v10}, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;->getPath()Ljava/lang/String;

    move-result-object v15

    invoke-virtual {v14, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, "\'"

    invoke-virtual {v14, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v14}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v14

    invoke-static {v2, v14}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 219
    :cond_1
    nop

    .line 220
    move v11, v13

    .line 221
    nop

    .line 222
    .end local v0    # "sb":Ljava/lang/StringBuilder;
    .end local v13    # "numAttempts2":I
    goto/16 :goto_1

    .line 223
    :cond_2
    if-eqz v12, :cond_3

    .line 224
    add-int/lit8 v8, v8, 0x1

    .line 225
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v13, "res/raw/ic_launcher_background"

    invoke-virtual {v0, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v8}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v13, ".rsp"

    invoke-virtual {v0, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v6, v0}, Ljava/util/zip/ZipFile;->getEntry(Ljava/lang/String;)Ljava/util/zip/ZipEntry;

    move-result-object v0

    move-object v3, v0

    .line 226
    move-object/from16 v7, p0

    .line 230
    .end local v9    # "fileName":Ljava/lang/String;
    .end local v10    # "extractedFile":Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;
    .end local v11    # "numAttempts":I
    .end local v12    # "isExtractionSuccessful":Z
    goto/16 :goto_0

    .line 228
    .restart local v9    # "fileName":Ljava/lang/String;
    .restart local v10    # "extractedFile":Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;
    .restart local v11    # "numAttempts":I
    .restart local v12    # "isExtractionSuccessful":Z
    :cond_3
    new-instance v0, Ljava/io/IOException;

    new-instance v13, Ljava/lang/StringBuilder;

    invoke-direct {v13}, Ljava/lang/StringBuilder;-><init>()V

    const-string v14, "Could not create zip file "

    invoke-virtual {v13, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v10}, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;->getAbsolutePath()Ljava/lang/String;

    move-result-object v14

    invoke-virtual {v13, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v14, " for secondary dex ("

    invoke-virtual {v13, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v13, v8}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v14, ")"

    invoke-virtual {v13, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v13}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v13

    invoke-direct {v0, v13}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    .end local v4    # "extractedFilePrefix":Ljava/lang/String;
    .end local v5    # "files":Ljava/util/List;, "Ljava/util/List<Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;>;"
    .end local v6    # "apk":Ljava/util/zip/ZipFile;
    .end local v7    # "multiDexExtractor":Lsage/mode/dexter/MultiDexExtractor;
    throw v0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 233
    .end local v3    # "dexFile":Ljava/util/zip/ZipEntry;
    .end local v8    # "secondaryNumber":I
    .end local v9    # "fileName":Ljava/lang/String;
    .end local v10    # "extractedFile":Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;
    .end local v11    # "numAttempts":I
    .end local v12    # "isExtractionSuccessful":Z
    .restart local v4    # "extractedFilePrefix":Ljava/lang/String;
    .restart local v5    # "files":Ljava/util/List;, "Ljava/util/List<Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;>;"
    .restart local v6    # "apk":Ljava/util/zip/ZipFile;
    .restart local v7    # "multiDexExtractor":Lsage/mode/dexter/MultiDexExtractor;
    :catchall_0
    move-exception v0

    move-object v3, v7

    move-object v7, v0

    goto :goto_5

    .line 231
    .restart local v3    # "dexFile":Ljava/util/zip/ZipEntry;
    .restart local v8    # "secondaryNumber":I
    :cond_4
    nop

    .line 234
    :try_start_4
    invoke-virtual {v6}, Ljava/util/zip/ZipFile;->close()V
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_1

    .line 237
    goto :goto_4

    .line 235
    :catch_1
    move-exception v0

    move-object v9, v0

    move-object v0, v9

    .line 236
    .local v0, "e2":Ljava/io/IOException;
    invoke-static {v2, v1, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 231
    .end local v0    # "e2":Ljava/io/IOException;
    :goto_4
    return-object v5

    .line 233
    .end local v7    # "multiDexExtractor":Lsage/mode/dexter/MultiDexExtractor;
    .end local v8    # "secondaryNumber":I
    .local v3, "multiDexExtractor":Lsage/mode/dexter/MultiDexExtractor;
    :catchall_1
    move-exception v0

    move-object v7, v0

    .line 234
    :goto_5
    :try_start_5
    invoke-virtual {v6}, Ljava/util/zip/ZipFile;->close()V
    :try_end_5
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_5} :catch_2

    .line 237
    goto :goto_6

    .line 235
    :catch_2
    move-exception v0

    move-object v8, v0

    move-object v0, v8

    .line 236
    .restart local v0    # "e2":Ljava/io/IOException;
    invoke-static {v2, v1, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 238
    .end local v0    # "e2":Ljava/io/IOException;
    :goto_6
    throw v7
.end method

.method private static putStoredApkInfo(Landroid/content/Context;Ljava/lang/String;JJLjava/util/List;)V
    .locals 7
    .param p0, "context"    # Landroid/content/Context;
    .param p1, "keyPrefix"    # Ljava/lang/String;
    .param p2, "timeStamp"    # J
    .param p4, "crc"    # J
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Ljava/lang/String;",
            "JJ",
            "Ljava/util/List<",
            "Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;",
            ">;)V"
        }
    .end annotation

    .line 242
    .local p6, "extractedDexes":Ljava/util/List;, "Ljava/util/List<Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;>;"
    invoke-static {p0}, Lsage/mode/dexter/MultiDexExtractor;->getMultiDexPreferences(Landroid/content/Context;)Landroid/content/SharedPreferences;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 243
    .local v0, "edit":Landroid/content/SharedPreferences$Editor;
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "timestamp"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1, p2, p3}, Landroid/content/SharedPreferences$Editor;->putLong(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;

    .line 244
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "crc"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1, p4, p5}, Landroid/content/SharedPreferences$Editor;->putLong(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;

    .line 245
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "dex.number"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-interface {p6}, Ljava/util/List;->size()I

    move-result v2

    add-int/lit8 v2, v2, 0x1

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    .line 246
    const/4 v1, 0x2

    .line 247
    .local v1, "extractedDexId":I
    invoke-interface {p6}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_0

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;

    .line 248
    .local v3, "dex":Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, "dex.crc."

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    iget-wide v5, v3, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;->crc:J

    invoke-interface {v0, v4, v5, v6}, Landroid/content/SharedPreferences$Editor;->putLong(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;

    .line 249
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, "dex.time."

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3}, Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;->lastModified()J

    move-result-wide v5

    invoke-interface {v0, v4, v5, v6}, Landroid/content/SharedPreferences$Editor;->putLong(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;

    .line 250
    nop

    .end local v3    # "dex":Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;
    add-int/lit8 v1, v1, 0x1

    .line 251
    goto :goto_0

    .line 252
    :cond_0
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->commit()Z

    .line 253
    return-void
.end method


# virtual methods
.method public close()V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 116
    iget-object v0, p0, Lsage/mode/dexter/MultiDexExtractor;->cacheLock:Ljava/nio/channels/FileLock;

    invoke-virtual {v0}, Ljava/nio/channels/FileLock;->release()V

    .line 117
    iget-object v0, p0, Lsage/mode/dexter/MultiDexExtractor;->lockChannel:Ljava/nio/channels/FileChannel;

    invoke-virtual {v0}, Ljava/nio/channels/FileChannel;->close()V

    .line 118
    iget-object v0, p0, Lsage/mode/dexter/MultiDexExtractor;->lockRaf:Ljava/io/RandomAccessFile;

    invoke-virtual {v0}, Ljava/io/RandomAccessFile;->close()V

    .line 119
    return-void
.end method

.method public load(Landroid/content/Context;Ljava/lang/String;Z)Ljava/util/List;
    .locals 10
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "prefsKeyPrefix"    # Ljava/lang/String;
    .param p3, "forceReload"    # Z
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Ljava/lang/String;",
            "Z)",
            "Ljava/util/List<",
            "+",
            "Ljava/io/File;",
            ">;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 88
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "MultiDexExtractor.load("

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lsage/mode/dexter/MultiDexExtractor;->sourceApk:Ljava/io/File;

    invoke-virtual {v1}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p3}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ")"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "MultiDex"

    invoke-static {v1, v0}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 89
    iget-object v0, p0, Lsage/mode/dexter/MultiDexExtractor;->cacheLock:Ljava/nio/channels/FileLock;

    invoke-virtual {v0}, Ljava/nio/channels/FileLock;->isValid()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 90
    if-nez p3, :cond_1

    iget-object v0, p0, Lsage/mode/dexter/MultiDexExtractor;->sourceApk:Ljava/io/File;

    iget-wide v2, p0, Lsage/mode/dexter/MultiDexExtractor;->sourceCrc:J

    invoke-static {p1, v0, v2, v3, p2}, Lsage/mode/dexter/MultiDexExtractor;->isModified(Landroid/content/Context;Ljava/io/File;JLjava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 100
    :cond_0
    :try_start_0
    invoke-direct {p0, p1, p2}, Lsage/mode/dexter/MultiDexExtractor;->loadExistingExtractions(Landroid/content/Context;Ljava/lang/String;)Ljava/util/List;

    move-result-object v0
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 106
    .local v0, "files":Ljava/util/List;, "Ljava/util/List<Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;>;"
    goto :goto_2

    .line 101
    .end local v0    # "files":Ljava/util/List;, "Ljava/util/List<Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;>;"
    :catch_0
    move-exception v0

    .line 102
    .local v0, "ioe":Ljava/io/IOException;
    const-string v2, "Failed to reload existing extracted secondary dex files, falling back to fresh extraction"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 103
    invoke-direct {p0}, Lsage/mode/dexter/MultiDexExtractor;->performExtractions()Ljava/util/List;

    move-result-object v2

    .line 104
    .local v2, "files2":Ljava/util/List;, "Ljava/util/List<Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;>;"
    iget-object v3, p0, Lsage/mode/dexter/MultiDexExtractor;->sourceApk:Ljava/io/File;

    invoke-static {v3}, Lsage/mode/dexter/MultiDexExtractor;->getTimeStamp(Ljava/io/File;)J

    move-result-wide v5

    iget-wide v7, p0, Lsage/mode/dexter/MultiDexExtractor;->sourceCrc:J

    move-object v3, p1

    move-object v4, p2

    move-object v9, v2

    invoke-static/range {v3 .. v9}, Lsage/mode/dexter/MultiDexExtractor;->putStoredApkInfo(Landroid/content/Context;Ljava/lang/String;JJLjava/util/List;)V

    .line 105
    move-object v3, v2

    move-object v0, v3

    .local v3, "files":Ljava/util/List;, "Ljava/util/List<Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;>;"
    goto :goto_2

    .line 91
    .end local v0    # "ioe":Ljava/io/IOException;
    .end local v2    # "files2":Ljava/util/List;, "Ljava/util/List<Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;>;"
    .end local v3    # "files":Ljava/util/List;, "Ljava/util/List<Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;>;"
    :cond_1
    :goto_0
    if-eqz p3, :cond_2

    .line 92
    const-string v0, "Forced extraction must be performed."

    invoke-static {v1, v0}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_1

    .line 94
    :cond_2
    const-string v0, "Detected that extraction must be performed."

    invoke-static {v1, v0}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 96
    :goto_1
    invoke-direct {p0}, Lsage/mode/dexter/MultiDexExtractor;->performExtractions()Ljava/util/List;

    move-result-object v0

    .line 97
    .local v0, "files":Ljava/util/List;, "Ljava/util/List<Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;>;"
    iget-object v2, p0, Lsage/mode/dexter/MultiDexExtractor;->sourceApk:Ljava/io/File;

    invoke-static {v2}, Lsage/mode/dexter/MultiDexExtractor;->getTimeStamp(Ljava/io/File;)J

    move-result-wide v4

    iget-wide v6, p0, Lsage/mode/dexter/MultiDexExtractor;->sourceCrc:J

    move-object v2, p1

    move-object v3, p2

    move-object v8, v0

    invoke-static/range {v2 .. v8}, Lsage/mode/dexter/MultiDexExtractor;->putStoredApkInfo(Landroid/content/Context;Ljava/lang/String;JJLjava/util/List;)V

    .line 108
    :goto_2
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "load found "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v3, " secondary dex files"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 109
    return-object v0

    .line 111
    .end local v0    # "files":Ljava/util/List;, "Ljava/util/List<Lsage/mode/dexter/MultiDexExtractor$ExtractedDex;>;"
    :cond_3
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "MultiDexExtractor was closed"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
