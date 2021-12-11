package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

public final class FadeThroughProvider implements VisibilityAnimatorProvider {
    static final float FADE_THROUGH_THRESHOLD = 0.35f;
    private float progressThreshold = FADE_THROUGH_THRESHOLD;

    public float getProgressThreshold() {
        return this.progressThreshold;
    }

    public void setProgressThreshold(float progressThreshold2) {
        this.progressThreshold = progressThreshold2;
    }

    @Override // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    public Animator createAppear(ViewGroup sceneRoot, View view) {
        float originalAlpha = view.getAlpha() == 0.0f ? 1.0f : view.getAlpha();
        return createFadeThroughAnimator(view, 0.0f, originalAlpha, this.progressThreshold, 1.0f, originalAlpha);
    }

    @Override // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    public Animator createDisappear(ViewGroup sceneRoot, View view) {
        float originalAlpha = view.getAlpha() == 0.0f ? 1.0f : view.getAlpha();
        return createFadeThroughAnimator(view, originalAlpha, 0.0f, 0.0f, this.progressThreshold, originalAlpha);
    }

    private static Animator createFadeThroughAnimator(final View view, final float startValue, final float endValue, final float startFraction, final float endFraction, final float originalAlpha) {
        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /* class com.google.android.material.transition.platform.FadeThroughProvider.C08221 */

            public void onAnimationUpdate(ValueAnimator animation) {
                view.setAlpha(TransitionUtils.lerp(startValue, endValue, startFraction, endFraction, ((Float) animation.getAnimatedValue()).floatValue()));
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            /* class com.google.android.material.transition.platform.FadeThroughProvider.C08232 */

            public void onAnimationEnd(Animator animation) {
                view.setAlpha(originalAlpha);
            }
        });
        return animator;
    }
}
