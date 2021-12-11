package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.C0552R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.textfield.TextInputLayout;

/* access modifiers changed from: package-private */
public class DropdownMenuEndIconDelegate extends EndIconDelegate {
    private static final int ANIMATION_FADE_IN_DURATION = 67;
    private static final int ANIMATION_FADE_OUT_DURATION = 50;
    private static final boolean IS_LOLLIPOP = (Build.VERSION.SDK_INT >= 21);
    private final TextInputLayout.AccessibilityDelegate accessibilityDelegate = new TextInputLayout.AccessibilityDelegate(this.textInputLayout) {
        /* class com.google.android.material.textfield.DropdownMenuEndIconDelegate.C07503 */

        @Override // com.google.android.material.textfield.TextInputLayout.AccessibilityDelegate, androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
            super.onInitializeAccessibilityNodeInfo(host, info);
            if (!DropdownMenuEndIconDelegate.isEditable(DropdownMenuEndIconDelegate.this.textInputLayout.getEditText())) {
                info.setClassName(Spinner.class.getName());
            }
            if (info.isShowingHintText()) {
                info.setHintText(null);
            }
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
            super.onPopulateAccessibilityEvent(host, event);
            AutoCompleteTextView editText = DropdownMenuEndIconDelegate.castAutoCompleteTextViewOrThrow(DropdownMenuEndIconDelegate.this.textInputLayout.getEditText());
            if (event.getEventType() == 1 && DropdownMenuEndIconDelegate.this.accessibilityManager.isTouchExplorationEnabled() && !DropdownMenuEndIconDelegate.isEditable(DropdownMenuEndIconDelegate.this.textInputLayout.getEditText())) {
                DropdownMenuEndIconDelegate.this.showHideDropdown(editText);
            }
        }
    };
    private AccessibilityManager accessibilityManager;
    private final TextInputLayout.OnEditTextAttachedListener dropdownMenuOnEditTextAttachedListener = new TextInputLayout.OnEditTextAttachedListener() {
        /* class com.google.android.material.textfield.DropdownMenuEndIconDelegate.C07514 */

        @Override // com.google.android.material.textfield.TextInputLayout.OnEditTextAttachedListener
        public void onEditTextAttached(TextInputLayout textInputLayout) {
            AutoCompleteTextView autoCompleteTextView = DropdownMenuEndIconDelegate.castAutoCompleteTextViewOrThrow(textInputLayout.getEditText());
            DropdownMenuEndIconDelegate.this.setPopupBackground(autoCompleteTextView);
            DropdownMenuEndIconDelegate.this.addRippleEffect(autoCompleteTextView);
            DropdownMenuEndIconDelegate.this.setUpDropdownShowHideBehavior(autoCompleteTextView);
            autoCompleteTextView.setThreshold(0);
            autoCompleteTextView.removeTextChangedListener(DropdownMenuEndIconDelegate.this.exposedDropdownEndIconTextWatcher);
            autoCompleteTextView.addTextChangedListener(DropdownMenuEndIconDelegate.this.exposedDropdownEndIconTextWatcher);
            textInputLayout.setEndIconCheckable(true);
            textInputLayout.setErrorIconDrawable((Drawable) null);
            if (!DropdownMenuEndIconDelegate.isEditable(autoCompleteTextView)) {
                ViewCompat.setImportantForAccessibility(DropdownMenuEndIconDelegate.this.endIconView, 2);
            }
            textInputLayout.setTextInputAccessibilityDelegate(DropdownMenuEndIconDelegate.this.accessibilityDelegate);
            textInputLayout.setEndIconVisible(true);
        }
    };
    private long dropdownPopupActivatedAt = Long.MAX_VALUE;
    private boolean dropdownPopupDirty = false;
    private final TextInputLayout.OnEndIconChangedListener endIconChangedListener = new TextInputLayout.OnEndIconChangedListener() {
        /* class com.google.android.material.textfield.DropdownMenuEndIconDelegate.C07525 */

        @Override // com.google.android.material.textfield.TextInputLayout.OnEndIconChangedListener
        public void onEndIconChanged(TextInputLayout textInputLayout, int previousIcon) {
            final AutoCompleteTextView editText = (AutoCompleteTextView) textInputLayout.getEditText();
            if (editText != null && previousIcon == 3) {
                editText.post(new Runnable() {
                    /* class com.google.android.material.textfield.DropdownMenuEndIconDelegate.C07525.RunnableC07531 */

                    public void run() {
                        editText.removeTextChangedListener(DropdownMenuEndIconDelegate.this.exposedDropdownEndIconTextWatcher);
                    }
                });
                if (editText.getOnFocusChangeListener() == DropdownMenuEndIconDelegate.this.onFocusChangeListener) {
                    editText.setOnFocusChangeListener(null);
                }
                editText.setOnTouchListener(null);
                if (DropdownMenuEndIconDelegate.IS_LOLLIPOP) {
                    editText.setOnDismissListener(null);
                }
            }
        }
    };
    private final TextWatcher exposedDropdownEndIconTextWatcher = new TextWatcherAdapter() {
        /* class com.google.android.material.textfield.DropdownMenuEndIconDelegate.C07461 */

        @Override // com.google.android.material.internal.TextWatcherAdapter
        public void afterTextChanged(Editable s) {
            final AutoCompleteTextView editText = DropdownMenuEndIconDelegate.castAutoCompleteTextViewOrThrow(DropdownMenuEndIconDelegate.this.textInputLayout.getEditText());
            if (DropdownMenuEndIconDelegate.this.accessibilityManager.isTouchExplorationEnabled() && DropdownMenuEndIconDelegate.isEditable(editText) && !DropdownMenuEndIconDelegate.this.endIconView.hasFocus()) {
                editText.dismissDropDown();
            }
            editText.post(new Runnable() {
                /* class com.google.android.material.textfield.DropdownMenuEndIconDelegate.C07461.RunnableC07471 */

                public void run() {
                    boolean isPopupShowing = editText.isPopupShowing();
                    DropdownMenuEndIconDelegate.this.setEndIconChecked(isPopupShowing);
                    DropdownMenuEndIconDelegate.this.dropdownPopupDirty = isPopupShowing;
                }
            });
        }
    };
    private ValueAnimator fadeInAnim;
    private ValueAnimator fadeOutAnim;
    private StateListDrawable filledPopupBackground;
    private boolean isEndIconChecked = false;
    private final View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        /* class com.google.android.material.textfield.DropdownMenuEndIconDelegate.View$OnFocusChangeListenerC07492 */

        public void onFocusChange(View v, boolean hasFocus) {
            DropdownMenuEndIconDelegate.this.textInputLayout.setEndIconActivated(hasFocus);
            if (!hasFocus) {
                DropdownMenuEndIconDelegate.this.setEndIconChecked(false);
                DropdownMenuEndIconDelegate.this.dropdownPopupDirty = false;
            }
        }
    };
    private MaterialShapeDrawable outlinedPopupBackground;

    DropdownMenuEndIconDelegate(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.material.textfield.EndIconDelegate
    public void initialize() {
        float popupCornerRadius = (float) this.context.getResources().getDimensionPixelOffset(C0552R.dimen.mtrl_shape_corner_size_small_component);
        float exposedDropdownPopupElevation = (float) this.context.getResources().getDimensionPixelOffset(C0552R.dimen.mtrl_exposed_dropdown_menu_popup_elevation);
        int exposedDropdownPopupVerticalPadding = this.context.getResources().getDimensionPixelOffset(C0552R.dimen.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        MaterialShapeDrawable roundedCornersPopupBackground = getPopUpMaterialShapeDrawable(popupCornerRadius, popupCornerRadius, exposedDropdownPopupElevation, exposedDropdownPopupVerticalPadding);
        MaterialShapeDrawable roundedBottomCornersPopupBackground = getPopUpMaterialShapeDrawable(0.0f, popupCornerRadius, exposedDropdownPopupElevation, exposedDropdownPopupVerticalPadding);
        this.outlinedPopupBackground = roundedCornersPopupBackground;
        StateListDrawable stateListDrawable = new StateListDrawable();
        this.filledPopupBackground = stateListDrawable;
        stateListDrawable.addState(new int[]{16842922}, roundedCornersPopupBackground);
        this.filledPopupBackground.addState(new int[0], roundedBottomCornersPopupBackground);
        this.textInputLayout.setEndIconDrawable(AppCompatResources.getDrawable(this.context, IS_LOLLIPOP ? C0552R.C0554drawable.mtrl_dropdown_arrow : C0552R.C0554drawable.mtrl_ic_arrow_drop_down));
        this.textInputLayout.setEndIconContentDescription(this.textInputLayout.getResources().getText(C0552R.string.exposed_dropdown_menu_content_description));
        this.textInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            /* class com.google.android.material.textfield.DropdownMenuEndIconDelegate.View$OnClickListenerC07546 */

            public void onClick(View v) {
                DropdownMenuEndIconDelegate.this.showHideDropdown((AutoCompleteTextView) DropdownMenuEndIconDelegate.this.textInputLayout.getEditText());
            }
        });
        this.textInputLayout.addOnEditTextAttachedListener(this.dropdownMenuOnEditTextAttachedListener);
        this.textInputLayout.addOnEndIconChangedListener(this.endIconChangedListener);
        initAnimators();
        this.accessibilityManager = (AccessibilityManager) this.context.getSystemService("accessibility");
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.material.textfield.EndIconDelegate
    public boolean shouldTintIconOnError() {
        return true;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.material.textfield.EndIconDelegate
    public boolean isBoxBackgroundModeSupported(int boxBackgroundMode) {
        return boxBackgroundMode != 0;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showHideDropdown(AutoCompleteTextView editText) {
        if (editText != null) {
            if (isDropdownPopupActive()) {
                this.dropdownPopupDirty = false;
            }
            if (!this.dropdownPopupDirty) {
                if (IS_LOLLIPOP) {
                    setEndIconChecked(!this.isEndIconChecked);
                } else {
                    this.isEndIconChecked = !this.isEndIconChecked;
                    this.endIconView.toggle();
                }
                if (this.isEndIconChecked) {
                    editText.requestFocus();
                    editText.showDropDown();
                    return;
                }
                editText.dismissDropDown();
                return;
            }
            this.dropdownPopupDirty = false;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPopupBackground(AutoCompleteTextView editText) {
        if (IS_LOLLIPOP) {
            int boxBackgroundMode = this.textInputLayout.getBoxBackgroundMode();
            if (boxBackgroundMode == 2) {
                editText.setDropDownBackgroundDrawable(this.outlinedPopupBackground);
            } else if (boxBackgroundMode == 1) {
                editText.setDropDownBackgroundDrawable(this.filledPopupBackground);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRippleEffect(AutoCompleteTextView editText) {
        if (!isEditable(editText)) {
            int boxBackgroundMode = this.textInputLayout.getBoxBackgroundMode();
            MaterialShapeDrawable boxBackground = this.textInputLayout.getBoxBackground();
            int rippleColor = MaterialColors.getColor(editText, C0552R.attr.colorControlHighlight);
            int[][] states = {new int[]{16842919}, new int[0]};
            if (boxBackgroundMode == 2) {
                addRippleEffectOnOutlinedLayout(editText, rippleColor, states, boxBackground);
            } else if (boxBackgroundMode == 1) {
                addRippleEffectOnFilledLayout(editText, rippleColor, states, boxBackground);
            }
        }
    }

    /* JADX INFO: Multiple debug info for r3v2 android.graphics.drawable.Drawable[]: [D('editTextBackground' android.graphics.drawable.LayerDrawable), D('layers' android.graphics.drawable.Drawable[])] */
    private void addRippleEffectOnOutlinedLayout(AutoCompleteTextView editText, int rippleColor, int[][] states, MaterialShapeDrawable boxBackground) {
        LayerDrawable editTextBackground;
        int surfaceColor = MaterialColors.getColor(editText, C0552R.attr.colorSurface);
        MaterialShapeDrawable rippleBackground = new MaterialShapeDrawable(boxBackground.getShapeAppearanceModel());
        int pressedBackgroundColor = MaterialColors.layer(rippleColor, surfaceColor, 0.1f);
        rippleBackground.setFillColor(new ColorStateList(states, new int[]{pressedBackgroundColor, 0}));
        if (IS_LOLLIPOP) {
            rippleBackground.setTint(surfaceColor);
            ColorStateList rippleColorStateList = new ColorStateList(states, new int[]{pressedBackgroundColor, surfaceColor});
            MaterialShapeDrawable mask = new MaterialShapeDrawable(boxBackground.getShapeAppearanceModel());
            mask.setTint(-1);
            editTextBackground = new LayerDrawable(new Drawable[]{new RippleDrawable(rippleColorStateList, rippleBackground, mask), boxBackground});
        } else {
            editTextBackground = new LayerDrawable(new Drawable[]{rippleBackground, boxBackground});
        }
        ViewCompat.setBackground(editText, editTextBackground);
    }

    private void addRippleEffectOnFilledLayout(AutoCompleteTextView editText, int rippleColor, int[][] states, MaterialShapeDrawable boxBackground) {
        int boxBackgroundColor = this.textInputLayout.getBoxBackgroundColor();
        int[] colors = {MaterialColors.layer(rippleColor, boxBackgroundColor, 0.1f), boxBackgroundColor};
        if (IS_LOLLIPOP) {
            ViewCompat.setBackground(editText, new RippleDrawable(new ColorStateList(states, colors), boxBackground, boxBackground));
            return;
        }
        MaterialShapeDrawable rippleBackground = new MaterialShapeDrawable(boxBackground.getShapeAppearanceModel());
        rippleBackground.setFillColor(new ColorStateList(states, colors));
        LayerDrawable editTextBackground = new LayerDrawable(new Drawable[]{boxBackground, rippleBackground});
        int start = ViewCompat.getPaddingStart(editText);
        int top = editText.getPaddingTop();
        int end = ViewCompat.getPaddingEnd(editText);
        int bottom = editText.getPaddingBottom();
        ViewCompat.setBackground(editText, editTextBackground);
        ViewCompat.setPaddingRelative(editText, start, top, end, bottom);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUpDropdownShowHideBehavior(final AutoCompleteTextView editText) {
        editText.setOnTouchListener(new View.OnTouchListener() {
            /* class com.google.android.material.textfield.DropdownMenuEndIconDelegate.View$OnTouchListenerC07557 */

            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 1) {
                    if (DropdownMenuEndIconDelegate.this.isDropdownPopupActive()) {
                        DropdownMenuEndIconDelegate.this.dropdownPopupDirty = false;
                    }
                    DropdownMenuEndIconDelegate.this.showHideDropdown(editText);
                }
                return false;
            }
        });
        editText.setOnFocusChangeListener(this.onFocusChangeListener);
        if (IS_LOLLIPOP) {
            editText.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
                /* class com.google.android.material.textfield.DropdownMenuEndIconDelegate.C07568 */

                public void onDismiss() {
                    DropdownMenuEndIconDelegate.this.dropdownPopupDirty = true;
                    DropdownMenuEndIconDelegate.this.dropdownPopupActivatedAt = System.currentTimeMillis();
                    DropdownMenuEndIconDelegate.this.setEndIconChecked(false);
                }
            });
        }
    }

    private MaterialShapeDrawable getPopUpMaterialShapeDrawable(float topCornerRadius, float bottomCornerRadius, float elevation, int verticalPadding) {
        ShapeAppearanceModel shapeAppearanceModel = ShapeAppearanceModel.builder().setTopLeftCornerSize(topCornerRadius).setTopRightCornerSize(topCornerRadius).setBottomLeftCornerSize(bottomCornerRadius).setBottomRightCornerSize(bottomCornerRadius).build();
        MaterialShapeDrawable popupDrawable = MaterialShapeDrawable.createWithElevationOverlay(this.context, elevation);
        popupDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        popupDrawable.setPadding(0, verticalPadding, 0, verticalPadding);
        return popupDrawable;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isDropdownPopupActive() {
        long activeFor = System.currentTimeMillis() - this.dropdownPopupActivatedAt;
        return activeFor < 0 || activeFor > 300;
    }

    /* access modifiers changed from: private */
    public static AutoCompleteTextView castAutoCompleteTextViewOrThrow(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            return (AutoCompleteTextView) editText;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    /* access modifiers changed from: private */
    public static boolean isEditable(EditText editText) {
        return editText.getKeyListener() != null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEndIconChecked(boolean checked) {
        if (this.isEndIconChecked != checked) {
            this.isEndIconChecked = checked;
            this.fadeInAnim.cancel();
            this.fadeOutAnim.start();
        }
    }

    private void initAnimators() {
        this.fadeInAnim = getAlphaAnimator(67, 0.0f, 1.0f);
        ValueAnimator alphaAnimator = getAlphaAnimator(50, 1.0f, 0.0f);
        this.fadeOutAnim = alphaAnimator;
        alphaAnimator.addListener(new AnimatorListenerAdapter() {
            /* class com.google.android.material.textfield.DropdownMenuEndIconDelegate.C07579 */

            public void onAnimationEnd(Animator animation) {
                DropdownMenuEndIconDelegate.this.endIconView.setChecked(DropdownMenuEndIconDelegate.this.isEndIconChecked);
                DropdownMenuEndIconDelegate.this.fadeInAnim.start();
            }
        });
    }

    private ValueAnimator getAlphaAnimator(int duration, float... values) {
        ValueAnimator animator = ValueAnimator.ofFloat(values);
        animator.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        animator.setDuration((long) duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /* class com.google.android.material.textfield.DropdownMenuEndIconDelegate.C074810 */

            public void onAnimationUpdate(ValueAnimator animation) {
                DropdownMenuEndIconDelegate.this.endIconView.setAlpha(((Float) animation.getAnimatedValue()).floatValue());
            }
        });
        return animator;
    }
}
