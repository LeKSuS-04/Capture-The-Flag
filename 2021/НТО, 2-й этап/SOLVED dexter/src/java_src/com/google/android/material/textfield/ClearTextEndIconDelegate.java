package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.content.res.AppCompatResources;
import com.google.android.material.C0552R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.textfield.TextInputLayout;

/* access modifiers changed from: package-private */
public class ClearTextEndIconDelegate extends EndIconDelegate {
    private static final int ANIMATION_FADE_DURATION = 100;
    private static final int ANIMATION_SCALE_DURATION = 150;
    private static final float ANIMATION_SCALE_FROM_VALUE = 0.8f;
    private final TextWatcher clearTextEndIconTextWatcher = new TextWatcher() {
        /* class com.google.android.material.textfield.ClearTextEndIconDelegate.C07361 */

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            if (ClearTextEndIconDelegate.this.textInputLayout.getSuffixText() == null) {
                ClearTextEndIconDelegate clearTextEndIconDelegate = ClearTextEndIconDelegate.this;
                clearTextEndIconDelegate.animateIcon(clearTextEndIconDelegate.textInputLayout.hasFocus() && ClearTextEndIconDelegate.hasText(s));
            }
        }
    };
    private final TextInputLayout.OnEditTextAttachedListener clearTextOnEditTextAttachedListener = new TextInputLayout.OnEditTextAttachedListener() {
        /* class com.google.android.material.textfield.ClearTextEndIconDelegate.C07383 */

        @Override // com.google.android.material.textfield.TextInputLayout.OnEditTextAttachedListener
        public void onEditTextAttached(TextInputLayout textInputLayout) {
            EditText editText = textInputLayout.getEditText();
            textInputLayout.setEndIconVisible(editText.hasFocus() && ClearTextEndIconDelegate.hasText(editText.getText()));
            textInputLayout.setEndIconCheckable(false);
            editText.setOnFocusChangeListener(ClearTextEndIconDelegate.this.onFocusChangeListener);
            editText.removeTextChangedListener(ClearTextEndIconDelegate.this.clearTextEndIconTextWatcher);
            editText.addTextChangedListener(ClearTextEndIconDelegate.this.clearTextEndIconTextWatcher);
        }
    };
    private final TextInputLayout.OnEndIconChangedListener endIconChangedListener = new TextInputLayout.OnEndIconChangedListener() {
        /* class com.google.android.material.textfield.ClearTextEndIconDelegate.C07394 */

        @Override // com.google.android.material.textfield.TextInputLayout.OnEndIconChangedListener
        public void onEndIconChanged(TextInputLayout textInputLayout, int previousIcon) {
            final EditText editText = textInputLayout.getEditText();
            if (editText != null && previousIcon == 2) {
                editText.post(new Runnable() {
                    /* class com.google.android.material.textfield.ClearTextEndIconDelegate.C07394.RunnableC07401 */

                    public void run() {
                        editText.removeTextChangedListener(ClearTextEndIconDelegate.this.clearTextEndIconTextWatcher);
                    }
                });
                if (editText.getOnFocusChangeListener() == ClearTextEndIconDelegate.this.onFocusChangeListener) {
                    editText.setOnFocusChangeListener(null);
                }
            }
        }
    };
    private AnimatorSet iconInAnim;
    private ValueAnimator iconOutAnim;
    private final View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        /* class com.google.android.material.textfield.ClearTextEndIconDelegate.View$OnFocusChangeListenerC07372 */

        public void onFocusChange(View v, boolean hasFocus) {
            boolean z = true;
            boolean hasText = !TextUtils.isEmpty(((EditText) v).getText());
            ClearTextEndIconDelegate clearTextEndIconDelegate = ClearTextEndIconDelegate.this;
            if (!hasText || !hasFocus) {
                z = false;
            }
            clearTextEndIconDelegate.animateIcon(z);
        }
    };

    ClearTextEndIconDelegate(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.material.textfield.EndIconDelegate
    public void initialize() {
        this.textInputLayout.setEndIconDrawable(AppCompatResources.getDrawable(this.context, C0552R.C0554drawable.mtrl_ic_cancel));
        this.textInputLayout.setEndIconContentDescription(this.textInputLayout.getResources().getText(C0552R.string.clear_text_end_icon_content_description));
        this.textInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            /* class com.google.android.material.textfield.ClearTextEndIconDelegate.View$OnClickListenerC07415 */

            public void onClick(View v) {
                Editable text = ClearTextEndIconDelegate.this.textInputLayout.getEditText().getText();
                if (text != null) {
                    text.clear();
                }
                ClearTextEndIconDelegate.this.textInputLayout.refreshEndIconDrawableState();
            }
        });
        this.textInputLayout.addOnEditTextAttachedListener(this.clearTextOnEditTextAttachedListener);
        this.textInputLayout.addOnEndIconChangedListener(this.endIconChangedListener);
        initAnimators();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.material.textfield.EndIconDelegate
    public void onSuffixVisibilityChanged(boolean visible) {
        if (this.textInputLayout.getSuffixText() != null) {
            animateIcon(visible);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void animateIcon(boolean show) {
        boolean shouldSkipAnimation = this.textInputLayout.isEndIconVisible() == show;
        if (show && !this.iconInAnim.isRunning()) {
            this.iconOutAnim.cancel();
            this.iconInAnim.start();
            if (shouldSkipAnimation) {
                this.iconInAnim.end();
            }
        } else if (!show) {
            this.iconInAnim.cancel();
            this.iconOutAnim.start();
            if (shouldSkipAnimation) {
                this.iconOutAnim.end();
            }
        }
    }

    private void initAnimators() {
        ValueAnimator scaleAnimator = getScaleAnimator();
        ValueAnimator fadeAnimator = getAlphaAnimator(0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        this.iconInAnim = animatorSet;
        animatorSet.playTogether(scaleAnimator, fadeAnimator);
        this.iconInAnim.addListener(new AnimatorListenerAdapter() {
            /* class com.google.android.material.textfield.ClearTextEndIconDelegate.C07426 */

            public void onAnimationStart(Animator animation) {
                ClearTextEndIconDelegate.this.textInputLayout.setEndIconVisible(true);
            }
        });
        ValueAnimator alphaAnimator = getAlphaAnimator(1.0f, 0.0f);
        this.iconOutAnim = alphaAnimator;
        alphaAnimator.addListener(new AnimatorListenerAdapter() {
            /* class com.google.android.material.textfield.ClearTextEndIconDelegate.C07437 */

            public void onAnimationEnd(Animator animation) {
                ClearTextEndIconDelegate.this.textInputLayout.setEndIconVisible(false);
            }
        });
    }

    private ValueAnimator getAlphaAnimator(float... values) {
        ValueAnimator animator = ValueAnimator.ofFloat(values);
        animator.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        animator.setDuration(100L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /* class com.google.android.material.textfield.ClearTextEndIconDelegate.C07448 */

            public void onAnimationUpdate(ValueAnimator animation) {
                ClearTextEndIconDelegate.this.endIconView.setAlpha(((Float) animation.getAnimatedValue()).floatValue());
            }
        });
        return animator;
    }

    private ValueAnimator getScaleAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(ANIMATION_SCALE_FROM_VALUE, 1.0f);
        animator.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        animator.setDuration(150L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /* class com.google.android.material.textfield.ClearTextEndIconDelegate.C07459 */

            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = ((Float) animation.getAnimatedValue()).floatValue();
                ClearTextEndIconDelegate.this.endIconView.setScaleX(scale);
                ClearTextEndIconDelegate.this.endIconView.setScaleY(scale);
            }
        });
        return animator;
    }

    /* access modifiers changed from: private */
    public static boolean hasText(Editable editable) {
        return editable.length() > 0;
    }
}
