package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.TypedValue;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.util.Preconditions;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParserException;

public final class ResourcesCompat {
    public static final int ID_NULL = 0;
    private static final String TAG = "ResourcesCompat";

    public static Drawable getDrawable(Resources res, int id, Resources.Theme theme) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 21) {
            return res.getDrawable(id, theme);
        }
        return res.getDrawable(id);
    }

    public static Drawable getDrawableForDensity(Resources res, int id, int density, Resources.Theme theme) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 21) {
            return res.getDrawableForDensity(id, density, theme);
        }
        if (Build.VERSION.SDK_INT >= 15) {
            return res.getDrawableForDensity(id, density);
        }
        return res.getDrawable(id);
    }

    public static int getColor(Resources res, int id, Resources.Theme theme) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 23) {
            return res.getColor(id, theme);
        }
        return res.getColor(id);
    }

    public static ColorStateList getColorStateList(Resources res, int id, Resources.Theme theme) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 23) {
            return res.getColorStateList(id, theme);
        }
        return res.getColorStateList(id);
    }

    public static float getFloat(Resources res, int id) {
        TypedValue value = new TypedValue();
        res.getValue(id, value, true);
        if (value.type == 4) {
            return value.getFloat();
        }
        throw new Resources.NotFoundException("Resource ID #0x" + Integer.toHexString(id) + " type #0x" + Integer.toHexString(value.type) + " is not valid");
    }

    public static Typeface getFont(Context context, int id) throws Resources.NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, id, new TypedValue(), 0, null, null, false, false);
    }

    public static Typeface getCachedFont(Context context, int id) throws Resources.NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, id, new TypedValue(), 0, null, null, false, true);
    }

    public static abstract class FontCallback {
        public abstract void onFontRetrievalFailed(int i);

        public abstract void onFontRetrieved(Typeface typeface);

        public final void callbackSuccessAsync(final Typeface typeface, Handler handler) {
            getHandler(handler).post(new Runnable() {
                /* class androidx.core.content.res.ResourcesCompat.FontCallback.RunnableC01961 */

                public void run() {
                    FontCallback.this.onFontRetrieved(typeface);
                }
            });
        }

        public final void callbackFailAsync(final int reason, Handler handler) {
            getHandler(handler).post(new Runnable() {
                /* class androidx.core.content.res.ResourcesCompat.FontCallback.RunnableC01972 */

                public void run() {
                    FontCallback.this.onFontRetrievalFailed(reason);
                }
            });
        }

        public static Handler getHandler(Handler handler) {
            return handler == null ? new Handler(Looper.getMainLooper()) : handler;
        }
    }

    public static void getFont(Context context, int id, FontCallback fontCallback, Handler handler) throws Resources.NotFoundException {
        Preconditions.checkNotNull(fontCallback);
        if (context.isRestricted()) {
            fontCallback.callbackFailAsync(-4, handler);
        } else {
            loadFont(context, id, new TypedValue(), 0, fontCallback, handler, false, false);
        }
    }

    public static Typeface getFont(Context context, int id, TypedValue value, int style, FontCallback fontCallback) throws Resources.NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, id, value, style, fontCallback, null, true, false);
    }

    private static Typeface loadFont(Context context, int id, TypedValue value, int style, FontCallback fontCallback, Handler handler, boolean isRequestFromLayoutInflator, boolean isCachedOnly) {
        Resources resources = context.getResources();
        resources.getValue(id, value, true);
        Typeface typeface = loadFont(context, resources, value, id, style, fontCallback, handler, isRequestFromLayoutInflator, isCachedOnly);
        if (typeface != null || fontCallback != null || isCachedOnly) {
            return typeface;
        }
        throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(id) + " could not be retrieved.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:66:0x00f3  */
    private static Typeface loadFont(Context context, Resources wrapper, TypedValue value, int id, int style, FontCallback fontCallback, Handler handler, boolean isRequestFromLayoutInflator, boolean isCachedOnly) {
        String file;
        XmlPullParserException e;
        IOException e2;
        Typeface typeface;
        if (value.string != null) {
            String file2 = value.string.toString();
            if (!file2.startsWith("res/")) {
                if (fontCallback != null) {
                    fontCallback.callbackFailAsync(-3, handler);
                }
                return null;
            }
            Typeface typeface2 = TypefaceCompat.findFromCache(wrapper, id, style);
            if (typeface2 != null) {
                if (fontCallback != null) {
                    fontCallback.callbackSuccessAsync(typeface2, handler);
                }
                return typeface2;
            } else if (isCachedOnly) {
                return null;
            } else {
                try {
                    if (file2.toLowerCase().endsWith(".xml")) {
                        try {
                            FontResourcesParserCompat.FamilyResourceEntry familyEntry = FontResourcesParserCompat.parse(wrapper.getXml(id), wrapper);
                            if (familyEntry == null) {
                                try {
                                    Log.e(TAG, "Failed to find font-family tag");
                                    if (fontCallback != null) {
                                        fontCallback.callbackFailAsync(-3, handler);
                                    }
                                    return null;
                                } catch (XmlPullParserException e3) {
                                    e = e3;
                                    file = file2;
                                    Log.e(TAG, "Failed to parse xml resource " + file, e);
                                    if (fontCallback != null) {
                                    }
                                    return null;
                                } catch (IOException e4) {
                                    e2 = e4;
                                    file = file2;
                                    Log.e(TAG, "Failed to read xml resource " + file, e2);
                                    if (fontCallback != null) {
                                    }
                                    return null;
                                }
                            } else {
                                typeface = typeface2;
                                file = file2;
                                try {
                                    return TypefaceCompat.createFromResourcesFamilyXml(context, familyEntry, wrapper, id, style, fontCallback, handler, isRequestFromLayoutInflator);
                                } catch (XmlPullParserException e5) {
                                    e = e5;
                                    Log.e(TAG, "Failed to parse xml resource " + file, e);
                                    if (fontCallback != null) {
                                    }
                                    return null;
                                } catch (IOException e6) {
                                    e2 = e6;
                                    Log.e(TAG, "Failed to read xml resource " + file, e2);
                                    if (fontCallback != null) {
                                    }
                                    return null;
                                }
                            }
                        } catch (XmlPullParserException e7) {
                            e = e7;
                            file = file2;
                            Log.e(TAG, "Failed to parse xml resource " + file, e);
                            if (fontCallback != null) {
                            }
                            return null;
                        } catch (IOException e8) {
                            e2 = e8;
                            file = file2;
                            Log.e(TAG, "Failed to read xml resource " + file, e2);
                            if (fontCallback != null) {
                            }
                            return null;
                        }
                    } else {
                        typeface = typeface2;
                        file = file2;
                        try {
                            Typeface typeface3 = TypefaceCompat.createFromResourcesFontFile(context, wrapper, id, file, style);
                            if (fontCallback != null) {
                                if (typeface3 != null) {
                                    try {
                                        fontCallback.callbackSuccessAsync(typeface3, handler);
                                    } catch (XmlPullParserException e9) {
                                        e = e9;
                                        Log.e(TAG, "Failed to parse xml resource " + file, e);
                                        if (fontCallback != null) {
                                        }
                                        return null;
                                    } catch (IOException e10) {
                                        e2 = e10;
                                        Log.e(TAG, "Failed to read xml resource " + file, e2);
                                        if (fontCallback != null) {
                                        }
                                        return null;
                                    }
                                } else {
                                    fontCallback.callbackFailAsync(-3, handler);
                                }
                            }
                            return typeface3;
                        } catch (XmlPullParserException e11) {
                            e = e11;
                            Log.e(TAG, "Failed to parse xml resource " + file, e);
                            if (fontCallback != null) {
                            }
                            return null;
                        } catch (IOException e12) {
                            e2 = e12;
                            Log.e(TAG, "Failed to read xml resource " + file, e2);
                            if (fontCallback != null) {
                            }
                            return null;
                        }
                    }
                } catch (XmlPullParserException e13) {
                    e = e13;
                    file = file2;
                    Log.e(TAG, "Failed to parse xml resource " + file, e);
                    if (fontCallback != null) {
                        fontCallback.callbackFailAsync(-3, handler);
                    }
                    return null;
                } catch (IOException e14) {
                    e2 = e14;
                    file = file2;
                    Log.e(TAG, "Failed to read xml resource " + file, e2);
                    if (fontCallback != null) {
                    }
                    return null;
                }
            }
        } else {
            throw new Resources.NotFoundException("Resource \"" + wrapper.getResourceName(id) + "\" (" + Integer.toHexString(id) + ") is not a Font: " + value);
        }
    }

    private ResourcesCompat() {
    }

    public static final class ThemeCompat {
        private ThemeCompat() {
        }

        public static void rebase(Resources.Theme theme) {
            if (Build.VERSION.SDK_INT >= 29) {
                ImplApi29.rebase(theme);
            } else if (Build.VERSION.SDK_INT >= 23) {
                ImplApi23.rebase(theme);
            }
        }

        /* access modifiers changed from: package-private */
        public static class ImplApi29 {
            private ImplApi29() {
            }

            static void rebase(Resources.Theme theme) {
                theme.rebase();
            }
        }

        /* access modifiers changed from: package-private */
        public static class ImplApi23 {
            private static Method sRebaseMethod;
            private static boolean sRebaseMethodFetched;
            private static final Object sRebaseMethodLock = new Object();

            private ImplApi23() {
            }

            static void rebase(Resources.Theme theme) {
                synchronized (sRebaseMethodLock) {
                    if (!sRebaseMethodFetched) {
                        try {
                            Method declaredMethod = Resources.Theme.class.getDeclaredMethod("rebase", new Class[0]);
                            sRebaseMethod = declaredMethod;
                            declaredMethod.setAccessible(true);
                        } catch (NoSuchMethodException e) {
                            Log.i(ResourcesCompat.TAG, "Failed to retrieve rebase() method", e);
                        }
                        sRebaseMethodFetched = true;
                    }
                    Method method = sRebaseMethod;
                    if (method != null) {
                        try {
                            method.invoke(theme, new Object[0]);
                        } catch (IllegalAccessException | InvocationTargetException e2) {
                            Log.i(ResourcesCompat.TAG, "Failed to invoke rebase() method via reflection", e2);
                            sRebaseMethod = null;
                        }
                    }
                }
            }
        }
    }
}
