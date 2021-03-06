package com.google.android.material.bottomappbar;

import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;

public class BottomAppBarTopEdgeTreatment extends EdgeTreatment implements Cloneable {
    private static final int ANGLE_LEFT = 180;
    private static final int ANGLE_UP = 270;
    private static final int ARC_HALF = 180;
    private static final int ARC_QUARTER = 90;
    private static final float ROUNDED_CORNER_FAB_OFFSET = 1.75f;
    private float cradleVerticalOffset;
    private float fabCornerSize = -1.0f;
    private float fabDiameter;
    private float fabMargin;
    private float horizontalOffset;
    private float roundedCornerRadius;

    public BottomAppBarTopEdgeTreatment(float fabMargin2, float roundedCornerRadius2, float cradleVerticalOffset2) {
        this.fabMargin = fabMargin2;
        this.roundedCornerRadius = roundedCornerRadius2;
        setCradleVerticalOffset(cradleVerticalOffset2);
        this.horizontalOffset = 0.0f;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float length, float center, float interpolation, ShapePath shapePath) {
        float verticalOffset;
        float arcOffset;
        float f = this.fabDiameter;
        if (f == 0.0f) {
            shapePath.lineTo(length, 0.0f);
            return;
        }
        float cradleRadius = ((this.fabMargin * 2.0f) + f) / 2.0f;
        float roundedCornerOffset = interpolation * this.roundedCornerRadius;
        float middle = center + this.horizontalOffset;
        float verticalOffset2 = (this.cradleVerticalOffset * interpolation) + ((1.0f - interpolation) * cradleRadius);
        if (verticalOffset2 / cradleRadius >= 1.0f) {
            shapePath.lineTo(length, 0.0f);
            return;
        }
        float f2 = this.fabCornerSize;
        float cornerSize = f2 * interpolation;
        boolean useCircleCutout = f2 == -1.0f || Math.abs((f2 * 2.0f) - f) < 0.1f;
        if (!useCircleCutout) {
            arcOffset = 1.75f;
            verticalOffset = 0.0f;
        } else {
            arcOffset = 0.0f;
            verticalOffset = verticalOffset2;
        }
        float distanceBetweenCenters = cradleRadius + roundedCornerOffset;
        float distanceY = verticalOffset + roundedCornerOffset;
        float distanceX = (float) Math.sqrt((double) ((distanceBetweenCenters * distanceBetweenCenters) - (distanceY * distanceY)));
        float leftRoundedCornerCircleX = middle - distanceX;
        float rightRoundedCornerCircleX = middle + distanceX;
        float cornerRadiusArcLength = (float) Math.toDegrees(Math.atan((double) (distanceX / distanceY)));
        float cutoutArcOffset = (90.0f - cornerRadiusArcLength) + arcOffset;
        shapePath.lineTo(leftRoundedCornerCircleX, 0.0f);
        shapePath.addArc(leftRoundedCornerCircleX - roundedCornerOffset, 0.0f, leftRoundedCornerCircleX + roundedCornerOffset, roundedCornerOffset * 2.0f, 270.0f, cornerRadiusArcLength);
        if (useCircleCutout) {
            shapePath.addArc(middle - cradleRadius, (-cradleRadius) - verticalOffset, middle + cradleRadius, cradleRadius - verticalOffset, 180.0f - cutoutArcOffset, (cutoutArcOffset * 2.0f) - 180.0f);
        } else {
            float f3 = this.fabMargin;
            shapePath.addArc(middle - cradleRadius, -(cornerSize + f3), (middle - cradleRadius) + f3 + (cornerSize * 2.0f), f3 + cornerSize, 180.0f - cutoutArcOffset, ((cutoutArcOffset * 2.0f) - 180.0f) / 2.0f);
            float f4 = this.fabMargin;
            shapePath.lineTo((middle + cradleRadius) - (cornerSize + (f4 / 2.0f)), cornerSize + f4);
            float f5 = this.fabMargin;
            shapePath.addArc((middle + cradleRadius) - ((cornerSize * 2.0f) + f5), -(cornerSize + f5), middle + cradleRadius, f5 + cornerSize, 90.0f, cutoutArcOffset - 0.049804688f);
        }
        shapePath.addArc(rightRoundedCornerCircleX - roundedCornerOffset, 0.0f, rightRoundedCornerCircleX + roundedCornerOffset, roundedCornerOffset * 2.0f, 270.0f - cornerRadiusArcLength, cornerRadiusArcLength);
        shapePath.lineTo(length, 0.0f);
    }

    public float getFabDiameter() {
        return this.fabDiameter;
    }

    public void setFabDiameter(float fabDiameter2) {
        this.fabDiameter = fabDiameter2;
    }

    /* access modifiers changed from: package-private */
    public void setHorizontalOffset(float horizontalOffset2) {
        this.horizontalOffset = horizontalOffset2;
    }

    public float getHorizontalOffset() {
        return this.horizontalOffset;
    }

    /* access modifiers changed from: package-private */
    public float getCradleVerticalOffset() {
        return this.cradleVerticalOffset;
    }

    /* access modifiers changed from: package-private */
    public void setCradleVerticalOffset(float cradleVerticalOffset2) {
        if (cradleVerticalOffset2 >= 0.0f) {
            this.cradleVerticalOffset = cradleVerticalOffset2;
            return;
        }
        throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
    }

    /* access modifiers changed from: package-private */
    public float getFabCradleMargin() {
        return this.fabMargin;
    }

    /* access modifiers changed from: package-private */
    public void setFabCradleMargin(float fabMargin2) {
        this.fabMargin = fabMargin2;
    }

    /* access modifiers changed from: package-private */
    public float getFabCradleRoundedCornerRadius() {
        return this.roundedCornerRadius;
    }

    /* access modifiers changed from: package-private */
    public void setFabCradleRoundedCornerRadius(float roundedCornerRadius2) {
        this.roundedCornerRadius = roundedCornerRadius2;
    }

    public float getFabCornerRadius() {
        return this.fabCornerSize;
    }

    public void setFabCornerSize(float size) {
        this.fabCornerSize = size;
    }
}
