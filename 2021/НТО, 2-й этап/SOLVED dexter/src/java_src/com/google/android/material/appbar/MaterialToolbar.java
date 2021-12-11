package com.google.android.material.appbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.C0552R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

public class MaterialToolbar extends Toolbar {
    private static final int DEF_STYLE_RES = C0552R.style.Widget_MaterialComponents_Toolbar;
    private Integer navigationIconTint;
    private boolean subtitleCentered;
    private boolean titleCentered;

    public MaterialToolbar(Context context) {
        this(context, null);
    }

    public MaterialToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, C0552R.attr.toolbarStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    public MaterialToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(MaterialThemeOverlay.wrap(context, attrs, defStyleAttr, r4), attrs, defStyleAttr);
        int i = DEF_STYLE_RES;
        Context context2 = getContext();
        TypedArray a = ThemeEnforcement.obtainStyledAttributes(context2, attrs, C0552R.styleable.MaterialToolbar, defStyleAttr, i, new int[0]);
        if (a.hasValue(C0552R.styleable.MaterialToolbar_navigationIconTint)) {
            setNavigationIconTint(a.getColor(C0552R.styleable.MaterialToolbar_navigationIconTint, -1));
        }
        this.titleCentered = a.getBoolean(C0552R.styleable.MaterialToolbar_titleCentered, false);
        this.subtitleCentered = a.getBoolean(C0552R.styleable.MaterialToolbar_subtitleCentered, false);
        a.recycle();
        initBackground(context2);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.Toolbar
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        maybeCenterTitleViews();
    }

    private void maybeCenterTitleViews() {
        if (this.titleCentered || this.subtitleCentered) {
            TextView titleTextView = ToolbarUtils.getTitleTextView(this);
            TextView subtitleTextView = ToolbarUtils.getSubtitleTextView(this);
            if (titleTextView != null || subtitleTextView != null) {
                Pair<Integer, Integer> titleBoundLimits = calculateTitleBoundLimits(titleTextView, subtitleTextView);
                if (this.titleCentered && titleTextView != null) {
                    layoutTitleCenteredHorizontally(titleTextView, titleBoundLimits);
                }
                if (this.subtitleCentered && subtitleTextView != null) {
                    layoutTitleCenteredHorizontally(subtitleTextView, titleBoundLimits);
                }
            }
        }
    }

    private Pair<Integer, Integer> calculateTitleBoundLimits(TextView titleTextView, TextView subtitleTextView) {
        int width = getMeasuredWidth();
        int midpoint = width / 2;
        int leftLimit = getPaddingLeft();
        int rightLimit = width - getPaddingRight();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (!(child.getVisibility() == 8 || child == titleTextView || child == subtitleTextView)) {
                if (child.getRight() < midpoint && child.getRight() > leftLimit) {
                    leftLimit = child.getRight();
                }
                if (child.getLeft() > midpoint && child.getLeft() < rightLimit) {
                    rightLimit = child.getLeft();
                }
            }
        }
        return new Pair<>(Integer.valueOf(leftLimit), Integer.valueOf(rightLimit));
    }

    private void layoutTitleCenteredHorizontally(View titleView, Pair<Integer, Integer> titleBoundLimits) {
        int width = getMeasuredWidth();
        int titleWidth = titleView.getMeasuredWidth();
        int titleLeft = (width / 2) - (titleWidth / 2);
        int titleRight = titleLeft + titleWidth;
        int overlap = Math.max(Math.max(((Integer) titleBoundLimits.first).intValue() - titleLeft, 0), Math.max(titleRight - ((Integer) titleBoundLimits.second).intValue(), 0));
        if (overlap > 0) {
            titleLeft += overlap;
            titleRight -= overlap;
            titleView.measure(View.MeasureSpec.makeMeasureSpec(titleRight - titleLeft, BasicMeasure.EXACTLY), titleView.getMeasuredHeightAndState());
        }
        titleView.layout(titleLeft, titleView.getTop(), titleRight, titleView.getBottom());
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    public void setElevation(float elevation) {
        super.setElevation(elevation);
        MaterialShapeUtils.setElevation(this, elevation);
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        super.setNavigationIcon(maybeTintNavigationIcon(drawable));
    }

    public void setNavigationIconTint(int navigationIconTint2) {
        this.navigationIconTint = Integer.valueOf(navigationIconTint2);
        Drawable navigationIcon = getNavigationIcon();
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
    }

    public void setTitleCentered(boolean titleCentered2) {
        if (this.titleCentered != titleCentered2) {
            this.titleCentered = titleCentered2;
            requestLayout();
        }
    }

    public boolean isTitleCentered() {
        return this.titleCentered;
    }

    public void setSubtitleCentered(boolean subtitleCentered2) {
        if (this.subtitleCentered != subtitleCentered2) {
            this.subtitleCentered = subtitleCentered2;
            requestLayout();
        }
    }

    public boolean isSubtitleCentered() {
        return this.subtitleCentered;
    }

    private void initBackground(Context context) {
        Drawable background = getBackground();
        if (background == null || (background instanceof ColorDrawable)) {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
            materialShapeDrawable.setFillColor(ColorStateList.valueOf(background != null ? ((ColorDrawable) background).getColor() : 0));
            materialShapeDrawable.initializeElevationOverlay(context);
            materialShapeDrawable.setElevation(ViewCompat.getElevation(this));
            ViewCompat.setBackground(this, materialShapeDrawable);
        }
    }

    private Drawable maybeTintNavigationIcon(Drawable navigationIcon) {
        if (navigationIcon == null || this.navigationIconTint == null) {
            return navigationIcon;
        }
        Drawable wrappedNavigationIcon = DrawableCompat.wrap(navigationIcon);
        DrawableCompat.setTint(wrappedNavigationIcon, this.navigationIconTint.intValue());
        return wrappedNavigationIcon;
    }
}
