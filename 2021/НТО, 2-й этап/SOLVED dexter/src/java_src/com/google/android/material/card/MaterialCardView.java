package com.google.android.material.card;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import com.google.android.material.C0552R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

public class MaterialCardView extends CardView implements Checkable, Shapeable {
    private static final String ACCESSIBILITY_CLASS_NAME = "androidx.cardview.widget.CardView";
    private static final int[] CHECKABLE_STATE_SET = {16842911};
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final int DEF_STYLE_RES = C0552R.style.Widget_MaterialComponents_CardView;
    private static final int[] DRAGGED_STATE_SET = {C0552R.attr.state_dragged};
    private static final String LOG_TAG = "MaterialCardView";
    private final MaterialCardViewHelper cardViewHelper;
    private boolean checked;
    private boolean dragged;
    private boolean isParentCardViewDoneInitializing;
    private OnCheckedChangeListener onCheckedChangeListener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(MaterialCardView materialCardView, boolean z);
    }

    public MaterialCardView(Context context) {
        this(context, null);
    }

    public MaterialCardView(Context context, AttributeSet attrs) {
        this(context, attrs, C0552R.attr.materialCardViewStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    public MaterialCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(MaterialThemeOverlay.wrap(context, attrs, defStyleAttr, r6), attrs, defStyleAttr);
        int i = DEF_STYLE_RES;
        this.checked = false;
        this.dragged = false;
        this.isParentCardViewDoneInitializing = true;
        TypedArray attributes = ThemeEnforcement.obtainStyledAttributes(getContext(), attrs, C0552R.styleable.MaterialCardView, defStyleAttr, i, new int[0]);
        MaterialCardViewHelper materialCardViewHelper = new MaterialCardViewHelper(this, attrs, defStyleAttr, i);
        this.cardViewHelper = materialCardViewHelper;
        materialCardViewHelper.setCardBackgroundColor(super.getCardBackgroundColor());
        materialCardViewHelper.setUserContentPadding(super.getContentPaddingLeft(), super.getContentPaddingTop(), super.getContentPaddingRight(), super.getContentPaddingBottom());
        materialCardViewHelper.loadFromAttributes(attributes);
        attributes.recycle();
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(ACCESSIBILITY_CLASS_NAME);
        info.setCheckable(isCheckable());
        info.setClickable(isClickable());
        info.setChecked(isChecked());
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(ACCESSIBILITY_CLASS_NAME);
        accessibilityEvent.setChecked(isChecked());
    }

    /* access modifiers changed from: protected */
    @Override // androidx.cardview.widget.CardView
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.cardViewHelper.onMeasure(getMeasuredWidth(), getMeasuredHeight());
    }

    public void setStrokeColor(int strokeColor) {
        this.cardViewHelper.setStrokeColor(ColorStateList.valueOf(strokeColor));
    }

    public void setStrokeColor(ColorStateList strokeColor) {
        this.cardViewHelper.setStrokeColor(strokeColor);
    }

    @Deprecated
    public int getStrokeColor() {
        return this.cardViewHelper.getStrokeColor();
    }

    public ColorStateList getStrokeColorStateList() {
        return this.cardViewHelper.getStrokeColorStateList();
    }

    public void setStrokeWidth(int strokeWidth) {
        this.cardViewHelper.setStrokeWidth(strokeWidth);
    }

    public int getStrokeWidth() {
        return this.cardViewHelper.getStrokeWidth();
    }

    @Override // androidx.cardview.widget.CardView
    public void setRadius(float radius) {
        super.setRadius(radius);
        this.cardViewHelper.setCornerRadius(radius);
    }

    @Override // androidx.cardview.widget.CardView
    public float getRadius() {
        return this.cardViewHelper.getCornerRadius();
    }

    /* access modifiers changed from: package-private */
    public float getCardViewRadius() {
        return super.getRadius();
    }

    public void setProgress(float progress) {
        this.cardViewHelper.setProgress(progress);
    }

    public float getProgress() {
        return this.cardViewHelper.getProgress();
    }

    @Override // androidx.cardview.widget.CardView
    public void setContentPadding(int left, int top, int right, int bottom) {
        this.cardViewHelper.setUserContentPadding(left, top, right, bottom);
    }

    /* access modifiers changed from: package-private */
    public void setAncestorContentPadding(int left, int top, int right, int bottom) {
        super.setContentPadding(left, top, right, bottom);
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingLeft() {
        return this.cardViewHelper.getUserContentPadding().left;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingTop() {
        return this.cardViewHelper.getUserContentPadding().top;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingRight() {
        return this.cardViewHelper.getUserContentPadding().right;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingBottom() {
        return this.cardViewHelper.getUserContentPadding().bottom;
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(int color) {
        this.cardViewHelper.setCardBackgroundColor(ColorStateList.valueOf(color));
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(ColorStateList color) {
        this.cardViewHelper.setCardBackgroundColor(color);
    }

    @Override // androidx.cardview.widget.CardView
    public ColorStateList getCardBackgroundColor() {
        return this.cardViewHelper.getCardBackgroundColor();
    }

    public void setCardForegroundColor(ColorStateList foregroundColor) {
        this.cardViewHelper.setCardForegroundColor(foregroundColor);
    }

    public ColorStateList getCardForegroundColor() {
        return this.cardViewHelper.getCardForegroundColor();
    }

    public void setClickable(boolean clickable) {
        super.setClickable(clickable);
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        if (materialCardViewHelper != null) {
            materialCardViewHelper.updateClickable();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this, this.cardViewHelper.getBackground());
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardElevation(float elevation) {
        super.setCardElevation(elevation);
        this.cardViewHelper.updateElevation();
    }

    @Override // androidx.cardview.widget.CardView
    public void setMaxCardElevation(float maxCardElevation) {
        super.setMaxCardElevation(maxCardElevation);
        this.cardViewHelper.updateInsets();
    }

    @Override // androidx.cardview.widget.CardView
    public void setUseCompatPadding(boolean useCompatPadding) {
        super.setUseCompatPadding(useCompatPadding);
        this.cardViewHelper.updateInsets();
        this.cardViewHelper.updateContentPadding();
    }

    @Override // androidx.cardview.widget.CardView
    public void setPreventCornerOverlap(boolean preventCornerOverlap) {
        super.setPreventCornerOverlap(preventCornerOverlap);
        this.cardViewHelper.updateInsets();
        this.cardViewHelper.updateContentPadding();
    }

    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (this.isParentCardViewDoneInitializing) {
            if (!this.cardViewHelper.isBackgroundOverwritten()) {
                Log.i(LOG_TAG, "Setting a custom background is not supported.");
                this.cardViewHelper.setBackgroundOverwritten(true);
            }
            super.setBackgroundDrawable(drawable);
        }
    }

    /* access modifiers changed from: package-private */
    public void setBackgroundInternal(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked2) {
        if (this.checked != checked2) {
            toggle();
        }
    }

    public void setDragged(boolean dragged2) {
        if (this.dragged != dragged2) {
            this.dragged = dragged2;
            refreshDrawableState();
            forceRippleRedrawIfNeeded();
            invalidate();
        }
    }

    public boolean isDragged() {
        return this.dragged;
    }

    public boolean isCheckable() {
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        return materialCardViewHelper != null && materialCardViewHelper.isCheckable();
    }

    public void setCheckable(boolean checkable) {
        this.cardViewHelper.setCheckable(checkable);
    }

    public void toggle() {
        if (isCheckable() && isEnabled()) {
            this.checked = !this.checked;
            refreshDrawableState();
            forceRippleRedrawIfNeeded();
            OnCheckedChangeListener onCheckedChangeListener2 = this.onCheckedChangeListener;
            if (onCheckedChangeListener2 != null) {
                onCheckedChangeListener2.onCheckedChanged(this, this.checked);
            }
        }
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int extraSpace) {
        int[] drawableState = super.onCreateDrawableState(extraSpace + 3);
        if (isCheckable()) {
            mergeDrawableStates(drawableState, CHECKABLE_STATE_SET);
        }
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        if (isDragged()) {
            mergeDrawableStates(drawableState, DRAGGED_STATE_SET);
        }
        return drawableState;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.onCheckedChangeListener = listener;
    }

    public void setRippleColor(ColorStateList rippleColor) {
        this.cardViewHelper.setRippleColor(rippleColor);
    }

    public void setRippleColorResource(int rippleColorResourceId) {
        this.cardViewHelper.setRippleColor(AppCompatResources.getColorStateList(getContext(), rippleColorResourceId));
    }

    public ColorStateList getRippleColor() {
        return this.cardViewHelper.getRippleColor();
    }

    public Drawable getCheckedIcon() {
        return this.cardViewHelper.getCheckedIcon();
    }

    public void setCheckedIconResource(int id) {
        this.cardViewHelper.setCheckedIcon(AppCompatResources.getDrawable(getContext(), id));
    }

    public void setCheckedIcon(Drawable checkedIcon) {
        this.cardViewHelper.setCheckedIcon(checkedIcon);
    }

    public ColorStateList getCheckedIconTint() {
        return this.cardViewHelper.getCheckedIconTint();
    }

    public void setCheckedIconTint(ColorStateList checkedIconTint) {
        this.cardViewHelper.setCheckedIconTint(checkedIconTint);
    }

    public int getCheckedIconSize() {
        return this.cardViewHelper.getCheckedIconSize();
    }

    public void setCheckedIconSize(int checkedIconSize) {
        this.cardViewHelper.setCheckedIconSize(checkedIconSize);
    }

    public void setCheckedIconSizeResource(int checkedIconSizeResId) {
        if (checkedIconSizeResId != 0) {
            this.cardViewHelper.setCheckedIconSize(getResources().getDimensionPixelSize(checkedIconSizeResId));
        }
    }

    public int getCheckedIconMargin() {
        return this.cardViewHelper.getCheckedIconMargin();
    }

    public void setCheckedIconMargin(int checkedIconMargin) {
        this.cardViewHelper.setCheckedIconMargin(checkedIconMargin);
    }

    public void setCheckedIconMarginResource(int checkedIconMarginResId) {
        if (checkedIconMarginResId != -1) {
            this.cardViewHelper.setCheckedIconMargin(getResources().getDimensionPixelSize(checkedIconMarginResId));
        }
    }

    private RectF getBoundsAsRectF() {
        RectF boundsRectF = new RectF();
        boundsRectF.set(this.cardViewHelper.getBackground().getBounds());
        return boundsRectF;
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(shapeAppearanceModel.isRoundRect(getBoundsAsRectF()));
        }
        this.cardViewHelper.setShapeAppearanceModel(shapeAppearanceModel);
    }

    @Override // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.cardViewHelper.getShapeAppearanceModel();
    }

    private void forceRippleRedrawIfNeeded() {
        if (Build.VERSION.SDK_INT > 26) {
            this.cardViewHelper.forceRippleRedraw();
        }
    }
}
