package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.core.C0183R;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public final class WindowInsetsAnimationCompat {
    private static final boolean DEBUG = false;
    private static final String TAG = "WindowInsetsAnimCompat";
    private Impl mImpl;

    public WindowInsetsAnimationCompat(int typeMask, Interpolator interpolator, long durationMillis) {
        if (Build.VERSION.SDK_INT >= 30) {
            this.mImpl = new Impl30(typeMask, interpolator, durationMillis);
        } else if (Build.VERSION.SDK_INT >= 21) {
            this.mImpl = new Impl21(typeMask, interpolator, durationMillis);
        } else {
            this.mImpl = new Impl(0, interpolator, durationMillis);
        }
    }

    private WindowInsetsAnimationCompat(WindowInsetsAnimation animation) {
        this(0, null, 0);
        if (Build.VERSION.SDK_INT >= 30) {
            this.mImpl = new Impl30(animation);
        }
    }

    public int getTypeMask() {
        return this.mImpl.getTypeMask();
    }

    public float getFraction() {
        return this.mImpl.getFraction();
    }

    public float getInterpolatedFraction() {
        return this.mImpl.getInterpolatedFraction();
    }

    public Interpolator getInterpolator() {
        return this.mImpl.getInterpolator();
    }

    public long getDurationMillis() {
        return this.mImpl.getDurationMillis();
    }

    public void setFraction(float fraction) {
        this.mImpl.setFraction(fraction);
    }

    public float getAlpha() {
        return this.mImpl.getAlpha();
    }

    public void setAlpha(float alpha) {
        this.mImpl.setAlpha(alpha);
    }

    public static final class BoundsCompat {
        private final Insets mLowerBound;
        private final Insets mUpperBound;

        public BoundsCompat(Insets lowerBound, Insets upperBound) {
            this.mLowerBound = lowerBound;
            this.mUpperBound = upperBound;
        }

        private BoundsCompat(WindowInsetsAnimation.Bounds bounds) {
            this.mLowerBound = Impl30.getLowerBounds(bounds);
            this.mUpperBound = Impl30.getHigherBounds(bounds);
        }

        public Insets getLowerBound() {
            return this.mLowerBound;
        }

        public Insets getUpperBound() {
            return this.mUpperBound;
        }

        public BoundsCompat inset(Insets insets) {
            return new BoundsCompat(WindowInsetsCompat.insetInsets(this.mLowerBound, insets.left, insets.top, insets.right, insets.bottom), WindowInsetsCompat.insetInsets(this.mUpperBound, insets.left, insets.top, insets.right, insets.bottom));
        }

        public String toString() {
            return "Bounds{lower=" + this.mLowerBound + " upper=" + this.mUpperBound + "}";
        }

        public WindowInsetsAnimation.Bounds toBounds() {
            return Impl30.createPlatformBounds(this);
        }

        public static BoundsCompat toBoundsCompat(WindowInsetsAnimation.Bounds bounds) {
            return new BoundsCompat(bounds);
        }
    }

    static WindowInsetsAnimationCompat toWindowInsetsAnimationCompat(WindowInsetsAnimation windowInsetsAnimation) {
        return new WindowInsetsAnimationCompat(windowInsetsAnimation);
    }

    public static abstract class Callback {
        public static final int DISPATCH_MODE_CONTINUE_ON_SUBTREE = 1;
        public static final int DISPATCH_MODE_STOP = 0;
        WindowInsets mDispachedInsets;
        private final int mDispatchMode;

        @Retention(RetentionPolicy.SOURCE)
        public @interface DispatchMode {
        }

        public abstract WindowInsetsCompat onProgress(WindowInsetsCompat windowInsetsCompat, List<WindowInsetsAnimationCompat> list);

        public Callback(int dispatchMode) {
            this.mDispatchMode = dispatchMode;
        }

        public final int getDispatchMode() {
            return this.mDispatchMode;
        }

        public void onPrepare(WindowInsetsAnimationCompat animation) {
        }

        public BoundsCompat onStart(WindowInsetsAnimationCompat animation, BoundsCompat bounds) {
            return bounds;
        }

        public void onEnd(WindowInsetsAnimationCompat animation) {
        }
    }

    static void setCallback(View view, Callback callback) {
        if (Build.VERSION.SDK_INT >= 30) {
            Impl30.setCallback(view, callback);
        } else if (Build.VERSION.SDK_INT >= 21) {
            Impl21.setCallback(view, callback);
        }
    }

    /* access modifiers changed from: private */
    public static class Impl {
        private float mAlpha;
        private final long mDurationMillis;
        private float mFraction;
        private final Interpolator mInterpolator;
        private final int mTypeMask;

        Impl(int typeMask, Interpolator interpolator, long durationMillis) {
            this.mTypeMask = typeMask;
            this.mInterpolator = interpolator;
            this.mDurationMillis = durationMillis;
        }

        public int getTypeMask() {
            return this.mTypeMask;
        }

        public float getFraction() {
            return this.mFraction;
        }

        public float getInterpolatedFraction() {
            Interpolator interpolator = this.mInterpolator;
            if (interpolator != null) {
                return interpolator.getInterpolation(this.mFraction);
            }
            return this.mFraction;
        }

        public Interpolator getInterpolator() {
            return this.mInterpolator;
        }

        public long getDurationMillis() {
            return this.mDurationMillis;
        }

        public float getAlpha() {
            return this.mAlpha;
        }

        public void setFraction(float fraction) {
            this.mFraction = fraction;
        }

        public void setAlpha(float alpha) {
            this.mAlpha = alpha;
        }
    }

    /* access modifiers changed from: private */
    public static class Impl21 extends Impl {
        Impl21(int typeMask, Interpolator interpolator, long durationMillis) {
            super(typeMask, interpolator, durationMillis);
        }

        static void setCallback(View view, Callback callback) {
            Object userListener = view.getTag(C0183R.C0186id.tag_on_apply_window_listener);
            if (callback == null) {
                view.setTag(C0183R.C0186id.tag_window_insets_animation_callback, null);
                if (userListener == null) {
                    view.setOnApplyWindowInsetsListener(null);
                    return;
                }
                return;
            }
            View.OnApplyWindowInsetsListener proxyListener = createProxyListener(view, callback);
            view.setTag(C0183R.C0186id.tag_window_insets_animation_callback, proxyListener);
            if (userListener == null) {
                view.setOnApplyWindowInsetsListener(proxyListener);
            }
        }

        private static View.OnApplyWindowInsetsListener createProxyListener(View view, Callback callback) {
            return new Impl21OnApplyWindowInsetsListener(view, callback);
        }

        static BoundsCompat computeAnimationBounds(WindowInsetsCompat targetInsets, WindowInsetsCompat startingInsets, int mask) {
            Insets targetInsetsInsets = targetInsets.getInsets(mask);
            Insets startingInsetsInsets = startingInsets.getInsets(mask);
            return new BoundsCompat(Insets.m5of(Math.min(targetInsetsInsets.left, startingInsetsInsets.left), Math.min(targetInsetsInsets.top, startingInsetsInsets.top), Math.min(targetInsetsInsets.right, startingInsetsInsets.right), Math.min(targetInsetsInsets.bottom, startingInsetsInsets.bottom)), Insets.m5of(Math.max(targetInsetsInsets.left, startingInsetsInsets.left), Math.max(targetInsetsInsets.top, startingInsetsInsets.top), Math.max(targetInsetsInsets.right, startingInsetsInsets.right), Math.max(targetInsetsInsets.bottom, startingInsetsInsets.bottom)));
        }

        static int buildAnimationMask(WindowInsetsCompat targetInsets, WindowInsetsCompat currentInsets) {
            int animatingMask = 0;
            for (int i = 1; i <= 256; i <<= 1) {
                if (!targetInsets.getInsets(i).equals(currentInsets.getInsets(i))) {
                    animatingMask |= i;
                }
            }
            return animatingMask;
        }

        static WindowInsetsCompat interpolateInsets(WindowInsetsCompat target, WindowInsetsCompat starting, float fraction, int typeMask) {
            WindowInsetsCompat.Builder builder = new WindowInsetsCompat.Builder(target);
            for (int i = 1; i <= 256; i <<= 1) {
                if ((typeMask & i) == 0) {
                    builder.setInsets(i, target.getInsets(i));
                } else {
                    Insets targetInsets = target.getInsets(i);
                    Insets startingInsets = starting.getInsets(i);
                    builder.setInsets(i, WindowInsetsCompat.insetInsets(targetInsets, (int) (((double) (((float) (targetInsets.left - startingInsets.left)) * (1.0f - fraction))) + 0.5d), (int) (((double) (((float) (targetInsets.top - startingInsets.top)) * (1.0f - fraction))) + 0.5d), (int) (((double) (((float) (targetInsets.right - startingInsets.right)) * (1.0f - fraction))) + 0.5d), (int) (((double) (((float) (targetInsets.bottom - startingInsets.bottom)) * (1.0f - fraction))) + 0.5d)));
                }
            }
            return builder.build();
        }

        /* access modifiers changed from: private */
        public static class Impl21OnApplyWindowInsetsListener implements View.OnApplyWindowInsetsListener {
            private static final int COMPAT_ANIMATION_DURATION = 160;
            final Callback mCallback;
            private WindowInsetsCompat mLastInsets;

            Impl21OnApplyWindowInsetsListener(View view, Callback callback) {
                WindowInsetsCompat windowInsetsCompat;
                this.mCallback = callback;
                WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(view);
                if (rootWindowInsets != null) {
                    windowInsetsCompat = new WindowInsetsCompat.Builder(rootWindowInsets).build();
                } else {
                    windowInsetsCompat = null;
                }
                this.mLastInsets = windowInsetsCompat;
            }

            public WindowInsets onApplyWindowInsets(final View v, WindowInsets insets) {
                if (!v.isLaidOut()) {
                    this.mLastInsets = WindowInsetsCompat.toWindowInsetsCompat(insets, v);
                    return Impl21.forwardToViewIfNeeded(v, insets);
                }
                final WindowInsetsCompat targetInsets = WindowInsetsCompat.toWindowInsetsCompat(insets, v);
                if (this.mLastInsets == null) {
                    this.mLastInsets = ViewCompat.getRootWindowInsets(v);
                }
                if (this.mLastInsets == null) {
                    this.mLastInsets = targetInsets;
                    return Impl21.forwardToViewIfNeeded(v, insets);
                }
                Callback callback = Impl21.getCallback(v);
                if (callback != null && Objects.equals(callback.mDispachedInsets, insets)) {
                    return Impl21.forwardToViewIfNeeded(v, insets);
                }
                final int animationMask = Impl21.buildAnimationMask(targetInsets, this.mLastInsets);
                if (animationMask == 0) {
                    return Impl21.forwardToViewIfNeeded(v, insets);
                }
                final WindowInsetsCompat startingInsets = this.mLastInsets;
                final WindowInsetsAnimationCompat anim = new WindowInsetsAnimationCompat(animationMask, new DecelerateInterpolator(), 160);
                anim.setFraction(0.0f);
                final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(anim.getDurationMillis());
                final BoundsCompat animationBounds = Impl21.computeAnimationBounds(targetInsets, startingInsets, animationMask);
                Impl21.dispatchOnPrepare(v, anim, insets, false);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    /* class androidx.core.view.WindowInsetsAnimationCompat.Impl21.Impl21OnApplyWindowInsetsListener.C02361 */

                    public void onAnimationUpdate(ValueAnimator animator) {
                        anim.setFraction(animator.getAnimatedFraction());
                        Impl21.dispatchOnProgress(v, Impl21.interpolateInsets(targetInsets, startingInsets, anim.getInterpolatedFraction(), animationMask), Collections.singletonList(anim));
                    }
                });
                animator.addListener(new AnimatorListenerAdapter() {
                    /* class androidx.core.view.WindowInsetsAnimationCompat.Impl21.Impl21OnApplyWindowInsetsListener.C02372 */

                    public void onAnimationEnd(Animator animator) {
                        anim.setFraction(1.0f);
                        Impl21.dispatchOnEnd(v, anim);
                    }
                });
                OneShotPreDrawListener.add(v, new Runnable() {
                    /* class androidx.core.view.WindowInsetsAnimationCompat.Impl21.Impl21OnApplyWindowInsetsListener.RunnableC02383 */

                    public void run() {
                        Impl21.dispatchOnStart(v, anim, animationBounds);
                        animator.start();
                    }
                });
                this.mLastInsets = targetInsets;
                return Impl21.forwardToViewIfNeeded(v, insets);
            }
        }

        static WindowInsets forwardToViewIfNeeded(View v, WindowInsets insets) {
            if (v.getTag(C0183R.C0186id.tag_on_apply_window_listener) != null) {
                return insets;
            }
            return v.onApplyWindowInsets(insets);
        }

        static void dispatchOnPrepare(View v, WindowInsetsAnimationCompat anim, WindowInsets insets, boolean stopDispatch) {
            Callback callback = getCallback(v);
            if (callback != null) {
                callback.mDispachedInsets = insets;
                if (!stopDispatch) {
                    callback.onPrepare(anim);
                    stopDispatch = callback.getDispatchMode() == 0;
                }
            }
            if (v instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) v;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    dispatchOnPrepare(viewGroup.getChildAt(i), anim, insets, stopDispatch);
                }
            }
        }

        static void dispatchOnStart(View v, WindowInsetsAnimationCompat anim, BoundsCompat animationBounds) {
            Callback callback = getCallback(v);
            if (callback != null) {
                callback.onStart(anim, animationBounds);
                if (callback.getDispatchMode() == 0) {
                    return;
                }
            }
            if (v instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) v;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    dispatchOnStart(viewGroup.getChildAt(i), anim, animationBounds);
                }
            }
        }

        static void dispatchOnProgress(View v, WindowInsetsCompat interpolateInsets, List<WindowInsetsAnimationCompat> runningAnimations) {
            Callback callback = getCallback(v);
            WindowInsetsCompat insets = interpolateInsets;
            if (callback != null) {
                insets = callback.onProgress(insets, runningAnimations);
                if (callback.getDispatchMode() == 0) {
                    return;
                }
            }
            if (v instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) v;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    dispatchOnProgress(viewGroup.getChildAt(i), insets, runningAnimations);
                }
            }
        }

        static void dispatchOnEnd(View v, WindowInsetsAnimationCompat anim) {
            Callback callback = getCallback(v);
            if (callback != null) {
                callback.onEnd(anim);
                if (callback.getDispatchMode() == 0) {
                    return;
                }
            }
            if (v instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) v;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    dispatchOnEnd(viewGroup.getChildAt(i), anim);
                }
            }
        }

        static Callback getCallback(View child) {
            Object listener = child.getTag(C0183R.C0186id.tag_window_insets_animation_callback);
            if (listener instanceof Impl21OnApplyWindowInsetsListener) {
                return ((Impl21OnApplyWindowInsetsListener) listener).mCallback;
            }
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static class Impl30 extends Impl {
        private final WindowInsetsAnimation mWrapped;

        Impl30(WindowInsetsAnimation wrapped) {
            super(0, null, 0);
            this.mWrapped = wrapped;
        }

        Impl30(int typeMask, Interpolator interpolator, long durationMillis) {
            this(new WindowInsetsAnimation(typeMask, interpolator, durationMillis));
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public int getTypeMask() {
            return this.mWrapped.getTypeMask();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public Interpolator getInterpolator() {
            return this.mWrapped.getInterpolator();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public long getDurationMillis() {
            return this.mWrapped.getDurationMillis();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public float getFraction() {
            return this.mWrapped.getFraction();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public void setFraction(float fraction) {
            this.mWrapped.setFraction(fraction);
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public float getInterpolatedFraction() {
            return this.mWrapped.getInterpolatedFraction();
        }

        /* access modifiers changed from: private */
        public static class ProxyCallback extends WindowInsetsAnimation.Callback {
            private final HashMap<WindowInsetsAnimation, WindowInsetsAnimationCompat> mAnimations = new HashMap<>();
            private final Callback mCompat;
            private List<WindowInsetsAnimationCompat> mRORunningAnimations;
            private ArrayList<WindowInsetsAnimationCompat> mTmpRunningAnimations;

            ProxyCallback(Callback compat) {
                super(compat.getDispatchMode());
                this.mCompat = compat;
            }

            private WindowInsetsAnimationCompat getWindowInsetsAnimationCompat(WindowInsetsAnimation animation) {
                WindowInsetsAnimationCompat animationCompat = this.mAnimations.get(animation);
                if (animationCompat != null) {
                    return animationCompat;
                }
                WindowInsetsAnimationCompat animationCompat2 = WindowInsetsAnimationCompat.toWindowInsetsAnimationCompat(animation);
                this.mAnimations.put(animation, animationCompat2);
                return animationCompat2;
            }

            public void onPrepare(WindowInsetsAnimation animation) {
                this.mCompat.onPrepare(getWindowInsetsAnimationCompat(animation));
            }

            public WindowInsetsAnimation.Bounds onStart(WindowInsetsAnimation animation, WindowInsetsAnimation.Bounds bounds) {
                return this.mCompat.onStart(getWindowInsetsAnimationCompat(animation), BoundsCompat.toBoundsCompat(bounds)).toBounds();
            }

            public WindowInsets onProgress(WindowInsets insets, List<WindowInsetsAnimation> runningAnimations) {
                ArrayList<WindowInsetsAnimationCompat> arrayList = this.mTmpRunningAnimations;
                if (arrayList == null) {
                    ArrayList<WindowInsetsAnimationCompat> arrayList2 = new ArrayList<>(runningAnimations.size());
                    this.mTmpRunningAnimations = arrayList2;
                    this.mRORunningAnimations = Collections.unmodifiableList(arrayList2);
                } else {
                    arrayList.clear();
                }
                for (int i = runningAnimations.size() - 1; i >= 0; i--) {
                    WindowInsetsAnimation animation = runningAnimations.get(i);
                    WindowInsetsAnimationCompat animationCompat = getWindowInsetsAnimationCompat(animation);
                    animationCompat.setFraction(animation.getFraction());
                    this.mTmpRunningAnimations.add(animationCompat);
                }
                return this.mCompat.onProgress(WindowInsetsCompat.toWindowInsetsCompat(insets), this.mRORunningAnimations).toWindowInsets();
            }

            public void onEnd(WindowInsetsAnimation animation) {
                this.mCompat.onEnd(getWindowInsetsAnimationCompat(animation));
                this.mAnimations.remove(animation);
            }
        }

        public static void setCallback(View view, Callback callback) {
            view.setWindowInsetsAnimationCallback(callback != null ? new ProxyCallback(callback) : null);
        }

        public static WindowInsetsAnimation.Bounds createPlatformBounds(BoundsCompat bounds) {
            return new WindowInsetsAnimation.Bounds(bounds.getLowerBound().toPlatformInsets(), bounds.getUpperBound().toPlatformInsets());
        }

        public static Insets getLowerBounds(WindowInsetsAnimation.Bounds bounds) {
            return Insets.toCompatInsets(bounds.getLowerBound());
        }

        public static Insets getHigherBounds(WindowInsetsAnimation.Bounds bounds) {
            return Insets.toCompatInsets(bounds.getUpperBound());
        }
    }
}
