package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.ViewGroup;

public final class ScaleProvider implements VisibilityAnimatorProvider {
    private boolean growing;
    private float incomingEndScale;
    private float incomingStartScale;
    private float outgoingEndScale;
    private float outgoingStartScale;
    private boolean scaleOnDisappear;

    public ScaleProvider() {
        this(true);
    }

    public ScaleProvider(boolean growing2) {
        this.outgoingStartScale = 1.0f;
        this.outgoingEndScale = 1.1f;
        this.incomingStartScale = 0.8f;
        this.incomingEndScale = 1.0f;
        this.scaleOnDisappear = true;
        this.growing = growing2;
    }

    public boolean isGrowing() {
        return this.growing;
    }

    public void setGrowing(boolean growing2) {
        this.growing = growing2;
    }

    public boolean isScaleOnDisappear() {
        return this.scaleOnDisappear;
    }

    public void setScaleOnDisappear(boolean scaleOnDisappear2) {
        this.scaleOnDisappear = scaleOnDisappear2;
    }

    public float getOutgoingStartScale() {
        return this.outgoingStartScale;
    }

    public void setOutgoingStartScale(float outgoingStartScale2) {
        this.outgoingStartScale = outgoingStartScale2;
    }

    public float getOutgoingEndScale() {
        return this.outgoingEndScale;
    }

    public void setOutgoingEndScale(float outgoingEndScale2) {
        this.outgoingEndScale = outgoingEndScale2;
    }

    public float getIncomingStartScale() {
        return this.incomingStartScale;
    }

    public void setIncomingStartScale(float incomingStartScale2) {
        this.incomingStartScale = incomingStartScale2;
    }

    public float getIncomingEndScale() {
        return this.incomingEndScale;
    }

    public void setIncomingEndScale(float incomingEndScale2) {
        this.incomingEndScale = incomingEndScale2;
    }

    @Override // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    public Animator createAppear(ViewGroup sceneRoot, View view) {
        if (this.growing) {
            return createScaleAnimator(view, this.incomingStartScale, this.incomingEndScale);
        }
        return createScaleAnimator(view, this.outgoingEndScale, this.outgoingStartScale);
    }

    @Override // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    public Animator createDisappear(ViewGroup sceneRoot, View view) {
        if (!this.scaleOnDisappear) {
            return null;
        }
        if (this.growing) {
            return createScaleAnimator(view, this.outgoingStartScale, this.outgoingEndScale);
        }
        return createScaleAnimator(view, this.incomingEndScale, this.incomingStartScale);
    }

    private static Animator createScaleAnimator(final View view, float startScale, float endScale) {
        final float originalScaleX = view.getScaleX();
        final float originalScaleY = view.getScaleY();
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(View.SCALE_X, originalScaleX * startScale, originalScaleX * endScale), PropertyValuesHolder.ofFloat(View.SCALE_Y, originalScaleY * startScale, originalScaleY * endScale));
        animator.addListener(new AnimatorListenerAdapter() {
            /* class com.google.android.material.transition.platform.ScaleProvider.C08331 */

            public void onAnimationEnd(Animator animation) {
                view.setScaleX(originalScaleX);
                view.setScaleY(originalScaleY);
            }
        });
        return animator;
    }
}
