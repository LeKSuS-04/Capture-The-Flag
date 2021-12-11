package com.google.android.material.textfield;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.DrawableUtils;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.text.BidiFormatter;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.C0552R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class TextInputLayout extends LinearLayout {
    public static final int BOX_BACKGROUND_FILLED = 1;
    public static final int BOX_BACKGROUND_NONE = 0;
    public static final int BOX_BACKGROUND_OUTLINE = 2;
    private static final int DEF_STYLE_RES = C0552R.style.Widget_Design_TextInputLayout;
    public static final int END_ICON_CLEAR_TEXT = 2;
    public static final int END_ICON_CUSTOM = -1;
    public static final int END_ICON_DROPDOWN_MENU = 3;
    public static final int END_ICON_NONE = 0;
    public static final int END_ICON_PASSWORD_TOGGLE = 1;
    private static final int INVALID_MAX_LENGTH = -1;
    private static final int LABEL_SCALE_ANIMATION_DURATION = 167;
    private static final String LOG_TAG = "TextInputLayout";
    private static final int NO_WIDTH = -1;
    private ValueAnimator animator;
    private MaterialShapeDrawable boxBackground;
    private int boxBackgroundColor;
    private int boxBackgroundMode;
    private int boxCollapsedPaddingTopPx;
    private int boxLabelCutoutHeight;
    private final int boxLabelCutoutPaddingPx;
    private int boxStrokeColor;
    private int boxStrokeWidthDefaultPx;
    private int boxStrokeWidthFocusedPx;
    private int boxStrokeWidthPx;
    private MaterialShapeDrawable boxUnderline;
    final CollapsingTextHelper collapsingTextHelper;
    boolean counterEnabled;
    private int counterMaxLength;
    private int counterOverflowTextAppearance;
    private ColorStateList counterOverflowTextColor;
    private boolean counterOverflowed;
    private int counterTextAppearance;
    private ColorStateList counterTextColor;
    private TextView counterView;
    private int defaultFilledBackgroundColor;
    private ColorStateList defaultHintTextColor;
    private int defaultStrokeColor;
    private int disabledColor;
    private int disabledFilledBackgroundColor;
    EditText editText;
    private final LinkedHashSet<OnEditTextAttachedListener> editTextAttachedListeners;
    private Drawable endDummyDrawable;
    private int endDummyDrawableWidth;
    private final LinkedHashSet<OnEndIconChangedListener> endIconChangedListeners;
    private final SparseArray<EndIconDelegate> endIconDelegates;
    private final FrameLayout endIconFrame;
    private int endIconMode;
    private View.OnLongClickListener endIconOnLongClickListener;
    private ColorStateList endIconTintList;
    private PorterDuff.Mode endIconTintMode;
    private final CheckableImageButton endIconView;
    private final LinearLayout endLayout;
    private View.OnLongClickListener errorIconOnLongClickListener;
    private ColorStateList errorIconTintList;
    private final CheckableImageButton errorIconView;
    private boolean expandedHintEnabled;
    private int focusedFilledBackgroundColor;
    private int focusedStrokeColor;
    private ColorStateList focusedTextColor;
    private boolean hasEndIconTintList;
    private boolean hasEndIconTintMode;
    private boolean hasStartIconTintList;
    private boolean hasStartIconTintMode;
    private CharSequence hint;
    private boolean hintAnimationEnabled;
    private boolean hintEnabled;
    private boolean hintExpanded;
    private int hoveredFilledBackgroundColor;
    private int hoveredStrokeColor;
    private boolean inDrawableStateChanged;
    private final IndicatorViewController indicatorViewController;
    private final FrameLayout inputFrame;
    private boolean isProvidingHint;
    private int maxWidth;
    private int minWidth;
    private Drawable originalEditTextEndDrawable;
    private CharSequence originalHint;
    private boolean placeholderEnabled;
    private CharSequence placeholderText;
    private int placeholderTextAppearance;
    private ColorStateList placeholderTextColor;
    private TextView placeholderTextView;
    private CharSequence prefixText;
    private final TextView prefixTextView;
    private boolean restoringSavedState;
    private ShapeAppearanceModel shapeAppearanceModel;
    private Drawable startDummyDrawable;
    private int startDummyDrawableWidth;
    private View.OnLongClickListener startIconOnLongClickListener;
    private ColorStateList startIconTintList;
    private PorterDuff.Mode startIconTintMode;
    private final CheckableImageButton startIconView;
    private final LinearLayout startLayout;
    private ColorStateList strokeErrorColor;
    private CharSequence suffixText;
    private final TextView suffixTextView;
    private final Rect tmpBoundsRect;
    private final Rect tmpRect;
    private final RectF tmpRectF;
    private Typeface typeface;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BoxBackgroundMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EndIconMode {
    }

    public interface OnEditTextAttachedListener {
        void onEditTextAttached(TextInputLayout textInputLayout);
    }

    public interface OnEndIconChangedListener {
        void onEndIconChanged(TextInputLayout textInputLayout, int i);
    }

    public TextInputLayout(Context context) {
        this(context, null);
    }

    public TextInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, C0552R.attr.textInputStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    public TextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(MaterialThemeOverlay.wrap(context, attrs, defStyleAttr, r9), attrs, defStyleAttr);
        int i;
        int i2;
        int i3 = DEF_STYLE_RES;
        this.minWidth = -1;
        this.maxWidth = -1;
        this.indicatorViewController = new IndicatorViewController(this);
        this.tmpRect = new Rect();
        this.tmpBoundsRect = new Rect();
        this.tmpRectF = new RectF();
        this.editTextAttachedListeners = new LinkedHashSet<>();
        this.endIconMode = 0;
        SparseArray<EndIconDelegate> sparseArray = new SparseArray<>();
        this.endIconDelegates = sparseArray;
        this.endIconChangedListeners = new LinkedHashSet<>();
        CollapsingTextHelper collapsingTextHelper2 = new CollapsingTextHelper(this);
        this.collapsingTextHelper = collapsingTextHelper2;
        Context context2 = getContext();
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        FrameLayout frameLayout = new FrameLayout(context2);
        this.inputFrame = frameLayout;
        frameLayout.setAddStatesFromChildren(true);
        addView(frameLayout);
        LinearLayout linearLayout = new LinearLayout(context2);
        this.startLayout = linearLayout;
        linearLayout.setOrientation(0);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, GravityCompat.START));
        frameLayout.addView(linearLayout);
        LinearLayout linearLayout2 = new LinearLayout(context2);
        this.endLayout = linearLayout2;
        linearLayout2.setOrientation(0);
        linearLayout2.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, GravityCompat.END));
        frameLayout.addView(linearLayout2);
        FrameLayout frameLayout2 = new FrameLayout(context2);
        this.endIconFrame = frameLayout2;
        frameLayout2.setLayoutParams(new FrameLayout.LayoutParams(-2, -1));
        collapsingTextHelper2.setTextSizeInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        collapsingTextHelper2.setPositionInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        collapsingTextHelper2.setCollapsedTextGravity(BadgeDrawable.TOP_START);
        TintTypedArray a = ThemeEnforcement.obtainTintedStyledAttributes(context2, attrs, C0552R.styleable.TextInputLayout, defStyleAttr, i3, C0552R.styleable.TextInputLayout_counterTextAppearance, C0552R.styleable.TextInputLayout_counterOverflowTextAppearance, C0552R.styleable.TextInputLayout_errorTextAppearance, C0552R.styleable.TextInputLayout_helperTextTextAppearance, C0552R.styleable.TextInputLayout_hintTextAppearance);
        this.hintEnabled = a.getBoolean(C0552R.styleable.TextInputLayout_hintEnabled, true);
        setHint(a.getText(C0552R.styleable.TextInputLayout_android_hint));
        this.hintAnimationEnabled = a.getBoolean(C0552R.styleable.TextInputLayout_hintAnimationEnabled, true);
        this.expandedHintEnabled = a.getBoolean(C0552R.styleable.TextInputLayout_expandedHintEnabled, true);
        if (a.hasValue(C0552R.styleable.TextInputLayout_android_minWidth)) {
            setMinWidth(a.getDimensionPixelSize(C0552R.styleable.TextInputLayout_android_minWidth, -1));
        }
        if (a.hasValue(C0552R.styleable.TextInputLayout_android_maxWidth)) {
            setMaxWidth(a.getDimensionPixelSize(C0552R.styleable.TextInputLayout_android_maxWidth, -1));
        }
        this.shapeAppearanceModel = ShapeAppearanceModel.builder(context2, attrs, defStyleAttr, i3).build();
        this.boxLabelCutoutPaddingPx = context2.getResources().getDimensionPixelOffset(C0552R.dimen.mtrl_textinput_box_label_cutout_padding);
        this.boxCollapsedPaddingTopPx = a.getDimensionPixelOffset(C0552R.styleable.TextInputLayout_boxCollapsedPaddingTop, 0);
        this.boxStrokeWidthDefaultPx = a.getDimensionPixelSize(C0552R.styleable.TextInputLayout_boxStrokeWidth, context2.getResources().getDimensionPixelSize(C0552R.dimen.mtrl_textinput_box_stroke_width_default));
        this.boxStrokeWidthFocusedPx = a.getDimensionPixelSize(C0552R.styleable.TextInputLayout_boxStrokeWidthFocused, context2.getResources().getDimensionPixelSize(C0552R.dimen.mtrl_textinput_box_stroke_width_focused));
        this.boxStrokeWidthPx = this.boxStrokeWidthDefaultPx;
        float boxCornerRadiusTopStart = a.getDimension(C0552R.styleable.TextInputLayout_boxCornerRadiusTopStart, -1.0f);
        float boxCornerRadiusTopEnd = a.getDimension(C0552R.styleable.TextInputLayout_boxCornerRadiusTopEnd, -1.0f);
        float boxCornerRadiusBottomEnd = a.getDimension(C0552R.styleable.TextInputLayout_boxCornerRadiusBottomEnd, -1.0f);
        float boxCornerRadiusBottomStart = a.getDimension(C0552R.styleable.TextInputLayout_boxCornerRadiusBottomStart, -1.0f);
        ShapeAppearanceModel.Builder shapeBuilder = this.shapeAppearanceModel.toBuilder();
        if (boxCornerRadiusTopStart >= 0.0f) {
            shapeBuilder.setTopLeftCornerSize(boxCornerRadiusTopStart);
        }
        if (boxCornerRadiusTopEnd >= 0.0f) {
            shapeBuilder.setTopRightCornerSize(boxCornerRadiusTopEnd);
        }
        if (boxCornerRadiusBottomEnd >= 0.0f) {
            shapeBuilder.setBottomRightCornerSize(boxCornerRadiusBottomEnd);
        }
        if (boxCornerRadiusBottomStart >= 0.0f) {
            shapeBuilder.setBottomLeftCornerSize(boxCornerRadiusBottomStart);
        }
        this.shapeAppearanceModel = shapeBuilder.build();
        ColorStateList filledBackgroundColorStateList = MaterialResources.getColorStateList(context2, a, C0552R.styleable.TextInputLayout_boxBackgroundColor);
        if (filledBackgroundColorStateList != null) {
            int defaultColor = filledBackgroundColorStateList.getDefaultColor();
            this.defaultFilledBackgroundColor = defaultColor;
            this.boxBackgroundColor = defaultColor;
            if (filledBackgroundColorStateList.isStateful()) {
                this.disabledFilledBackgroundColor = filledBackgroundColorStateList.getColorForState(new int[]{-16842910}, -1);
                this.focusedFilledBackgroundColor = filledBackgroundColorStateList.getColorForState(new int[]{16842908, 16842910}, -1);
                this.hoveredFilledBackgroundColor = filledBackgroundColorStateList.getColorForState(new int[]{16843623, 16842910}, -1);
            } else {
                this.focusedFilledBackgroundColor = this.defaultFilledBackgroundColor;
                ColorStateList mtrlFilledBackgroundColorStateList = AppCompatResources.getColorStateList(context2, C0552R.C0553color.mtrl_filled_background_color);
                this.disabledFilledBackgroundColor = mtrlFilledBackgroundColorStateList.getColorForState(new int[]{-16842910}, -1);
                this.hoveredFilledBackgroundColor = mtrlFilledBackgroundColorStateList.getColorForState(new int[]{16843623}, -1);
            }
        } else {
            this.boxBackgroundColor = 0;
            this.defaultFilledBackgroundColor = 0;
            this.disabledFilledBackgroundColor = 0;
            this.focusedFilledBackgroundColor = 0;
            this.hoveredFilledBackgroundColor = 0;
        }
        if (a.hasValue(C0552R.styleable.TextInputLayout_android_textColorHint)) {
            ColorStateList colorStateList = a.getColorStateList(C0552R.styleable.TextInputLayout_android_textColorHint);
            this.focusedTextColor = colorStateList;
            this.defaultHintTextColor = colorStateList;
        }
        ColorStateList boxStrokeColorStateList = MaterialResources.getColorStateList(context2, a, C0552R.styleable.TextInputLayout_boxStrokeColor);
        this.focusedStrokeColor = a.getColor(C0552R.styleable.TextInputLayout_boxStrokeColor, 0);
        this.defaultStrokeColor = ContextCompat.getColor(context2, C0552R.C0553color.mtrl_textinput_default_box_stroke_color);
        this.disabledColor = ContextCompat.getColor(context2, C0552R.C0553color.mtrl_textinput_disabled_color);
        this.hoveredStrokeColor = ContextCompat.getColor(context2, C0552R.C0553color.mtrl_textinput_hovered_box_stroke_color);
        if (boxStrokeColorStateList != null) {
            setBoxStrokeColorStateList(boxStrokeColorStateList);
        }
        if (a.hasValue(C0552R.styleable.TextInputLayout_boxStrokeErrorColor)) {
            setBoxStrokeErrorColor(MaterialResources.getColorStateList(context2, a, C0552R.styleable.TextInputLayout_boxStrokeErrorColor));
        }
        if (a.getResourceId(C0552R.styleable.TextInputLayout_hintTextAppearance, -1) != -1) {
            i = 0;
            setHintTextAppearance(a.getResourceId(C0552R.styleable.TextInputLayout_hintTextAppearance, 0));
        } else {
            i = 0;
        }
        int errorTextAppearance = a.getResourceId(C0552R.styleable.TextInputLayout_errorTextAppearance, i);
        CharSequence errorContentDescription = a.getText(C0552R.styleable.TextInputLayout_errorContentDescription);
        boolean errorEnabled = a.getBoolean(C0552R.styleable.TextInputLayout_errorEnabled, false);
        CheckableImageButton checkableImageButton = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(C0552R.layout.design_text_input_end_icon, (ViewGroup) linearLayout2, false);
        this.errorIconView = checkableImageButton;
        checkableImageButton.setId(C0552R.C0555id.text_input_error_icon);
        checkableImageButton.setVisibility(8);
        if (MaterialResources.isFontScaleAtLeast1_3(context2)) {
            MarginLayoutParamsCompat.setMarginStart((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams(), 0);
        }
        if (a.hasValue(C0552R.styleable.TextInputLayout_errorIconDrawable)) {
            setErrorIconDrawable(a.getDrawable(C0552R.styleable.TextInputLayout_errorIconDrawable));
        }
        if (a.hasValue(C0552R.styleable.TextInputLayout_errorIconTint)) {
            setErrorIconTintList(MaterialResources.getColorStateList(context2, a, C0552R.styleable.TextInputLayout_errorIconTint));
        }
        if (a.hasValue(C0552R.styleable.TextInputLayout_errorIconTintMode)) {
            setErrorIconTintMode(ViewUtils.parseTintMode(a.getInt(C0552R.styleable.TextInputLayout_errorIconTintMode, -1), null));
        }
        checkableImageButton.setContentDescription(getResources().getText(C0552R.string.error_icon_content_description));
        ViewCompat.setImportantForAccessibility(checkableImageButton, 2);
        checkableImageButton.setClickable(false);
        checkableImageButton.setPressable(false);
        checkableImageButton.setFocusable(false);
        int helperTextTextAppearance = a.getResourceId(C0552R.styleable.TextInputLayout_helperTextTextAppearance, 0);
        boolean helperTextEnabled = a.getBoolean(C0552R.styleable.TextInputLayout_helperTextEnabled, false);
        CharSequence helperText = a.getText(C0552R.styleable.TextInputLayout_helperText);
        int placeholderTextAppearance2 = a.getResourceId(C0552R.styleable.TextInputLayout_placeholderTextAppearance, 0);
        CharSequence placeholderText2 = a.getText(C0552R.styleable.TextInputLayout_placeholderText);
        int prefixTextAppearance = a.getResourceId(C0552R.styleable.TextInputLayout_prefixTextAppearance, 0);
        CharSequence prefixText2 = a.getText(C0552R.styleable.TextInputLayout_prefixText);
        int suffixTextAppearance = a.getResourceId(C0552R.styleable.TextInputLayout_suffixTextAppearance, 0);
        CharSequence suffixText2 = a.getText(C0552R.styleable.TextInputLayout_suffixText);
        boolean counterEnabled2 = a.getBoolean(C0552R.styleable.TextInputLayout_counterEnabled, false);
        setCounterMaxLength(a.getInt(C0552R.styleable.TextInputLayout_counterMaxLength, -1));
        this.counterTextAppearance = a.getResourceId(C0552R.styleable.TextInputLayout_counterTextAppearance, 0);
        this.counterOverflowTextAppearance = a.getResourceId(C0552R.styleable.TextInputLayout_counterOverflowTextAppearance, 0);
        CheckableImageButton checkableImageButton2 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(C0552R.layout.design_text_input_start_icon, (ViewGroup) linearLayout, false);
        this.startIconView = checkableImageButton2;
        checkableImageButton2.setVisibility(8);
        if (MaterialResources.isFontScaleAtLeast1_3(context2)) {
            MarginLayoutParamsCompat.setMarginEnd((ViewGroup.MarginLayoutParams) checkableImageButton2.getLayoutParams(), 0);
        }
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        if (a.hasValue(C0552R.styleable.TextInputLayout_startIconDrawable)) {
            setStartIconDrawable(a.getDrawable(C0552R.styleable.TextInputLayout_startIconDrawable));
            if (a.hasValue(C0552R.styleable.TextInputLayout_startIconContentDescription)) {
                setStartIconContentDescription(a.getText(C0552R.styleable.TextInputLayout_startIconContentDescription));
            }
            setStartIconCheckable(a.getBoolean(C0552R.styleable.TextInputLayout_startIconCheckable, true));
        }
        if (a.hasValue(C0552R.styleable.TextInputLayout_startIconTint)) {
            setStartIconTintList(MaterialResources.getColorStateList(context2, a, C0552R.styleable.TextInputLayout_startIconTint));
        }
        if (a.hasValue(C0552R.styleable.TextInputLayout_startIconTintMode)) {
            setStartIconTintMode(ViewUtils.parseTintMode(a.getInt(C0552R.styleable.TextInputLayout_startIconTintMode, -1), null));
        }
        setBoxBackgroundMode(a.getInt(C0552R.styleable.TextInputLayout_boxBackgroundMode, 0));
        CheckableImageButton checkableImageButton3 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(C0552R.layout.design_text_input_end_icon, (ViewGroup) frameLayout2, false);
        this.endIconView = checkableImageButton3;
        frameLayout2.addView(checkableImageButton3);
        checkableImageButton3.setVisibility(8);
        if (MaterialResources.isFontScaleAtLeast1_3(context2)) {
            i2 = 0;
            MarginLayoutParamsCompat.setMarginStart((ViewGroup.MarginLayoutParams) checkableImageButton3.getLayoutParams(), 0);
        } else {
            i2 = 0;
        }
        sparseArray.append(-1, new CustomEndIconDelegate(this));
        sparseArray.append(i2, new NoEndIconDelegate(this));
        sparseArray.append(1, new PasswordToggleEndIconDelegate(this));
        sparseArray.append(2, new ClearTextEndIconDelegate(this));
        sparseArray.append(3, new DropdownMenuEndIconDelegate(this));
        if (a.hasValue(C0552R.styleable.TextInputLayout_endIconMode)) {
            setEndIconMode(a.getInt(C0552R.styleable.TextInputLayout_endIconMode, 0));
            if (a.hasValue(C0552R.styleable.TextInputLayout_endIconDrawable)) {
                setEndIconDrawable(a.getDrawable(C0552R.styleable.TextInputLayout_endIconDrawable));
            }
            if (a.hasValue(C0552R.styleable.TextInputLayout_endIconContentDescription)) {
                setEndIconContentDescription(a.getText(C0552R.styleable.TextInputLayout_endIconContentDescription));
            }
            setEndIconCheckable(a.getBoolean(C0552R.styleable.TextInputLayout_endIconCheckable, true));
        } else if (a.hasValue(C0552R.styleable.TextInputLayout_passwordToggleEnabled)) {
            setEndIconMode(a.getBoolean(C0552R.styleable.TextInputLayout_passwordToggleEnabled, false) ? 1 : 0);
            setEndIconDrawable(a.getDrawable(C0552R.styleable.TextInputLayout_passwordToggleDrawable));
            setEndIconContentDescription(a.getText(C0552R.styleable.TextInputLayout_passwordToggleContentDescription));
            if (a.hasValue(C0552R.styleable.TextInputLayout_passwordToggleTint)) {
                setEndIconTintList(MaterialResources.getColorStateList(context2, a, C0552R.styleable.TextInputLayout_passwordToggleTint));
            }
            if (a.hasValue(C0552R.styleable.TextInputLayout_passwordToggleTintMode)) {
                setEndIconTintMode(ViewUtils.parseTintMode(a.getInt(C0552R.styleable.TextInputLayout_passwordToggleTintMode, -1), null));
            }
        }
        if (!a.hasValue(C0552R.styleable.TextInputLayout_passwordToggleEnabled)) {
            if (a.hasValue(C0552R.styleable.TextInputLayout_endIconTint)) {
                setEndIconTintList(MaterialResources.getColorStateList(context2, a, C0552R.styleable.TextInputLayout_endIconTint));
            }
            if (a.hasValue(C0552R.styleable.TextInputLayout_endIconTintMode)) {
                setEndIconTintMode(ViewUtils.parseTintMode(a.getInt(C0552R.styleable.TextInputLayout_endIconTintMode, -1), null));
            }
        }
        AppCompatTextView appCompatTextView = new AppCompatTextView(context2);
        this.prefixTextView = appCompatTextView;
        appCompatTextView.setId(C0552R.C0555id.textinput_prefix_text);
        appCompatTextView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        ViewCompat.setAccessibilityLiveRegion(appCompatTextView, 1);
        linearLayout.addView(checkableImageButton2);
        linearLayout.addView(appCompatTextView);
        AppCompatTextView appCompatTextView2 = new AppCompatTextView(context2);
        this.suffixTextView = appCompatTextView2;
        appCompatTextView2.setId(C0552R.C0555id.textinput_suffix_text);
        appCompatTextView2.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 80));
        ViewCompat.setAccessibilityLiveRegion(appCompatTextView2, 1);
        linearLayout2.addView(appCompatTextView2);
        linearLayout2.addView(checkableImageButton);
        linearLayout2.addView(frameLayout2);
        setHelperTextEnabled(helperTextEnabled);
        setHelperText(helperText);
        setHelperTextTextAppearance(helperTextTextAppearance);
        setErrorEnabled(errorEnabled);
        setErrorTextAppearance(errorTextAppearance);
        setErrorContentDescription(errorContentDescription);
        setCounterTextAppearance(this.counterTextAppearance);
        setCounterOverflowTextAppearance(this.counterOverflowTextAppearance);
        setPlaceholderText(placeholderText2);
        setPlaceholderTextAppearance(placeholderTextAppearance2);
        setPrefixText(prefixText2);
        setPrefixTextAppearance(prefixTextAppearance);
        setSuffixText(suffixText2);
        setSuffixTextAppearance(suffixTextAppearance);
        if (a.hasValue(C0552R.styleable.TextInputLayout_errorTextColor)) {
            setErrorTextColor(a.getColorStateList(C0552R.styleable.TextInputLayout_errorTextColor));
        }
        if (a.hasValue(C0552R.styleable.TextInputLayout_helperTextTextColor)) {
            setHelperTextColor(a.getColorStateList(C0552R.styleable.TextInputLayout_helperTextTextColor));
        }
        if (a.hasValue(C0552R.styleable.TextInputLayout_hintTextColor)) {
            setHintTextColor(a.getColorStateList(C0552R.styleable.TextInputLayout_hintTextColor));
        }
        if (a.hasValue(C0552R.styleable.TextInputLayout_counterTextColor)) {
            setCounterTextColor(a.getColorStateList(C0552R.styleable.TextInputLayout_counterTextColor));
        }
        if (a.hasValue(C0552R.styleable.TextInputLayout_counterOverflowTextColor)) {
            setCounterOverflowTextColor(a.getColorStateList(C0552R.styleable.TextInputLayout_counterOverflowTextColor));
        }
        if (a.hasValue(C0552R.styleable.TextInputLayout_placeholderTextColor)) {
            setPlaceholderTextColor(a.getColorStateList(C0552R.styleable.TextInputLayout_placeholderTextColor));
        }
        if (a.hasValue(C0552R.styleable.TextInputLayout_prefixTextColor)) {
            setPrefixTextColor(a.getColorStateList(C0552R.styleable.TextInputLayout_prefixTextColor));
        }
        if (a.hasValue(C0552R.styleable.TextInputLayout_suffixTextColor)) {
            setSuffixTextColor(a.getColorStateList(C0552R.styleable.TextInputLayout_suffixTextColor));
        }
        setCounterEnabled(counterEnabled2);
        setEnabled(a.getBoolean(C0552R.styleable.TextInputLayout_android_enabled, true));
        a.recycle();
        ViewCompat.setImportantForAccessibility(this, 2);
        if (Build.VERSION.SDK_INT >= 26) {
            ViewCompat.setImportantForAutofill(this, 1);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof EditText) {
            FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(params);
            flp.gravity = (flp.gravity & -113) | 16;
            this.inputFrame.addView(child, flp);
            this.inputFrame.setLayoutParams(params);
            updateInputLayoutMargins();
            setEditText((EditText) child);
            return;
        }
        super.addView(child, index, params);
    }

    /* access modifiers changed from: package-private */
    public MaterialShapeDrawable getBoxBackground() {
        int i = this.boxBackgroundMode;
        if (i == 1 || i == 2) {
            return this.boxBackground;
        }
        throw new IllegalStateException();
    }

    public void setBoxBackgroundMode(int boxBackgroundMode2) {
        if (boxBackgroundMode2 != this.boxBackgroundMode) {
            this.boxBackgroundMode = boxBackgroundMode2;
            if (this.editText != null) {
                onApplyBoxBackgroundMode();
            }
        }
    }

    public int getBoxBackgroundMode() {
        return this.boxBackgroundMode;
    }

    private void onApplyBoxBackgroundMode() {
        assignBoxBackgroundByMode();
        setEditTextBoxBackground();
        updateTextInputBoxState();
        updateBoxCollapsedPaddingTop();
        adjustFilledEditTextPaddingForLargeFont();
        if (this.boxBackgroundMode != 0) {
            updateInputLayoutMargins();
        }
    }

    private void assignBoxBackgroundByMode() {
        switch (this.boxBackgroundMode) {
            case 0:
                this.boxBackground = null;
                this.boxUnderline = null;
                return;
            case 1:
                this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
                this.boxUnderline = new MaterialShapeDrawable();
                return;
            case 2:
                if (!this.hintEnabled || (this.boxBackground instanceof CutoutDrawable)) {
                    this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
                } else {
                    this.boxBackground = new CutoutDrawable(this.shapeAppearanceModel);
                }
                this.boxUnderline = null;
                return;
            default:
                throw new IllegalArgumentException(this.boxBackgroundMode + " is illegal; only @BoxBackgroundMode constants are supported.");
        }
    }

    private void setEditTextBoxBackground() {
        if (shouldUseEditTextBackgroundForBoxBackground()) {
            ViewCompat.setBackground(this.editText, this.boxBackground);
        }
    }

    private boolean shouldUseEditTextBackgroundForBoxBackground() {
        EditText editText2 = this.editText;
        return (editText2 == null || this.boxBackground == null || editText2.getBackground() != null || this.boxBackgroundMode == 0) ? false : true;
    }

    private void updateBoxCollapsedPaddingTop() {
        if (this.boxBackgroundMode != 1) {
            return;
        }
        if (MaterialResources.isFontScaleAtLeast2_0(getContext())) {
            this.boxCollapsedPaddingTopPx = getResources().getDimensionPixelSize(C0552R.dimen.material_font_2_0_box_collapsed_padding_top);
        } else if (MaterialResources.isFontScaleAtLeast1_3(getContext())) {
            this.boxCollapsedPaddingTopPx = getResources().getDimensionPixelSize(C0552R.dimen.material_font_1_3_box_collapsed_padding_top);
        }
    }

    private void adjustFilledEditTextPaddingForLargeFont() {
        if (this.editText != null && this.boxBackgroundMode == 1) {
            if (MaterialResources.isFontScaleAtLeast2_0(getContext())) {
                EditText editText2 = this.editText;
                ViewCompat.setPaddingRelative(editText2, ViewCompat.getPaddingStart(editText2), getResources().getDimensionPixelSize(C0552R.dimen.material_filled_edittext_font_2_0_padding_top), ViewCompat.getPaddingEnd(this.editText), getResources().getDimensionPixelSize(C0552R.dimen.material_filled_edittext_font_2_0_padding_bottom));
            } else if (MaterialResources.isFontScaleAtLeast1_3(getContext())) {
                EditText editText3 = this.editText;
                ViewCompat.setPaddingRelative(editText3, ViewCompat.getPaddingStart(editText3), getResources().getDimensionPixelSize(C0552R.dimen.material_filled_edittext_font_1_3_padding_top), ViewCompat.getPaddingEnd(this.editText), getResources().getDimensionPixelSize(C0552R.dimen.material_filled_edittext_font_1_3_padding_bottom));
            }
        }
    }

    public void setBoxStrokeWidthResource(int boxStrokeWidthResId) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(boxStrokeWidthResId));
    }

    public void setBoxStrokeWidth(int boxStrokeWidth) {
        this.boxStrokeWidthDefaultPx = boxStrokeWidth;
        updateTextInputBoxState();
    }

    public int getBoxStrokeWidth() {
        return this.boxStrokeWidthDefaultPx;
    }

    public void setBoxStrokeWidthFocusedResource(int boxStrokeWidthFocusedResId) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(boxStrokeWidthFocusedResId));
    }

    public void setBoxStrokeWidthFocused(int boxStrokeWidthFocused) {
        this.boxStrokeWidthFocusedPx = boxStrokeWidthFocused;
        updateTextInputBoxState();
    }

    public int getBoxStrokeWidthFocused() {
        return this.boxStrokeWidthFocusedPx;
    }

    public void setBoxStrokeColor(int boxStrokeColor2) {
        if (this.focusedStrokeColor != boxStrokeColor2) {
            this.focusedStrokeColor = boxStrokeColor2;
            updateTextInputBoxState();
        }
    }

    public int getBoxStrokeColor() {
        return this.focusedStrokeColor;
    }

    public void setBoxStrokeColorStateList(ColorStateList boxStrokeColorStateList) {
        if (boxStrokeColorStateList.isStateful()) {
            this.defaultStrokeColor = boxStrokeColorStateList.getDefaultColor();
            this.disabledColor = boxStrokeColorStateList.getColorForState(new int[]{-16842910}, -1);
            this.hoveredStrokeColor = boxStrokeColorStateList.getColorForState(new int[]{16843623, 16842910}, -1);
            this.focusedStrokeColor = boxStrokeColorStateList.getColorForState(new int[]{16842908, 16842910}, -1);
        } else if (this.focusedStrokeColor != boxStrokeColorStateList.getDefaultColor()) {
            this.focusedStrokeColor = boxStrokeColorStateList.getDefaultColor();
        }
        updateTextInputBoxState();
    }

    public void setBoxStrokeErrorColor(ColorStateList strokeErrorColor2) {
        if (this.strokeErrorColor != strokeErrorColor2) {
            this.strokeErrorColor = strokeErrorColor2;
            updateTextInputBoxState();
        }
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.strokeErrorColor;
    }

    public void setBoxBackgroundColorResource(int boxBackgroundColorId) {
        setBoxBackgroundColor(ContextCompat.getColor(getContext(), boxBackgroundColorId));
    }

    public void setBoxBackgroundColor(int boxBackgroundColor2) {
        if (this.boxBackgroundColor != boxBackgroundColor2) {
            this.boxBackgroundColor = boxBackgroundColor2;
            this.defaultFilledBackgroundColor = boxBackgroundColor2;
            this.focusedFilledBackgroundColor = boxBackgroundColor2;
            this.hoveredFilledBackgroundColor = boxBackgroundColor2;
            applyBoxAttributes();
        }
    }

    public void setBoxBackgroundColorStateList(ColorStateList boxBackgroundColorStateList) {
        int defaultColor = boxBackgroundColorStateList.getDefaultColor();
        this.defaultFilledBackgroundColor = defaultColor;
        this.boxBackgroundColor = defaultColor;
        this.disabledFilledBackgroundColor = boxBackgroundColorStateList.getColorForState(new int[]{-16842910}, -1);
        this.focusedFilledBackgroundColor = boxBackgroundColorStateList.getColorForState(new int[]{16842908, 16842910}, -1);
        this.hoveredFilledBackgroundColor = boxBackgroundColorStateList.getColorForState(new int[]{16843623, 16842910}, -1);
        applyBoxAttributes();
    }

    public int getBoxBackgroundColor() {
        return this.boxBackgroundColor;
    }

    public void setBoxCornerRadiiResources(int boxCornerRadiusTopStartId, int boxCornerRadiusTopEndId, int boxCornerRadiusBottomEndId, int boxCornerRadiusBottomStartId) {
        setBoxCornerRadii(getContext().getResources().getDimension(boxCornerRadiusTopStartId), getContext().getResources().getDimension(boxCornerRadiusTopEndId), getContext().getResources().getDimension(boxCornerRadiusBottomStartId), getContext().getResources().getDimension(boxCornerRadiusBottomEndId));
    }

    public void setBoxCornerRadii(float boxCornerRadiusTopStart, float boxCornerRadiusTopEnd, float boxCornerRadiusBottomStart, float boxCornerRadiusBottomEnd) {
        MaterialShapeDrawable materialShapeDrawable = this.boxBackground;
        if (materialShapeDrawable == null || materialShapeDrawable.getTopLeftCornerResolvedSize() != boxCornerRadiusTopStart || this.boxBackground.getTopRightCornerResolvedSize() != boxCornerRadiusTopEnd || this.boxBackground.getBottomRightCornerResolvedSize() != boxCornerRadiusBottomEnd || this.boxBackground.getBottomLeftCornerResolvedSize() != boxCornerRadiusBottomStart) {
            this.shapeAppearanceModel = this.shapeAppearanceModel.toBuilder().setTopLeftCornerSize(boxCornerRadiusTopStart).setTopRightCornerSize(boxCornerRadiusTopEnd).setBottomRightCornerSize(boxCornerRadiusBottomEnd).setBottomLeftCornerSize(boxCornerRadiusBottomStart).build();
            applyBoxAttributes();
        }
    }

    public float getBoxCornerRadiusTopStart() {
        return this.boxBackground.getTopLeftCornerResolvedSize();
    }

    public float getBoxCornerRadiusTopEnd() {
        return this.boxBackground.getTopRightCornerResolvedSize();
    }

    public float getBoxCornerRadiusBottomEnd() {
        return this.boxBackground.getBottomLeftCornerResolvedSize();
    }

    public float getBoxCornerRadiusBottomStart() {
        return this.boxBackground.getBottomRightCornerResolvedSize();
    }

    public void setTypeface(Typeface typeface2) {
        if (typeface2 != this.typeface) {
            this.typeface = typeface2;
            this.collapsingTextHelper.setTypefaces(typeface2);
            this.indicatorViewController.setTypefaces(typeface2);
            TextView textView = this.counterView;
            if (textView != null) {
                textView.setTypeface(typeface2);
            }
        }
    }

    public Typeface getTypeface() {
        return this.typeface;
    }

    public void dispatchProvideAutofillStructure(ViewStructure structure, int flags) {
        EditText editText2 = this.editText;
        if (editText2 == null) {
            super.dispatchProvideAutofillStructure(structure, flags);
        } else if (this.originalHint != null) {
            boolean wasProvidingHint = this.isProvidingHint;
            this.isProvidingHint = false;
            CharSequence hint2 = editText2.getHint();
            this.editText.setHint(this.originalHint);
            try {
                super.dispatchProvideAutofillStructure(structure, flags);
            } finally {
                this.editText.setHint(hint2);
                this.isProvidingHint = wasProvidingHint;
            }
        } else {
            structure.setAutofillId(getAutofillId());
            onProvideAutofillStructure(structure, flags);
            onProvideAutofillVirtualStructure(structure, flags);
            structure.setChildCount(this.inputFrame.getChildCount());
            for (int i = 0; i < this.inputFrame.getChildCount(); i++) {
                View child = this.inputFrame.getChildAt(i);
                ViewStructure childStructure = structure.newChild(i);
                child.dispatchProvideAutofillStructure(childStructure, flags);
                if (child == this.editText) {
                    childStructure.setHint(getHint());
                }
            }
        }
    }

    private void setEditText(EditText editText2) {
        if (this.editText == null) {
            if (this.endIconMode != 3 && !(editText2 instanceof TextInputEditText)) {
                Log.i(LOG_TAG, "EditText added is not a TextInputEditText. Please switch to using that class instead.");
            }
            this.editText = editText2;
            setMinWidth(this.minWidth);
            setMaxWidth(this.maxWidth);
            onApplyBoxBackgroundMode();
            setTextInputAccessibilityDelegate(new AccessibilityDelegate(this));
            this.collapsingTextHelper.setTypefaces(this.editText.getTypeface());
            this.collapsingTextHelper.setExpandedTextSize(this.editText.getTextSize());
            int editTextGravity = this.editText.getGravity();
            this.collapsingTextHelper.setCollapsedTextGravity((editTextGravity & -113) | 48);
            this.collapsingTextHelper.setExpandedTextGravity(editTextGravity);
            this.editText.addTextChangedListener(new TextWatcher() {
                /* class com.google.android.material.textfield.TextInputLayout.C07651 */

                public void afterTextChanged(Editable s) {
                    TextInputLayout textInputLayout = TextInputLayout.this;
                    textInputLayout.updateLabelState(!textInputLayout.restoringSavedState);
                    if (TextInputLayout.this.counterEnabled) {
                        TextInputLayout.this.updateCounter(s.length());
                    }
                    if (TextInputLayout.this.placeholderEnabled) {
                        TextInputLayout.this.updatePlaceholderText(s.length());
                    }
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });
            if (this.defaultHintTextColor == null) {
                this.defaultHintTextColor = this.editText.getHintTextColors();
            }
            if (this.hintEnabled) {
                if (TextUtils.isEmpty(this.hint)) {
                    CharSequence hint2 = this.editText.getHint();
                    this.originalHint = hint2;
                    setHint(hint2);
                    this.editText.setHint((CharSequence) null);
                }
                this.isProvidingHint = true;
            }
            if (this.counterView != null) {
                updateCounter(this.editText.getText().length());
            }
            updateEditTextBackground();
            this.indicatorViewController.adjustIndicatorPadding();
            this.startLayout.bringToFront();
            this.endLayout.bringToFront();
            this.endIconFrame.bringToFront();
            this.errorIconView.bringToFront();
            dispatchOnEditTextAttached();
            updatePrefixTextViewPadding();
            updateSuffixTextViewPadding();
            if (!isEnabled()) {
                editText2.setEnabled(false);
            }
            updateLabelState(false, true);
            return;
        }
        throw new IllegalArgumentException("We already have an EditText, can only have one");
    }

    private void updateInputLayoutMargins() {
        if (this.boxBackgroundMode != 1) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.inputFrame.getLayoutParams();
            int newTopMargin = calculateLabelMarginTop();
            if (newTopMargin != lp.topMargin) {
                lp.topMargin = newTopMargin;
                this.inputFrame.requestLayout();
            }
        }
    }

    public int getBaseline() {
        EditText editText2 = this.editText;
        if (editText2 != null) {
            return editText2.getBaseline() + getPaddingTop() + calculateLabelMarginTop();
        }
        return super.getBaseline();
    }

    /* access modifiers changed from: package-private */
    public void updateLabelState(boolean animate) {
        updateLabelState(animate, false);
    }

    private void updateLabelState(boolean animate, boolean force) {
        ColorStateList colorStateList;
        TextView textView;
        boolean isEnabled = isEnabled();
        EditText editText2 = this.editText;
        boolean hasText = editText2 != null && !TextUtils.isEmpty(editText2.getText());
        EditText editText3 = this.editText;
        boolean hasFocus = editText3 != null && editText3.hasFocus();
        boolean errorShouldBeShown = this.indicatorViewController.errorShouldBeShown();
        ColorStateList colorStateList2 = this.defaultHintTextColor;
        if (colorStateList2 != null) {
            this.collapsingTextHelper.setCollapsedTextColor(colorStateList2);
            this.collapsingTextHelper.setExpandedTextColor(this.defaultHintTextColor);
        }
        if (!isEnabled) {
            ColorStateList colorStateList3 = this.defaultHintTextColor;
            int disabledHintColor = colorStateList3 != null ? colorStateList3.getColorForState(new int[]{-16842910}, this.disabledColor) : this.disabledColor;
            this.collapsingTextHelper.setCollapsedTextColor(ColorStateList.valueOf(disabledHintColor));
            this.collapsingTextHelper.setExpandedTextColor(ColorStateList.valueOf(disabledHintColor));
        } else if (errorShouldBeShown) {
            this.collapsingTextHelper.setCollapsedTextColor(this.indicatorViewController.getErrorViewTextColors());
        } else if (this.counterOverflowed && (textView = this.counterView) != null) {
            this.collapsingTextHelper.setCollapsedTextColor(textView.getTextColors());
        } else if (hasFocus && (colorStateList = this.focusedTextColor) != null) {
            this.collapsingTextHelper.setCollapsedTextColor(colorStateList);
        }
        if (hasText || !this.expandedHintEnabled || (isEnabled() && hasFocus)) {
            if (force || this.hintExpanded) {
                collapseHint(animate);
            }
        } else if (force || !this.hintExpanded) {
            expandHint(animate);
        }
    }

    public EditText getEditText() {
        return this.editText;
    }

    public void setMinWidth(int minWidth2) {
        this.minWidth = minWidth2;
        EditText editText2 = this.editText;
        if (editText2 != null && minWidth2 != -1) {
            editText2.setMinWidth(minWidth2);
        }
    }

    public void setMinWidthResource(int minWidthId) {
        setMinWidth(getContext().getResources().getDimensionPixelSize(minWidthId));
    }

    public int getMinWidth() {
        return this.minWidth;
    }

    public void setMaxWidth(int maxWidth2) {
        this.maxWidth = maxWidth2;
        EditText editText2 = this.editText;
        if (editText2 != null && maxWidth2 != -1) {
            editText2.setMaxWidth(maxWidth2);
        }
    }

    public void setMaxWidthResource(int maxWidthId) {
        setMaxWidth(getContext().getResources().getDimensionPixelSize(maxWidthId));
    }

    public int getMaxWidth() {
        return this.maxWidth;
    }

    public void setHint(CharSequence hint2) {
        if (this.hintEnabled) {
            setHintInternal(hint2);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHint(int textHintId) {
        setHint(textHintId != 0 ? getResources().getText(textHintId) : null);
    }

    private void setHintInternal(CharSequence hint2) {
        if (!TextUtils.equals(hint2, this.hint)) {
            this.hint = hint2;
            this.collapsingTextHelper.setText(hint2);
            if (!this.hintExpanded) {
                openCutout();
            }
        }
    }

    public CharSequence getHint() {
        if (this.hintEnabled) {
            return this.hint;
        }
        return null;
    }

    public void setHintEnabled(boolean enabled) {
        if (enabled != this.hintEnabled) {
            this.hintEnabled = enabled;
            if (!enabled) {
                this.isProvidingHint = false;
                if (!TextUtils.isEmpty(this.hint) && TextUtils.isEmpty(this.editText.getHint())) {
                    this.editText.setHint(this.hint);
                }
                setHintInternal(null);
            } else {
                CharSequence editTextHint = this.editText.getHint();
                if (!TextUtils.isEmpty(editTextHint)) {
                    if (TextUtils.isEmpty(this.hint)) {
                        setHint(editTextHint);
                    }
                    this.editText.setHint((CharSequence) null);
                }
                this.isProvidingHint = true;
            }
            if (this.editText != null) {
                updateInputLayoutMargins();
            }
        }
    }

    public boolean isHintEnabled() {
        return this.hintEnabled;
    }

    public boolean isProvidingHint() {
        return this.isProvidingHint;
    }

    public void setHintTextAppearance(int resId) {
        this.collapsingTextHelper.setCollapsedTextAppearance(resId);
        this.focusedTextColor = this.collapsingTextHelper.getCollapsedTextColor();
        if (this.editText != null) {
            updateLabelState(false);
            updateInputLayoutMargins();
        }
    }

    public void setHintTextColor(ColorStateList hintTextColor) {
        if (this.focusedTextColor != hintTextColor) {
            if (this.defaultHintTextColor == null) {
                this.collapsingTextHelper.setCollapsedTextColor(hintTextColor);
            }
            this.focusedTextColor = hintTextColor;
            if (this.editText != null) {
                updateLabelState(false);
            }
        }
    }

    public ColorStateList getHintTextColor() {
        return this.focusedTextColor;
    }

    public void setDefaultHintTextColor(ColorStateList textColor) {
        this.defaultHintTextColor = textColor;
        this.focusedTextColor = textColor;
        if (this.editText != null) {
            updateLabelState(false);
        }
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.defaultHintTextColor;
    }

    public void setErrorEnabled(boolean enabled) {
        this.indicatorViewController.setErrorEnabled(enabled);
    }

    public void setErrorTextAppearance(int errorTextAppearance) {
        this.indicatorViewController.setErrorTextAppearance(errorTextAppearance);
    }

    public void setErrorTextColor(ColorStateList errorTextColor) {
        this.indicatorViewController.setErrorViewTextColor(errorTextColor);
    }

    public int getErrorCurrentTextColors() {
        return this.indicatorViewController.getErrorViewCurrentTextColor();
    }

    public void setHelperTextTextAppearance(int helperTextTextAppearance) {
        this.indicatorViewController.setHelperTextAppearance(helperTextTextAppearance);
    }

    public void setHelperTextColor(ColorStateList helperTextColor) {
        this.indicatorViewController.setHelperTextViewTextColor(helperTextColor);
    }

    public boolean isErrorEnabled() {
        return this.indicatorViewController.isErrorEnabled();
    }

    public void setHelperTextEnabled(boolean enabled) {
        this.indicatorViewController.setHelperTextEnabled(enabled);
    }

    public void setHelperText(CharSequence helperText) {
        if (!TextUtils.isEmpty(helperText)) {
            if (!isHelperTextEnabled()) {
                setHelperTextEnabled(true);
            }
            this.indicatorViewController.showHelper(helperText);
        } else if (isHelperTextEnabled()) {
            setHelperTextEnabled(false);
        }
    }

    public boolean isHelperTextEnabled() {
        return this.indicatorViewController.isHelperTextEnabled();
    }

    public int getHelperTextCurrentTextColor() {
        return this.indicatorViewController.getHelperTextViewCurrentTextColor();
    }

    public void setErrorContentDescription(CharSequence errorContentDecription) {
        this.indicatorViewController.setErrorContentDescription(errorContentDecription);
    }

    public CharSequence getErrorContentDescription() {
        return this.indicatorViewController.getErrorContentDescription();
    }

    public void setError(CharSequence errorText) {
        if (!this.indicatorViewController.isErrorEnabled()) {
            if (!TextUtils.isEmpty(errorText)) {
                setErrorEnabled(true);
            } else {
                return;
            }
        }
        if (!TextUtils.isEmpty(errorText)) {
            this.indicatorViewController.showError(errorText);
        } else {
            this.indicatorViewController.hideError();
        }
    }

    public void setErrorIconDrawable(int resId) {
        setErrorIconDrawable(resId != 0 ? AppCompatResources.getDrawable(getContext(), resId) : null);
        refreshErrorIconDrawableState();
    }

    public void setErrorIconDrawable(Drawable errorIconDrawable) {
        this.errorIconView.setImageDrawable(errorIconDrawable);
        setErrorIconVisible(errorIconDrawable != null && this.indicatorViewController.isErrorEnabled());
    }

    public Drawable getErrorIconDrawable() {
        return this.errorIconView.getDrawable();
    }

    public void setErrorIconTintList(ColorStateList errorIconTintList2) {
        this.errorIconTintList = errorIconTintList2;
        Drawable icon = this.errorIconView.getDrawable();
        if (icon != null) {
            icon = DrawableCompat.wrap(icon).mutate();
            DrawableCompat.setTintList(icon, errorIconTintList2);
        }
        if (this.errorIconView.getDrawable() != icon) {
            this.errorIconView.setImageDrawable(icon);
        }
    }

    public void setErrorIconTintMode(PorterDuff.Mode errorIconTintMode) {
        Drawable icon = this.errorIconView.getDrawable();
        if (icon != null) {
            icon = DrawableCompat.wrap(icon).mutate();
            DrawableCompat.setTintMode(icon, errorIconTintMode);
        }
        if (this.errorIconView.getDrawable() != icon) {
            this.errorIconView.setImageDrawable(icon);
        }
    }

    public void setCounterEnabled(boolean enabled) {
        if (this.counterEnabled != enabled) {
            if (enabled) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
                this.counterView = appCompatTextView;
                appCompatTextView.setId(C0552R.C0555id.textinput_counter);
                Typeface typeface2 = this.typeface;
                if (typeface2 != null) {
                    this.counterView.setTypeface(typeface2);
                }
                this.counterView.setMaxLines(1);
                this.indicatorViewController.addIndicator(this.counterView, 2);
                MarginLayoutParamsCompat.setMarginStart((ViewGroup.MarginLayoutParams) this.counterView.getLayoutParams(), getResources().getDimensionPixelOffset(C0552R.dimen.mtrl_textinput_counter_margin_start));
                updateCounterTextAppearanceAndColor();
                updateCounter();
            } else {
                this.indicatorViewController.removeIndicator(this.counterView, 2);
                this.counterView = null;
            }
            this.counterEnabled = enabled;
        }
    }

    public void setCounterTextAppearance(int counterTextAppearance2) {
        if (this.counterTextAppearance != counterTextAppearance2) {
            this.counterTextAppearance = counterTextAppearance2;
            updateCounterTextAppearanceAndColor();
        }
    }

    public void setCounterTextColor(ColorStateList counterTextColor2) {
        if (this.counterTextColor != counterTextColor2) {
            this.counterTextColor = counterTextColor2;
            updateCounterTextAppearanceAndColor();
        }
    }

    public ColorStateList getCounterTextColor() {
        return this.counterTextColor;
    }

    public void setCounterOverflowTextAppearance(int counterOverflowTextAppearance2) {
        if (this.counterOverflowTextAppearance != counterOverflowTextAppearance2) {
            this.counterOverflowTextAppearance = counterOverflowTextAppearance2;
            updateCounterTextAppearanceAndColor();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList counterOverflowTextColor2) {
        if (this.counterOverflowTextColor != counterOverflowTextColor2) {
            this.counterOverflowTextColor = counterOverflowTextColor2;
            updateCounterTextAppearanceAndColor();
        }
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.counterTextColor;
    }

    public boolean isCounterEnabled() {
        return this.counterEnabled;
    }

    public void setCounterMaxLength(int maxLength) {
        if (this.counterMaxLength != maxLength) {
            if (maxLength > 0) {
                this.counterMaxLength = maxLength;
            } else {
                this.counterMaxLength = -1;
            }
            if (this.counterEnabled) {
                updateCounter();
            }
        }
    }

    private void updateCounter() {
        if (this.counterView != null) {
            EditText editText2 = this.editText;
            updateCounter(editText2 == null ? 0 : editText2.getText().length());
        }
    }

    /* access modifiers changed from: package-private */
    public void updateCounter(int length) {
        boolean wasCounterOverflowed = this.counterOverflowed;
        int i = this.counterMaxLength;
        if (i == -1) {
            this.counterView.setText(String.valueOf(length));
            this.counterView.setContentDescription(null);
            this.counterOverflowed = false;
        } else {
            this.counterOverflowed = length > i;
            updateCounterContentDescription(getContext(), this.counterView, length, this.counterMaxLength, this.counterOverflowed);
            if (wasCounterOverflowed != this.counterOverflowed) {
                updateCounterTextAppearanceAndColor();
            }
            this.counterView.setText(BidiFormatter.getInstance().unicodeWrap(getContext().getString(C0552R.string.character_counter_pattern, Integer.valueOf(length), Integer.valueOf(this.counterMaxLength))));
        }
        if (this.editText != null && wasCounterOverflowed != this.counterOverflowed) {
            updateLabelState(false);
            updateTextInputBoxState();
            updateEditTextBackground();
        }
    }

    private static void updateCounterContentDescription(Context context, TextView counterView2, int length, int counterMaxLength2, boolean counterOverflowed2) {
        counterView2.setContentDescription(context.getString(counterOverflowed2 ? C0552R.string.character_counter_overflowed_content_description : C0552R.string.character_counter_content_description, Integer.valueOf(length), Integer.valueOf(counterMaxLength2)));
    }

    public void setPlaceholderText(CharSequence placeholderText2) {
        if (!this.placeholderEnabled || !TextUtils.isEmpty(placeholderText2)) {
            if (!this.placeholderEnabled) {
                setPlaceholderTextEnabled(true);
            }
            this.placeholderText = placeholderText2;
        } else {
            setPlaceholderTextEnabled(false);
        }
        updatePlaceholderText();
    }

    public CharSequence getPlaceholderText() {
        if (this.placeholderEnabled) {
            return this.placeholderText;
        }
        return null;
    }

    private void setPlaceholderTextEnabled(boolean placeholderEnabled2) {
        if (this.placeholderEnabled != placeholderEnabled2) {
            if (placeholderEnabled2) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
                this.placeholderTextView = appCompatTextView;
                appCompatTextView.setId(C0552R.C0555id.textinput_placeholder);
                ViewCompat.setAccessibilityLiveRegion(this.placeholderTextView, 1);
                setPlaceholderTextAppearance(this.placeholderTextAppearance);
                setPlaceholderTextColor(this.placeholderTextColor);
                addPlaceholderTextView();
            } else {
                removePlaceholderTextView();
                this.placeholderTextView = null;
            }
            this.placeholderEnabled = placeholderEnabled2;
        }
    }

    private void updatePlaceholderText() {
        EditText editText2 = this.editText;
        updatePlaceholderText(editText2 == null ? 0 : editText2.getText().length());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updatePlaceholderText(int inputTextLength) {
        if (inputTextLength != 0 || this.hintExpanded) {
            hidePlaceholderText();
        } else {
            showPlaceholderText();
        }
    }

    private void showPlaceholderText() {
        TextView textView = this.placeholderTextView;
        if (textView != null && this.placeholderEnabled) {
            textView.setText(this.placeholderText);
            this.placeholderTextView.setVisibility(0);
            this.placeholderTextView.bringToFront();
        }
    }

    private void hidePlaceholderText() {
        TextView textView = this.placeholderTextView;
        if (textView != null && this.placeholderEnabled) {
            textView.setText((CharSequence) null);
            this.placeholderTextView.setVisibility(4);
        }
    }

    private void addPlaceholderTextView() {
        TextView textView = this.placeholderTextView;
        if (textView != null) {
            this.inputFrame.addView(textView);
            this.placeholderTextView.setVisibility(0);
        }
    }

    private void removePlaceholderTextView() {
        TextView textView = this.placeholderTextView;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    public void setPlaceholderTextColor(ColorStateList placeholderTextColor2) {
        if (this.placeholderTextColor != placeholderTextColor2) {
            this.placeholderTextColor = placeholderTextColor2;
            TextView textView = this.placeholderTextView;
            if (textView != null && placeholderTextColor2 != null) {
                textView.setTextColor(placeholderTextColor2);
            }
        }
    }

    public ColorStateList getPlaceholderTextColor() {
        return this.placeholderTextColor;
    }

    public void setPlaceholderTextAppearance(int placeholderTextAppearance2) {
        this.placeholderTextAppearance = placeholderTextAppearance2;
        TextView textView = this.placeholderTextView;
        if (textView != null) {
            TextViewCompat.setTextAppearance(textView, placeholderTextAppearance2);
        }
    }

    public int getPlaceholderTextAppearance() {
        return this.placeholderTextAppearance;
    }

    public void setPrefixText(CharSequence prefixText2) {
        this.prefixText = TextUtils.isEmpty(prefixText2) ? null : prefixText2;
        this.prefixTextView.setText(prefixText2);
        updatePrefixTextVisibility();
    }

    public CharSequence getPrefixText() {
        return this.prefixText;
    }

    public TextView getPrefixTextView() {
        return this.prefixTextView;
    }

    private void updatePrefixTextVisibility() {
        this.prefixTextView.setVisibility((this.prefixText == null || isHintExpanded()) ? 8 : 0);
        updateDummyDrawables();
    }

    public void setPrefixTextColor(ColorStateList prefixTextColor) {
        this.prefixTextView.setTextColor(prefixTextColor);
    }

    public ColorStateList getPrefixTextColor() {
        return this.prefixTextView.getTextColors();
    }

    public void setPrefixTextAppearance(int prefixTextAppearance) {
        TextViewCompat.setTextAppearance(this.prefixTextView, prefixTextAppearance);
    }

    private void updatePrefixTextViewPadding() {
        if (this.editText != null) {
            ViewCompat.setPaddingRelative(this.prefixTextView, isStartIconVisible() ? 0 : ViewCompat.getPaddingStart(this.editText), this.editText.getCompoundPaddingTop(), getContext().getResources().getDimensionPixelSize(C0552R.dimen.material_input_text_to_prefix_suffix_padding), this.editText.getCompoundPaddingBottom());
        }
    }

    public void setSuffixText(CharSequence suffixText2) {
        this.suffixText = TextUtils.isEmpty(suffixText2) ? null : suffixText2;
        this.suffixTextView.setText(suffixText2);
        updateSuffixTextVisibility();
    }

    public CharSequence getSuffixText() {
        return this.suffixText;
    }

    public TextView getSuffixTextView() {
        return this.suffixTextView;
    }

    private void updateSuffixTextVisibility() {
        int oldSuffixVisibility = this.suffixTextView.getVisibility();
        int i = 0;
        boolean visible = this.suffixText != null && !isHintExpanded();
        TextView textView = this.suffixTextView;
        if (!visible) {
            i = 8;
        }
        textView.setVisibility(i);
        if (oldSuffixVisibility != this.suffixTextView.getVisibility()) {
            getEndIconDelegate().onSuffixVisibilityChanged(visible);
        }
        updateDummyDrawables();
    }

    public void setSuffixTextColor(ColorStateList suffixTextColor) {
        this.suffixTextView.setTextColor(suffixTextColor);
    }

    public ColorStateList getSuffixTextColor() {
        return this.suffixTextView.getTextColors();
    }

    public void setSuffixTextAppearance(int suffixTextAppearance) {
        TextViewCompat.setTextAppearance(this.suffixTextView, suffixTextAppearance);
    }

    private void updateSuffixTextViewPadding() {
        if (this.editText != null) {
            ViewCompat.setPaddingRelative(this.suffixTextView, getContext().getResources().getDimensionPixelSize(C0552R.dimen.material_input_text_to_prefix_suffix_padding), this.editText.getPaddingTop(), (isEndIconVisible() || isErrorIconVisible()) ? 0 : ViewCompat.getPaddingEnd(this.editText), this.editText.getPaddingBottom());
        }
    }

    public void setEnabled(boolean enabled) {
        recursiveSetEnabled(this, enabled);
        super.setEnabled(enabled);
    }

    private static void recursiveSetEnabled(ViewGroup vg, boolean enabled) {
        int count = vg.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = vg.getChildAt(i);
            child.setEnabled(enabled);
            if (child instanceof ViewGroup) {
                recursiveSetEnabled((ViewGroup) child, enabled);
            }
        }
    }

    public int getCounterMaxLength() {
        return this.counterMaxLength;
    }

    /* access modifiers changed from: package-private */
    public CharSequence getCounterOverflowDescription() {
        TextView textView;
        if (!this.counterEnabled || !this.counterOverflowed || (textView = this.counterView) == null) {
            return null;
        }
        return textView.getContentDescription();
    }

    private void updateCounterTextAppearanceAndColor() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        TextView textView = this.counterView;
        if (textView != null) {
            setTextAppearanceCompatWithErrorFallback(textView, this.counterOverflowed ? this.counterOverflowTextAppearance : this.counterTextAppearance);
            if (!this.counterOverflowed && (colorStateList2 = this.counterTextColor) != null) {
                this.counterView.setTextColor(colorStateList2);
            }
            if (this.counterOverflowed && (colorStateList = this.counterOverflowTextColor) != null) {
                this.counterView.setTextColor(colorStateList);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setTextAppearanceCompatWithErrorFallback(TextView textView, int textAppearance) {
        boolean useDefaultColor = false;
        try {
            TextViewCompat.setTextAppearance(textView, textAppearance);
            if (Build.VERSION.SDK_INT >= 23 && textView.getTextColors().getDefaultColor() == -65281) {
                useDefaultColor = true;
            }
        } catch (Exception e) {
            useDefaultColor = true;
        }
        if (useDefaultColor) {
            TextViewCompat.setTextAppearance(textView, C0552R.style.TextAppearance_AppCompat_Caption);
            textView.setTextColor(ContextCompat.getColor(getContext(), C0552R.C0553color.design_error));
        }
    }

    private int calculateLabelMarginTop() {
        if (!this.hintEnabled) {
            return 0;
        }
        switch (this.boxBackgroundMode) {
            case 0:
            case 1:
                return (int) this.collapsingTextHelper.getCollapsedTextHeight();
            case 2:
                return (int) (this.collapsingTextHelper.getCollapsedTextHeight() / 2.0f);
            default:
                return 0;
        }
    }

    private Rect calculateCollapsedTextBounds(Rect rect) {
        if (this.editText != null) {
            Rect bounds = this.tmpBoundsRect;
            boolean isRtl = true;
            if (ViewCompat.getLayoutDirection(this) != 1) {
                isRtl = false;
            }
            bounds.bottom = rect.bottom;
            switch (this.boxBackgroundMode) {
                case 1:
                    bounds.left = getLabelLeftBoundAlightWithPrefix(rect.left, isRtl);
                    bounds.top = rect.top + this.boxCollapsedPaddingTopPx;
                    bounds.right = getLabelRightBoundAlignedWithSuffix(rect.right, isRtl);
                    return bounds;
                case 2:
                    bounds.left = rect.left + this.editText.getPaddingLeft();
                    bounds.top = rect.top - calculateLabelMarginTop();
                    bounds.right = rect.right - this.editText.getPaddingRight();
                    return bounds;
                default:
                    bounds.left = getLabelLeftBoundAlightWithPrefix(rect.left, isRtl);
                    bounds.top = getPaddingTop();
                    bounds.right = getLabelRightBoundAlignedWithSuffix(rect.right, isRtl);
                    return bounds;
            }
        } else {
            throw new IllegalStateException();
        }
    }

    private int getLabelLeftBoundAlightWithPrefix(int rectLeft, boolean isRtl) {
        int left = this.editText.getCompoundPaddingLeft() + rectLeft;
        if (this.prefixText == null || isRtl) {
            return left;
        }
        return (left - this.prefixTextView.getMeasuredWidth()) + this.prefixTextView.getPaddingLeft();
    }

    private int getLabelRightBoundAlignedWithSuffix(int rectRight, boolean isRtl) {
        int right = rectRight - this.editText.getCompoundPaddingRight();
        if (this.prefixText == null || !isRtl) {
            return right;
        }
        return right + (this.prefixTextView.getMeasuredWidth() - this.prefixTextView.getPaddingRight());
    }

    private Rect calculateExpandedTextBounds(Rect rect) {
        if (this.editText != null) {
            Rect bounds = this.tmpBoundsRect;
            float labelHeight = this.collapsingTextHelper.getExpandedTextHeight();
            bounds.left = rect.left + this.editText.getCompoundPaddingLeft();
            bounds.top = calculateExpandedLabelTop(rect, labelHeight);
            bounds.right = rect.right - this.editText.getCompoundPaddingRight();
            bounds.bottom = calculateExpandedLabelBottom(rect, bounds, labelHeight);
            return bounds;
        }
        throw new IllegalStateException();
    }

    private int calculateExpandedLabelTop(Rect rect, float labelHeight) {
        if (isSingleLineFilledTextField()) {
            return (int) (((float) rect.centerY()) - (labelHeight / 2.0f));
        }
        return rect.top + this.editText.getCompoundPaddingTop();
    }

    private int calculateExpandedLabelBottom(Rect rect, Rect bounds, float labelHeight) {
        if (isSingleLineFilledTextField()) {
            return (int) (((float) bounds.top) + labelHeight);
        }
        return rect.bottom - this.editText.getCompoundPaddingBottom();
    }

    private boolean isSingleLineFilledTextField() {
        if (this.boxBackgroundMode != 1 || (Build.VERSION.SDK_INT >= 16 && this.editText.getMinLines() > 1)) {
            return false;
        }
        return true;
    }

    private int calculateBoxBackgroundColor() {
        int backgroundColor = this.boxBackgroundColor;
        if (this.boxBackgroundMode == 1) {
            return MaterialColors.layer(MaterialColors.getColor(this, C0552R.attr.colorSurface, 0), this.boxBackgroundColor);
        }
        return backgroundColor;
    }

    private void applyBoxAttributes() {
        MaterialShapeDrawable materialShapeDrawable = this.boxBackground;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShapeAppearanceModel(this.shapeAppearanceModel);
            if (canDrawOutlineStroke()) {
                this.boxBackground.setStroke((float) this.boxStrokeWidthPx, this.boxStrokeColor);
            }
            int calculateBoxBackgroundColor = calculateBoxBackgroundColor();
            this.boxBackgroundColor = calculateBoxBackgroundColor;
            this.boxBackground.setFillColor(ColorStateList.valueOf(calculateBoxBackgroundColor));
            if (this.endIconMode == 3) {
                this.editText.getBackground().invalidateSelf();
            }
            applyBoxUnderlineAttributes();
            invalidate();
        }
    }

    private void applyBoxUnderlineAttributes() {
        if (this.boxUnderline != null) {
            if (canDrawStroke()) {
                this.boxUnderline.setFillColor(ColorStateList.valueOf(this.boxStrokeColor));
            }
            invalidate();
        }
    }

    private boolean canDrawOutlineStroke() {
        return this.boxBackgroundMode == 2 && canDrawStroke();
    }

    private boolean canDrawStroke() {
        return this.boxStrokeWidthPx > -1 && this.boxStrokeColor != 0;
    }

    /* access modifiers changed from: package-private */
    public void updateEditTextBackground() {
        Drawable editTextBackground;
        TextView textView;
        EditText editText2 = this.editText;
        if (editText2 != null && this.boxBackgroundMode == 0 && (editTextBackground = editText2.getBackground()) != null) {
            if (DrawableUtils.canSafelyMutateDrawable(editTextBackground)) {
                editTextBackground = editTextBackground.mutate();
            }
            if (this.indicatorViewController.errorShouldBeShown()) {
                editTextBackground.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(this.indicatorViewController.getErrorViewCurrentTextColor(), PorterDuff.Mode.SRC_IN));
            } else if (!this.counterOverflowed || (textView = this.counterView) == null) {
                DrawableCompat.clearColorFilter(editTextBackground);
                this.editText.refreshDrawableState();
            } else {
                editTextBackground.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            /* class com.google.android.material.textfield.TextInputLayout.SavedState.C07691 */

            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                return new SavedState(in, loader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in, null);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        CharSequence error;
        CharSequence helperText;
        CharSequence hintText;
        boolean isEndIconChecked;
        CharSequence placeholderText;

        SavedState(Parcelable superState) {
            super(superState);
        }

        SavedState(Parcel source, ClassLoader loader) {
            super(source, loader);
            this.error = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source);
            this.isEndIconChecked = source.readInt() != 1 ? false : true;
            this.hintText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source);
            this.helperText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source);
            this.placeholderText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source);
        }

        @Override // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            TextUtils.writeToParcel(this.error, dest, flags);
            dest.writeInt(this.isEndIconChecked ? 1 : 0);
            TextUtils.writeToParcel(this.hintText, dest, flags);
            TextUtils.writeToParcel(this.helperText, dest, flags);
            TextUtils.writeToParcel(this.placeholderText, dest, flags);
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.error) + " hint=" + ((Object) this.hintText) + " helperText=" + ((Object) this.helperText) + " placeholderText=" + ((Object) this.placeholderText) + "}";
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        if (this.indicatorViewController.errorShouldBeShown()) {
            ss.error = getError();
        }
        ss.isEndIconChecked = hasEndIcon() && this.endIconView.isChecked();
        ss.hintText = getHint();
        ss.helperText = getHelperText();
        ss.placeholderText = getPlaceholderText();
        return ss;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setError(ss.error);
        if (ss.isEndIconChecked) {
            this.endIconView.post(new Runnable() {
                /* class com.google.android.material.textfield.TextInputLayout.RunnableC07662 */

                public void run() {
                    TextInputLayout.this.endIconView.performClick();
                    TextInputLayout.this.endIconView.jumpDrawablesToCurrentState();
                }
            });
        }
        setHint(ss.hintText);
        setHelperText(ss.helperText);
        setPlaceholderText(ss.placeholderText);
        requestLayout();
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View, android.view.ViewGroup
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        this.restoringSavedState = true;
        super.dispatchRestoreInstanceState(container);
        this.restoringSavedState = false;
    }

    public CharSequence getError() {
        if (this.indicatorViewController.isErrorEnabled()) {
            return this.indicatorViewController.getErrorText();
        }
        return null;
    }

    public CharSequence getHelperText() {
        if (this.indicatorViewController.isHelperTextEnabled()) {
            return this.indicatorViewController.getHelperText();
        }
        return null;
    }

    public boolean isHintAnimationEnabled() {
        return this.hintAnimationEnabled;
    }

    public void setHintAnimationEnabled(boolean enabled) {
        this.hintAnimationEnabled = enabled;
    }

    public boolean isExpandedHintEnabled() {
        return this.expandedHintEnabled;
    }

    public void setExpandedHintEnabled(boolean enabled) {
        if (this.expandedHintEnabled != enabled) {
            this.expandedHintEnabled = enabled;
            updateLabelState(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        boolean updatedHeight = updateEditTextHeightBasedOnIcon();
        boolean updatedIcon = updateDummyDrawables();
        if (updatedHeight || updatedIcon) {
            this.editText.post(new Runnable() {
                /* class com.google.android.material.textfield.TextInputLayout.RunnableC07673 */

                public void run() {
                    TextInputLayout.this.editText.requestLayout();
                }
            });
        }
        updatePlaceholderMeasurementsBasedOnEditText();
        updatePrefixTextViewPadding();
        updateSuffixTextViewPadding();
    }

    private boolean updateEditTextHeightBasedOnIcon() {
        int maxIconHeight;
        if (this.editText == null || this.editText.getMeasuredHeight() >= (maxIconHeight = Math.max(this.endLayout.getMeasuredHeight(), this.startLayout.getMeasuredHeight()))) {
            return false;
        }
        this.editText.setMinimumHeight(maxIconHeight);
        return true;
    }

    private void updatePlaceholderMeasurementsBasedOnEditText() {
        EditText editText2;
        if (this.placeholderTextView != null && (editText2 = this.editText) != null) {
            this.placeholderTextView.setGravity(editText2.getGravity());
            this.placeholderTextView.setPadding(this.editText.getCompoundPaddingLeft(), this.editText.getCompoundPaddingTop(), this.editText.getCompoundPaddingRight(), this.editText.getCompoundPaddingBottom());
        }
    }

    public void setStartIconDrawable(int resId) {
        setStartIconDrawable(resId != 0 ? AppCompatResources.getDrawable(getContext(), resId) : null);
    }

    public void setStartIconDrawable(Drawable startIconDrawable) {
        this.startIconView.setImageDrawable(startIconDrawable);
        if (startIconDrawable != null) {
            setStartIconVisible(true);
            refreshStartIconDrawableState();
            return;
        }
        setStartIconVisible(false);
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        setStartIconContentDescription((CharSequence) null);
    }

    public Drawable getStartIconDrawable() {
        return this.startIconView.getDrawable();
    }

    public void setStartIconOnClickListener(View.OnClickListener startIconOnClickListener) {
        setIconOnClickListener(this.startIconView, startIconOnClickListener, this.startIconOnLongClickListener);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener startIconOnLongClickListener2) {
        this.startIconOnLongClickListener = startIconOnLongClickListener2;
        setIconOnLongClickListener(this.startIconView, startIconOnLongClickListener2);
    }

    public void setStartIconVisible(boolean visible) {
        if (isStartIconVisible() != visible) {
            this.startIconView.setVisibility(visible ? 0 : 8);
            updatePrefixTextViewPadding();
            updateDummyDrawables();
        }
    }

    public boolean isStartIconVisible() {
        return this.startIconView.getVisibility() == 0;
    }

    public void refreshStartIconDrawableState() {
        refreshIconDrawableState(this.startIconView, this.startIconTintList);
    }

    public void setStartIconCheckable(boolean startIconCheckable) {
        this.startIconView.setCheckable(startIconCheckable);
    }

    public boolean isStartIconCheckable() {
        return this.startIconView.isCheckable();
    }

    public void setStartIconContentDescription(int resId) {
        setStartIconContentDescription(resId != 0 ? getResources().getText(resId) : null);
    }

    public void setStartIconContentDescription(CharSequence startIconContentDescription) {
        if (getStartIconContentDescription() != startIconContentDescription) {
            this.startIconView.setContentDescription(startIconContentDescription);
        }
    }

    public CharSequence getStartIconContentDescription() {
        return this.startIconView.getContentDescription();
    }

    public void setStartIconTintList(ColorStateList startIconTintList2) {
        if (this.startIconTintList != startIconTintList2) {
            this.startIconTintList = startIconTintList2;
            this.hasStartIconTintList = true;
            applyStartIconTint();
        }
    }

    public void setStartIconTintMode(PorterDuff.Mode startIconTintMode2) {
        if (this.startIconTintMode != startIconTintMode2) {
            this.startIconTintMode = startIconTintMode2;
            this.hasStartIconTintMode = true;
            applyStartIconTint();
        }
    }

    public void setEndIconMode(int endIconMode2) {
        int previousEndIconMode = this.endIconMode;
        this.endIconMode = endIconMode2;
        dispatchOnEndIconChanged(previousEndIconMode);
        setEndIconVisible(endIconMode2 != 0);
        if (getEndIconDelegate().isBoxBackgroundModeSupported(this.boxBackgroundMode)) {
            getEndIconDelegate().initialize();
            applyEndIconTint();
            return;
        }
        throw new IllegalStateException("The current box background mode " + this.boxBackgroundMode + " is not supported by the end icon mode " + endIconMode2);
    }

    public int getEndIconMode() {
        return this.endIconMode;
    }

    public void setEndIconOnClickListener(View.OnClickListener endIconOnClickListener) {
        setIconOnClickListener(this.endIconView, endIconOnClickListener, this.endIconOnLongClickListener);
    }

    public void setErrorIconOnClickListener(View.OnClickListener errorIconOnClickListener) {
        setIconOnClickListener(this.errorIconView, errorIconOnClickListener, this.errorIconOnLongClickListener);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener endIconOnLongClickListener2) {
        this.endIconOnLongClickListener = endIconOnLongClickListener2;
        setIconOnLongClickListener(this.endIconView, endIconOnLongClickListener2);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener errorIconOnLongClickListener2) {
        this.errorIconOnLongClickListener = errorIconOnLongClickListener2;
        setIconOnLongClickListener(this.errorIconView, errorIconOnLongClickListener2);
    }

    public void refreshErrorIconDrawableState() {
        refreshIconDrawableState(this.errorIconView, this.errorIconTintList);
    }

    public void setEndIconVisible(boolean visible) {
        if (isEndIconVisible() != visible) {
            this.endIconView.setVisibility(visible ? 0 : 8);
            updateSuffixTextViewPadding();
            updateDummyDrawables();
        }
    }

    public boolean isEndIconVisible() {
        return this.endIconFrame.getVisibility() == 0 && this.endIconView.getVisibility() == 0;
    }

    public void setEndIconActivated(boolean endIconActivated) {
        this.endIconView.setActivated(endIconActivated);
    }

    public void refreshEndIconDrawableState() {
        refreshIconDrawableState(this.endIconView, this.endIconTintList);
    }

    public void setEndIconCheckable(boolean endIconCheckable) {
        this.endIconView.setCheckable(endIconCheckable);
    }

    public boolean isEndIconCheckable() {
        return this.endIconView.isCheckable();
    }

    public void setEndIconDrawable(int resId) {
        setEndIconDrawable(resId != 0 ? AppCompatResources.getDrawable(getContext(), resId) : null);
    }

    public void setEndIconDrawable(Drawable endIconDrawable) {
        this.endIconView.setImageDrawable(endIconDrawable);
        refreshEndIconDrawableState();
    }

    public Drawable getEndIconDrawable() {
        return this.endIconView.getDrawable();
    }

    public void setEndIconContentDescription(int resId) {
        setEndIconContentDescription(resId != 0 ? getResources().getText(resId) : null);
    }

    public void setEndIconContentDescription(CharSequence endIconContentDescription) {
        if (getEndIconContentDescription() != endIconContentDescription) {
            this.endIconView.setContentDescription(endIconContentDescription);
        }
    }

    public CharSequence getEndIconContentDescription() {
        return this.endIconView.getContentDescription();
    }

    public void setEndIconTintList(ColorStateList endIconTintList2) {
        if (this.endIconTintList != endIconTintList2) {
            this.endIconTintList = endIconTintList2;
            this.hasEndIconTintList = true;
            applyEndIconTint();
        }
    }

    public void setEndIconTintMode(PorterDuff.Mode endIconTintMode2) {
        if (this.endIconTintMode != endIconTintMode2) {
            this.endIconTintMode = endIconTintMode2;
            this.hasEndIconTintMode = true;
            applyEndIconTint();
        }
    }

    public void addOnEndIconChangedListener(OnEndIconChangedListener listener) {
        this.endIconChangedListeners.add(listener);
    }

    public void removeOnEndIconChangedListener(OnEndIconChangedListener listener) {
        this.endIconChangedListeners.remove(listener);
    }

    public void clearOnEndIconChangedListeners() {
        this.endIconChangedListeners.clear();
    }

    public void addOnEditTextAttachedListener(OnEditTextAttachedListener listener) {
        this.editTextAttachedListeners.add(listener);
        if (this.editText != null) {
            listener.onEditTextAttached(this);
        }
    }

    public void removeOnEditTextAttachedListener(OnEditTextAttachedListener listener) {
        this.editTextAttachedListeners.remove(listener);
    }

    public void clearOnEditTextAttachedListeners() {
        this.editTextAttachedListeners.clear();
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int resId) {
        setPasswordVisibilityToggleDrawable(resId != 0 ? AppCompatResources.getDrawable(getContext(), resId) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable icon) {
        this.endIconView.setImageDrawable(icon);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(int resId) {
        setPasswordVisibilityToggleContentDescription(resId != 0 ? getResources().getText(resId) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence description) {
        this.endIconView.setContentDescription(description);
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.endIconView.getDrawable();
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.endIconView.getContentDescription();
    }

    @Deprecated
    public boolean isPasswordVisibilityToggleEnabled() {
        return this.endIconMode == 1;
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean enabled) {
        if (enabled && this.endIconMode != 1) {
            setEndIconMode(1);
        } else if (!enabled) {
            setEndIconMode(0);
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(ColorStateList tintList) {
        this.endIconTintList = tintList;
        this.hasEndIconTintList = true;
        applyEndIconTint();
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        this.endIconTintMode = mode;
        this.hasEndIconTintMode = true;
        applyEndIconTint();
    }

    @Deprecated
    public void passwordVisibilityToggleRequested(boolean shouldSkipAnimations) {
        if (this.endIconMode == 1) {
            this.endIconView.performClick();
            if (shouldSkipAnimations) {
                this.endIconView.jumpDrawablesToCurrentState();
            }
        }
    }

    public void setTextInputAccessibilityDelegate(AccessibilityDelegate delegate) {
        EditText editText2 = this.editText;
        if (editText2 != null) {
            ViewCompat.setAccessibilityDelegate(editText2, delegate);
        }
    }

    /* access modifiers changed from: package-private */
    public CheckableImageButton getEndIconView() {
        return this.endIconView;
    }

    private EndIconDelegate getEndIconDelegate() {
        EndIconDelegate endIconDelegate = this.endIconDelegates.get(this.endIconMode);
        return endIconDelegate != null ? endIconDelegate : this.endIconDelegates.get(0);
    }

    private void dispatchOnEditTextAttached() {
        Iterator<OnEditTextAttachedListener> it = this.editTextAttachedListeners.iterator();
        while (it.hasNext()) {
            it.next().onEditTextAttached(this);
        }
    }

    private void applyStartIconTint() {
        applyIconTint(this.startIconView, this.hasStartIconTintList, this.startIconTintList, this.hasStartIconTintMode, this.startIconTintMode);
    }

    private boolean hasEndIcon() {
        return this.endIconMode != 0;
    }

    private void dispatchOnEndIconChanged(int previousIcon) {
        Iterator<OnEndIconChangedListener> it = this.endIconChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().onEndIconChanged(this, previousIcon);
        }
    }

    private void tintEndIconOnError(boolean tintEndIconOnError) {
        if (!tintEndIconOnError || getEndIconDrawable() == null) {
            applyEndIconTint();
            return;
        }
        Drawable endIconDrawable = DrawableCompat.wrap(getEndIconDrawable()).mutate();
        DrawableCompat.setTint(endIconDrawable, this.indicatorViewController.getErrorViewCurrentTextColor());
        this.endIconView.setImageDrawable(endIconDrawable);
    }

    private void applyEndIconTint() {
        applyIconTint(this.endIconView, this.hasEndIconTintList, this.endIconTintList, this.hasEndIconTintMode, this.endIconTintMode);
    }

    private boolean updateDummyDrawables() {
        if (this.editText == null) {
            return false;
        }
        boolean updatedIcon = false;
        if (shouldUpdateStartDummyDrawable()) {
            int right = this.startLayout.getMeasuredWidth() - this.editText.getPaddingLeft();
            if (this.startDummyDrawable == null || this.startDummyDrawableWidth != right) {
                ColorDrawable colorDrawable = new ColorDrawable();
                this.startDummyDrawable = colorDrawable;
                this.startDummyDrawableWidth = right;
                colorDrawable.setBounds(0, 0, right, 1);
            }
            Drawable[] compounds = TextViewCompat.getCompoundDrawablesRelative(this.editText);
            Drawable drawable = compounds[0];
            Drawable drawable2 = this.startDummyDrawable;
            if (drawable != drawable2) {
                TextViewCompat.setCompoundDrawablesRelative(this.editText, drawable2, compounds[1], compounds[2], compounds[3]);
                updatedIcon = true;
            }
        } else if (this.startDummyDrawable != null) {
            Drawable[] compounds2 = TextViewCompat.getCompoundDrawablesRelative(this.editText);
            TextViewCompat.setCompoundDrawablesRelative(this.editText, null, compounds2[1], compounds2[2], compounds2[3]);
            this.startDummyDrawable = null;
            updatedIcon = true;
        }
        if (shouldUpdateEndDummyDrawable()) {
            int right2 = this.suffixTextView.getMeasuredWidth() - this.editText.getPaddingRight();
            View iconView = getEndIconToUpdateDummyDrawable();
            if (iconView != null) {
                right2 = iconView.getMeasuredWidth() + right2 + MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams) iconView.getLayoutParams());
            }
            Drawable[] compounds3 = TextViewCompat.getCompoundDrawablesRelative(this.editText);
            Drawable drawable3 = this.endDummyDrawable;
            if (drawable3 == null || this.endDummyDrawableWidth == right2) {
                if (drawable3 == null) {
                    ColorDrawable colorDrawable2 = new ColorDrawable();
                    this.endDummyDrawable = colorDrawable2;
                    this.endDummyDrawableWidth = right2;
                    colorDrawable2.setBounds(0, 0, right2, 1);
                }
                Drawable drawable4 = compounds3[2];
                Drawable drawable5 = this.endDummyDrawable;
                if (drawable4 == drawable5) {
                    return updatedIcon;
                }
                this.originalEditTextEndDrawable = compounds3[2];
                TextViewCompat.setCompoundDrawablesRelative(this.editText, compounds3[0], compounds3[1], drawable5, compounds3[3]);
                return true;
            }
            this.endDummyDrawableWidth = right2;
            drawable3.setBounds(0, 0, right2, 1);
            TextViewCompat.setCompoundDrawablesRelative(this.editText, compounds3[0], compounds3[1], this.endDummyDrawable, compounds3[3]);
            return true;
        } else if (this.endDummyDrawable == null) {
            return updatedIcon;
        } else {
            Drawable[] compounds4 = TextViewCompat.getCompoundDrawablesRelative(this.editText);
            if (compounds4[2] == this.endDummyDrawable) {
                TextViewCompat.setCompoundDrawablesRelative(this.editText, compounds4[0], compounds4[1], this.originalEditTextEndDrawable, compounds4[3]);
                updatedIcon = true;
            }
            this.endDummyDrawable = null;
            return updatedIcon;
        }
    }

    private boolean shouldUpdateStartDummyDrawable() {
        return !(getStartIconDrawable() == null && this.prefixText == null) && this.startLayout.getMeasuredWidth() > 0;
    }

    private boolean shouldUpdateEndDummyDrawable() {
        return (this.errorIconView.getVisibility() == 0 || ((hasEndIcon() && isEndIconVisible()) || this.suffixText != null)) && this.endLayout.getMeasuredWidth() > 0;
    }

    private CheckableImageButton getEndIconToUpdateDummyDrawable() {
        if (this.errorIconView.getVisibility() == 0) {
            return this.errorIconView;
        }
        if (!hasEndIcon() || !isEndIconVisible()) {
            return null;
        }
        return this.endIconView;
    }

    private void applyIconTint(CheckableImageButton iconView, boolean hasIconTintList, ColorStateList iconTintList, boolean hasIconTintMode, PorterDuff.Mode iconTintMode) {
        Drawable icon = iconView.getDrawable();
        if (icon != null && (hasIconTintList || hasIconTintMode)) {
            icon = DrawableCompat.wrap(icon).mutate();
            if (hasIconTintList) {
                DrawableCompat.setTintList(icon, iconTintList);
            }
            if (hasIconTintMode) {
                DrawableCompat.setTintMode(icon, iconTintMode);
            }
        }
        if (iconView.getDrawable() != icon) {
            iconView.setImageDrawable(icon);
        }
    }

    private static void setIconOnClickListener(CheckableImageButton iconView, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        iconView.setOnClickListener(onClickListener);
        setIconClickable(iconView, onLongClickListener);
    }

    private static void setIconOnLongClickListener(CheckableImageButton iconView, View.OnLongClickListener onLongClickListener) {
        iconView.setOnLongClickListener(onLongClickListener);
        setIconClickable(iconView, onLongClickListener);
    }

    private static void setIconClickable(CheckableImageButton iconView, View.OnLongClickListener onLongClickListener) {
        boolean iconClickable = ViewCompat.hasOnClickListeners(iconView);
        boolean iconFocusable = false;
        int i = 1;
        boolean iconLongClickable = onLongClickListener != null;
        if (iconClickable || iconLongClickable) {
            iconFocusable = true;
        }
        iconView.setFocusable(iconFocusable);
        iconView.setClickable(iconClickable);
        iconView.setPressable(iconClickable);
        iconView.setLongClickable(iconLongClickable);
        if (!iconFocusable) {
            i = 2;
        }
        ViewCompat.setImportantForAccessibility(iconView, i);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        EditText editText2 = this.editText;
        if (editText2 != null) {
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(this, editText2, rect);
            updateBoxUnderlineBounds(rect);
            if (this.hintEnabled) {
                this.collapsingTextHelper.setExpandedTextSize(this.editText.getTextSize());
                int editTextGravity = this.editText.getGravity();
                this.collapsingTextHelper.setCollapsedTextGravity((editTextGravity & -113) | 48);
                this.collapsingTextHelper.setExpandedTextGravity(editTextGravity);
                this.collapsingTextHelper.setCollapsedBounds(calculateCollapsedTextBounds(rect));
                this.collapsingTextHelper.setExpandedBounds(calculateExpandedTextBounds(rect));
                this.collapsingTextHelper.recalculate();
                if (cutoutEnabled() && !this.hintExpanded) {
                    openCutout();
                }
            }
        }
    }

    private void updateBoxUnderlineBounds(Rect bounds) {
        if (this.boxUnderline != null) {
            this.boxUnderline.setBounds(bounds.left, bounds.bottom - this.boxStrokeWidthFocusedPx, bounds.right, bounds.bottom);
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawHint(canvas);
        drawBoxUnderline(canvas);
    }

    private void drawHint(Canvas canvas) {
        if (this.hintEnabled) {
            this.collapsingTextHelper.draw(canvas);
        }
    }

    private void drawBoxUnderline(Canvas canvas) {
        MaterialShapeDrawable materialShapeDrawable = this.boxUnderline;
        if (materialShapeDrawable != null) {
            Rect underlineBounds = materialShapeDrawable.getBounds();
            underlineBounds.top = underlineBounds.bottom - this.boxStrokeWidthPx;
            this.boxUnderline.draw(canvas);
        }
    }

    private void collapseHint(boolean animate) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animator.cancel();
        }
        if (!animate || !this.hintAnimationEnabled) {
            this.collapsingTextHelper.setExpansionFraction(1.0f);
        } else {
            animateToExpansionFraction(1.0f);
        }
        this.hintExpanded = false;
        if (cutoutEnabled()) {
            openCutout();
        }
        updatePlaceholderText();
        updatePrefixTextVisibility();
        updateSuffixTextVisibility();
    }

    private boolean cutoutEnabled() {
        return this.hintEnabled && !TextUtils.isEmpty(this.hint) && (this.boxBackground instanceof CutoutDrawable);
    }

    private void openCutout() {
        if (cutoutEnabled()) {
            RectF cutoutBounds = this.tmpRectF;
            this.collapsingTextHelper.getCollapsedTextActualBounds(cutoutBounds, this.editText.getWidth(), this.editText.getGravity());
            applyCutoutPadding(cutoutBounds);
            this.boxLabelCutoutHeight = this.boxStrokeWidthPx;
            cutoutBounds.top = 0.0f;
            cutoutBounds.bottom = (float) this.boxLabelCutoutHeight;
            cutoutBounds.offset((float) (-getPaddingLeft()), 0.0f);
            ((CutoutDrawable) this.boxBackground).setCutout(cutoutBounds);
        }
    }

    private void updateCutout() {
        if (cutoutEnabled() && !this.hintExpanded && this.boxLabelCutoutHeight != this.boxStrokeWidthPx) {
            closeCutout();
            openCutout();
        }
    }

    private void closeCutout() {
        if (cutoutEnabled()) {
            ((CutoutDrawable) this.boxBackground).removeCutout();
        }
    }

    private void applyCutoutPadding(RectF cutoutBounds) {
        cutoutBounds.left -= (float) this.boxLabelCutoutPaddingPx;
        cutoutBounds.right += (float) this.boxLabelCutoutPaddingPx;
    }

    /* access modifiers changed from: package-private */
    public boolean cutoutIsOpen() {
        return cutoutEnabled() && ((CutoutDrawable) this.boxBackground).hasCutout();
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        if (!this.inDrawableStateChanged) {
            boolean z = true;
            this.inDrawableStateChanged = true;
            super.drawableStateChanged();
            int[] state = getDrawableState();
            boolean changed = false;
            CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
            if (collapsingTextHelper2 != null) {
                changed = false | collapsingTextHelper2.setState(state);
            }
            if (this.editText != null) {
                if (!ViewCompat.isLaidOut(this) || !isEnabled()) {
                    z = false;
                }
                updateLabelState(z);
            }
            updateEditTextBackground();
            updateTextInputBoxState();
            if (changed) {
                invalidate();
            }
            this.inDrawableStateChanged = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void updateTextInputBoxState() {
        TextView textView;
        EditText editText2;
        EditText editText3;
        if (this.boxBackground != null && this.boxBackgroundMode != 0) {
            boolean z = false;
            boolean hasFocus = isFocused() || ((editText3 = this.editText) != null && editText3.hasFocus());
            boolean isHovered = isHovered() || ((editText2 = this.editText) != null && editText2.isHovered());
            if (!isEnabled()) {
                this.boxStrokeColor = this.disabledColor;
            } else if (this.indicatorViewController.errorShouldBeShown()) {
                if (this.strokeErrorColor != null) {
                    updateStrokeErrorColor(hasFocus, isHovered);
                } else {
                    this.boxStrokeColor = this.indicatorViewController.getErrorViewCurrentTextColor();
                }
            } else if (!this.counterOverflowed || (textView = this.counterView) == null) {
                if (hasFocus) {
                    this.boxStrokeColor = this.focusedStrokeColor;
                } else if (isHovered) {
                    this.boxStrokeColor = this.hoveredStrokeColor;
                } else {
                    this.boxStrokeColor = this.defaultStrokeColor;
                }
            } else if (this.strokeErrorColor != null) {
                updateStrokeErrorColor(hasFocus, isHovered);
            } else {
                this.boxStrokeColor = textView.getCurrentTextColor();
            }
            if (getErrorIconDrawable() != null && this.indicatorViewController.isErrorEnabled() && this.indicatorViewController.errorShouldBeShown()) {
                z = true;
            }
            setErrorIconVisible(z);
            refreshErrorIconDrawableState();
            refreshStartIconDrawableState();
            refreshEndIconDrawableState();
            if (getEndIconDelegate().shouldTintIconOnError()) {
                tintEndIconOnError(this.indicatorViewController.errorShouldBeShown());
            }
            if (!hasFocus || !isEnabled()) {
                this.boxStrokeWidthPx = this.boxStrokeWidthDefaultPx;
            } else {
                this.boxStrokeWidthPx = this.boxStrokeWidthFocusedPx;
            }
            if (this.boxBackgroundMode == 2) {
                updateCutout();
            }
            if (this.boxBackgroundMode == 1) {
                if (!isEnabled()) {
                    this.boxBackgroundColor = this.disabledFilledBackgroundColor;
                } else if (isHovered && !hasFocus) {
                    this.boxBackgroundColor = this.hoveredFilledBackgroundColor;
                } else if (hasFocus) {
                    this.boxBackgroundColor = this.focusedFilledBackgroundColor;
                } else {
                    this.boxBackgroundColor = this.defaultFilledBackgroundColor;
                }
            }
            applyBoxAttributes();
        }
    }

    private void updateStrokeErrorColor(boolean hasFocus, boolean isHovered) {
        int defaultStrokeErrorColor = this.strokeErrorColor.getDefaultColor();
        int hoveredStrokeErrorColor = this.strokeErrorColor.getColorForState(new int[]{16843623, 16842910}, defaultStrokeErrorColor);
        int focusedStrokeErrorColor = this.strokeErrorColor.getColorForState(new int[]{16843518, 16842910}, defaultStrokeErrorColor);
        if (hasFocus) {
            this.boxStrokeColor = focusedStrokeErrorColor;
        } else if (isHovered) {
            this.boxStrokeColor = hoveredStrokeErrorColor;
        } else {
            this.boxStrokeColor = defaultStrokeErrorColor;
        }
    }

    private void setErrorIconVisible(boolean errorIconVisible) {
        int i = 0;
        this.errorIconView.setVisibility(errorIconVisible ? 0 : 8);
        FrameLayout frameLayout = this.endIconFrame;
        if (errorIconVisible) {
            i = 8;
        }
        frameLayout.setVisibility(i);
        updateSuffixTextViewPadding();
        if (!hasEndIcon()) {
            updateDummyDrawables();
        }
    }

    private boolean isErrorIconVisible() {
        return this.errorIconView.getVisibility() == 0;
    }

    private void refreshIconDrawableState(CheckableImageButton iconView, ColorStateList colorStateList) {
        Drawable icon = iconView.getDrawable();
        if (iconView.getDrawable() != null && colorStateList != null && colorStateList.isStateful()) {
            int color = colorStateList.getColorForState(mergeIconState(iconView), colorStateList.getDefaultColor());
            Drawable icon2 = DrawableCompat.wrap(icon).mutate();
            DrawableCompat.setTintList(icon2, ColorStateList.valueOf(color));
            iconView.setImageDrawable(icon2);
        }
    }

    private int[] mergeIconState(CheckableImageButton iconView) {
        int[] textInputStates = getDrawableState();
        int[] iconStates = iconView.getDrawableState();
        int index = textInputStates.length;
        int[] states = Arrays.copyOf(textInputStates, textInputStates.length + iconStates.length);
        System.arraycopy(iconStates, 0, states, index, iconStates.length);
        return states;
    }

    private void expandHint(boolean animate) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animator.cancel();
        }
        if (!animate || !this.hintAnimationEnabled) {
            this.collapsingTextHelper.setExpansionFraction(0.0f);
        } else {
            animateToExpansionFraction(0.0f);
        }
        if (cutoutEnabled() && ((CutoutDrawable) this.boxBackground).hasCutout()) {
            closeCutout();
        }
        this.hintExpanded = true;
        hidePlaceholderText();
        updatePrefixTextVisibility();
        updateSuffixTextVisibility();
    }

    /* access modifiers changed from: package-private */
    public void animateToExpansionFraction(float target) {
        if (this.collapsingTextHelper.getExpansionFraction() != target) {
            if (this.animator == null) {
                ValueAnimator valueAnimator = new ValueAnimator();
                this.animator = valueAnimator;
                valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                this.animator.setDuration(167L);
                this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    /* class com.google.android.material.textfield.TextInputLayout.C07684 */

                    public void onAnimationUpdate(ValueAnimator animator) {
                        TextInputLayout.this.collapsingTextHelper.setExpansionFraction(((Float) animator.getAnimatedValue()).floatValue());
                    }
                });
            }
            this.animator.setFloatValues(this.collapsingTextHelper.getExpansionFraction(), target);
            this.animator.start();
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean isHintExpanded() {
        return this.hintExpanded;
    }

    /* access modifiers changed from: package-private */
    public final boolean isHelperTextDisplayed() {
        return this.indicatorViewController.helperTextIsDisplayed();
    }

    /* access modifiers changed from: package-private */
    public final int getHintCurrentCollapsedTextColor() {
        return this.collapsingTextHelper.getCurrentCollapsedTextColor();
    }

    /* access modifiers changed from: package-private */
    public final float getHintCollapsedTextHeight() {
        return this.collapsingTextHelper.getCollapsedTextHeight();
    }

    /* access modifiers changed from: package-private */
    public final int getErrorTextCurrentColor() {
        return this.indicatorViewController.getErrorViewCurrentTextColor();
    }

    public static class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final TextInputLayout layout;

        public AccessibilityDelegate(TextInputLayout layout2) {
            this.layout = layout2;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
            String hint;
            String text;
            super.onInitializeAccessibilityNodeInfo(host, info);
            EditText editText = this.layout.getEditText();
            CharSequence inputText = editText != null ? editText.getText() : null;
            CharSequence hintText = this.layout.getHint();
            CharSequence errorText = this.layout.getError();
            CharSequence placeholderText = this.layout.getPlaceholderText();
            int maxCharLimit = this.layout.getCounterMaxLength();
            CharSequence counterOverflowDesc = this.layout.getCounterOverflowDescription();
            boolean showingText = !TextUtils.isEmpty(inputText);
            boolean hasHint = !TextUtils.isEmpty(hintText);
            boolean isHintCollapsed = !this.layout.isHintExpanded();
            boolean showingError = !TextUtils.isEmpty(errorText);
            boolean contentInvalid = showingError || !TextUtils.isEmpty(counterOverflowDesc);
            String hint2 = hasHint ? hintText.toString() : "";
            if (showingText) {
                info.setText(inputText);
                hint = hint2;
            } else if (!TextUtils.isEmpty(hint2)) {
                hint = hint2;
                info.setText(hint);
                if (isHintCollapsed && placeholderText != null) {
                    info.setText(hint + ", " + ((Object) placeholderText));
                }
            } else {
                hint = hint2;
                if (placeholderText != null) {
                    info.setText(placeholderText);
                }
            }
            if (!TextUtils.isEmpty(hint)) {
                if (Build.VERSION.SDK_INT >= 26) {
                    info.setHintText(hint);
                } else {
                    if (showingText) {
                        text = ((Object) inputText) + ", " + hint;
                    } else {
                        text = hint;
                    }
                    info.setText(text);
                }
                info.setShowingHintText(!showingText);
            }
            info.setMaxTextLength((inputText == null || inputText.length() != maxCharLimit) ? -1 : maxCharLimit);
            if (contentInvalid) {
                info.setError(showingError ? errorText : counterOverflowDesc);
            }
            if (Build.VERSION.SDK_INT >= 17 && editText != null) {
                editText.setLabelFor(C0552R.C0555id.textinput_helper_text);
            }
        }
    }
}
