package com.google.android.material.switchmaterial;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.C0552R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

public class SwitchMaterial extends SwitchCompat {
    private static final int DEF_STYLE_RES = C0552R.style.Widget_MaterialComponents_CompoundButton_Switch;
    private static final int[][] ENABLED_CHECKED_STATES = {new int[]{16842910, 16842912}, new int[]{16842910, -16842912}, new int[]{-16842910, 16842912}, new int[]{-16842910, -16842912}};
    private final ElevationOverlayProvider elevationOverlayProvider;
    private ColorStateList materialThemeColorsThumbTintList;
    private ColorStateList materialThemeColorsTrackTintList;
    private boolean useMaterialThemeColors;

    public SwitchMaterial(Context context) {
        this(context, null);
    }

    public SwitchMaterial(Context context, AttributeSet attrs) {
        this(context, attrs, C0552R.attr.switchStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    public SwitchMaterial(Context context, AttributeSet attrs, int defStyleAttr) {
        super(MaterialThemeOverlay.wrap(context, attrs, defStyleAttr, r4), attrs, defStyleAttr);
        int i = DEF_STYLE_RES;
        Context context2 = getContext();
        this.elevationOverlayProvider = new ElevationOverlayProvider(context2);
        TypedArray attributes = ThemeEnforcement.obtainStyledAttributes(context2, attrs, C0552R.styleable.SwitchMaterial, defStyleAttr, i, new int[0]);
        this.useMaterialThemeColors = attributes.getBoolean(C0552R.styleable.SwitchMaterial_useMaterialThemeColors, false);
        attributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.useMaterialThemeColors && getThumbTintList() == null) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
        }
        if (this.useMaterialThemeColors && getTrackTintList() == null) {
            setTrackTintList(getMaterialThemeColorsTrackTintList());
        }
    }

    public void setUseMaterialThemeColors(boolean useMaterialThemeColors2) {
        this.useMaterialThemeColors = useMaterialThemeColors2;
        if (useMaterialThemeColors2) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
            setTrackTintList(getMaterialThemeColorsTrackTintList());
            return;
        }
        setThumbTintList(null);
        setTrackTintList(null);
    }

    public boolean isUseMaterialThemeColors() {
        return this.useMaterialThemeColors;
    }

    private ColorStateList getMaterialThemeColorsThumbTintList() {
        if (this.materialThemeColorsThumbTintList == null) {
            int colorSurface = MaterialColors.getColor(this, C0552R.attr.colorSurface);
            int colorControlActivated = MaterialColors.getColor(this, C0552R.attr.colorControlActivated);
            float thumbElevation = getResources().getDimension(C0552R.dimen.mtrl_switch_thumb_elevation);
            if (this.elevationOverlayProvider.isThemeElevationOverlayEnabled()) {
                thumbElevation += ViewUtils.getParentAbsoluteElevation(this);
            }
            int colorThumbOff = this.elevationOverlayProvider.compositeOverlayIfNeeded(colorSurface, thumbElevation);
            int[][] iArr = ENABLED_CHECKED_STATES;
            int[] switchThumbColorsList = new int[iArr.length];
            switchThumbColorsList[0] = MaterialColors.layer(colorSurface, colorControlActivated, 1.0f);
            switchThumbColorsList[1] = colorThumbOff;
            switchThumbColorsList[2] = MaterialColors.layer(colorSurface, colorControlActivated, 0.38f);
            switchThumbColorsList[3] = colorThumbOff;
            this.materialThemeColorsThumbTintList = new ColorStateList(iArr, switchThumbColorsList);
        }
        return this.materialThemeColorsThumbTintList;
    }

    private ColorStateList getMaterialThemeColorsTrackTintList() {
        if (this.materialThemeColorsTrackTintList == null) {
            int[][] iArr = ENABLED_CHECKED_STATES;
            int[] switchTrackColorsList = new int[iArr.length];
            int colorSurface = MaterialColors.getColor(this, C0552R.attr.colorSurface);
            int colorControlActivated = MaterialColors.getColor(this, C0552R.attr.colorControlActivated);
            int colorOnSurface = MaterialColors.getColor(this, C0552R.attr.colorOnSurface);
            switchTrackColorsList[0] = MaterialColors.layer(colorSurface, colorControlActivated, 0.54f);
            switchTrackColorsList[1] = MaterialColors.layer(colorSurface, colorOnSurface, 0.32f);
            switchTrackColorsList[2] = MaterialColors.layer(colorSurface, colorControlActivated, 0.12f);
            switchTrackColorsList[3] = MaterialColors.layer(colorSurface, colorOnSurface, 0.12f);
            this.materialThemeColorsTrackTintList = new ColorStateList(iArr, switchTrackColorsList);
        }
        return this.materialThemeColorsTrackTintList;
    }
}
