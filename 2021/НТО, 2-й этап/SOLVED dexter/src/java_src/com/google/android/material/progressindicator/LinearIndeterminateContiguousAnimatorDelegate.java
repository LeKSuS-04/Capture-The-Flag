package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.color.MaterialColors;
import java.util.Arrays;

/* access modifiers changed from: package-private */
public final class LinearIndeterminateContiguousAnimatorDelegate extends IndeterminateAnimatorDelegate<ObjectAnimator> {
    private static final Property<LinearIndeterminateContiguousAnimatorDelegate, Float> ANIMATION_FRACTION = new Property<LinearIndeterminateContiguousAnimatorDelegate, Float>(Float.class, "animationFraction") {
        /* class com.google.android.material.progressindicator.LinearIndeterminateContiguousAnimatorDelegate.C06922 */

        public Float get(LinearIndeterminateContiguousAnimatorDelegate delegate) {
            return Float.valueOf(delegate.getAnimationFraction());
        }

        public void set(LinearIndeterminateContiguousAnimatorDelegate delegate, Float value) {
            delegate.setAnimationFraction(value.floatValue());
        }
    };
    private static final int DURATION_PER_CYCLE_IN_MS = 333;
    private static final int TOTAL_DURATION_IN_MS = 667;
    private float animationFraction;
    private ObjectAnimator animator;
    private final BaseProgressIndicatorSpec baseSpec;
    private boolean dirtyColors;
    private FastOutSlowInInterpolator interpolator;
    private int newIndicatorColorIndex = 1;

    public LinearIndeterminateContiguousAnimatorDelegate(LinearProgressIndicatorSpec spec) {
        super(3);
        this.baseSpec = spec;
        this.interpolator = new FastOutSlowInInterpolator();
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void startAnimator() {
        maybeInitializeAnimators();
        resetPropertiesForNewStart();
        this.animator.start();
    }

    private void maybeInitializeAnimators() {
        if (this.animator == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, ANIMATION_FRACTION, 0.0f, 1.0f);
            this.animator = ofFloat;
            ofFloat.setDuration(333L);
            this.animator.setInterpolator(null);
            this.animator.setRepeatCount(-1);
            this.animator.addListener(new AnimatorListenerAdapter() {
                /* class com.google.android.material.progressindicator.LinearIndeterminateContiguousAnimatorDelegate.C06911 */

                public void onAnimationRepeat(Animator animation) {
                    super.onAnimationRepeat(animation);
                    LinearIndeterminateContiguousAnimatorDelegate linearIndeterminateContiguousAnimatorDelegate = LinearIndeterminateContiguousAnimatorDelegate.this;
                    linearIndeterminateContiguousAnimatorDelegate.newIndicatorColorIndex = (linearIndeterminateContiguousAnimatorDelegate.newIndicatorColorIndex + 1) % LinearIndeterminateContiguousAnimatorDelegate.this.baseSpec.indicatorColors.length;
                    LinearIndeterminateContiguousAnimatorDelegate.this.dirtyColors = true;
                }
            });
        }
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void cancelAnimatorImmediately() {
        ObjectAnimator objectAnimator = this.animator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void requestCancelAnimatorAfterCurrentCycle() {
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void invalidateSpecValues() {
        resetPropertiesForNewStart();
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void registerAnimatorsCompleteCallback(Animatable2Compat.AnimationCallback callback) {
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void unregisterAnimatorsCompleteCallback() {
    }

    private void updateSegmentPositions(int playtime) {
        this.segmentPositions[0] = 0.0f;
        float fraction = getFractionInRange(playtime, 0, TOTAL_DURATION_IN_MS);
        float[] fArr = this.segmentPositions;
        float[] fArr2 = this.segmentPositions;
        float interpolation = this.interpolator.getInterpolation(fraction);
        fArr2[2] = interpolation;
        fArr[1] = interpolation;
        float[] fArr3 = this.segmentPositions;
        float[] fArr4 = this.segmentPositions;
        float interpolation2 = this.interpolator.getInterpolation(fraction + 0.49925038f);
        fArr4[4] = interpolation2;
        fArr3[3] = interpolation2;
        this.segmentPositions[5] = 1.0f;
    }

    private void maybeUpdateSegmentColors() {
        if (this.dirtyColors && this.segmentPositions[3] < 1.0f) {
            this.segmentColors[2] = this.segmentColors[1];
            this.segmentColors[1] = this.segmentColors[0];
            this.segmentColors[0] = MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[this.newIndicatorColorIndex], this.drawable.getAlpha());
            this.dirtyColors = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void resetPropertiesForNewStart() {
        this.dirtyColors = true;
        this.newIndicatorColorIndex = 1;
        Arrays.fill(this.segmentColors, MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[0], this.drawable.getAlpha()));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private float getAnimationFraction() {
        return this.animationFraction;
    }

    /* access modifiers changed from: package-private */
    public void setAnimationFraction(float value) {
        this.animationFraction = value;
        updateSegmentPositions((int) (333.0f * value));
        maybeUpdateSegmentColors();
        this.drawable.invalidateSelf();
    }
}
