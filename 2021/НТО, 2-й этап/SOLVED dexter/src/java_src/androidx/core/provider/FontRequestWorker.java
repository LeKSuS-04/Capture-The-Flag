package androidx.core.provider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Consumer;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* access modifiers changed from: package-private */
public class FontRequestWorker {
    private static final ExecutorService DEFAULT_EXECUTOR_SERVICE = RequestExecutor.createDefaultExecutor("fonts-androidx", 10, 10000);
    static final Object LOCK = new Object();
    static final SimpleArrayMap<String, ArrayList<Consumer<TypefaceResult>>> PENDING_REPLIES = new SimpleArrayMap<>();
    static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);

    private FontRequestWorker() {
    }

    static void resetTypefaceCache() {
        sTypefaceCache.evictAll();
    }

    static Typeface requestFontSync(final Context context, final FontRequest request, CallbackWithHandler callback, final int style, int timeoutInMillis) {
        final String id = createCacheId(request, style);
        Typeface cached = sTypefaceCache.get(id);
        if (cached != null) {
            callback.onTypefaceResult(new TypefaceResult(cached));
            return cached;
        } else if (timeoutInMillis == -1) {
            TypefaceResult typefaceResult = getFontSync(id, context, request, style);
            callback.onTypefaceResult(typefaceResult);
            return typefaceResult.mTypeface;
        } else {
            try {
                TypefaceResult typefaceResult2 = (TypefaceResult) RequestExecutor.submit(DEFAULT_EXECUTOR_SERVICE, new Callable<TypefaceResult>() {
                    /* class androidx.core.provider.FontRequestWorker.CallableC02141 */

                    @Override // java.util.concurrent.Callable
                    public TypefaceResult call() {
                        return FontRequestWorker.getFontSync(id, context, request, style);
                    }
                }, timeoutInMillis);
                callback.onTypefaceResult(typefaceResult2);
                return typefaceResult2.mTypeface;
            } catch (InterruptedException e) {
                callback.onTypefaceResult(new TypefaceResult(-3));
                return null;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003d, code lost:
        r3 = new androidx.core.provider.FontRequestWorker.CallableC02163();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        if (r11 != null) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0044, code lost:
        r4 = androidx.core.provider.FontRequestWorker.DEFAULT_EXECUTOR_SERVICE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0047, code lost:
        r4 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0048, code lost:
        androidx.core.provider.RequestExecutor.execute(r4, r3, new androidx.core.provider.FontRequestWorker.C02174());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0050, code lost:
        return null;
     */
    static Typeface requestFontAsync(final Context context, final FontRequest request, final int style, Executor executor, final CallbackWithHandler callback) {
        final String id = createCacheId(request, style);
        Typeface cached = sTypefaceCache.get(id);
        if (cached != null) {
            callback.onTypefaceResult(new TypefaceResult(cached));
            return cached;
        }
        Consumer<TypefaceResult> reply = new Consumer<TypefaceResult>() {
            /* class androidx.core.provider.FontRequestWorker.C02152 */

            public void accept(TypefaceResult typefaceResult) {
                CallbackWithHandler.this.onTypefaceResult(typefaceResult);
            }
        };
        synchronized (LOCK) {
            SimpleArrayMap<String, ArrayList<Consumer<TypefaceResult>>> simpleArrayMap = PENDING_REPLIES;
            ArrayList<Consumer<TypefaceResult>> pendingReplies = simpleArrayMap.get(id);
            if (pendingReplies != null) {
                pendingReplies.add(reply);
                return null;
            }
            ArrayList<Consumer<TypefaceResult>> pendingReplies2 = new ArrayList<>();
            pendingReplies2.add(reply);
            simpleArrayMap.put(id, pendingReplies2);
        }
    }

    private static String createCacheId(FontRequest request, int style) {
        return request.getId() + "-" + style;
    }

    static TypefaceResult getFontSync(String cacheId, Context context, FontRequest request, int style) {
        LruCache<String, Typeface> lruCache = sTypefaceCache;
        Typeface cached = lruCache.get(cacheId);
        if (cached != null) {
            return new TypefaceResult(cached);
        }
        try {
            FontsContractCompat.FontFamilyResult result = FontProvider.getFontFamilyResult(context, request, null);
            int fontFamilyResultStatus = getFontFamilyResultStatus(result);
            if (fontFamilyResultStatus != 0) {
                return new TypefaceResult(fontFamilyResultStatus);
            }
            Typeface typeface = TypefaceCompat.createFromFontInfo(context, null, result.getFonts(), style);
            if (typeface == null) {
                return new TypefaceResult(-3);
            }
            lruCache.put(cacheId, typeface);
            return new TypefaceResult(typeface);
        } catch (PackageManager.NameNotFoundException e) {
            return new TypefaceResult(-1);
        }
    }

    private static int getFontFamilyResultStatus(FontsContractCompat.FontFamilyResult fontFamilyResult) {
        if (fontFamilyResult.getStatusCode() != 0) {
            switch (fontFamilyResult.getStatusCode()) {
                case 1:
                    return -2;
                default:
                    return -3;
            }
        } else {
            FontsContractCompat.FontInfo[] fonts = fontFamilyResult.getFonts();
            if (fonts == null || fonts.length == 0) {
                return 1;
            }
            for (FontsContractCompat.FontInfo font : fonts) {
                int resultCode = font.getResultCode();
                if (resultCode != 0) {
                    if (resultCode < 0) {
                        return -3;
                    } else {
                        return resultCode;
                    }
                }
            }
            return 0;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        TypefaceResult(int result) {
            this.mTypeface = null;
            this.mResult = result;
        }

        TypefaceResult(Typeface typeface) {
            this.mTypeface = typeface;
            this.mResult = 0;
        }

        /* access modifiers changed from: package-private */
        public boolean isSuccess() {
            return this.mResult == 0;
        }
    }
}
