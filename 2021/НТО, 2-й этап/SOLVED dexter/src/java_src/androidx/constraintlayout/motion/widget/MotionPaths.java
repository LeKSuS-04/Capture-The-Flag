package androidx.constraintlayout.motion.widget;

import android.view.View;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.Arrays;
import java.util.LinkedHashMap;

/* access modifiers changed from: package-private */
public class MotionPaths implements Comparable<MotionPaths> {
    static final int CARTESIAN = 0;
    public static final boolean DEBUG = false;
    static final int OFF_HEIGHT = 4;
    static final int OFF_PATH_ROTATE = 5;
    static final int OFF_POSITION = 0;
    static final int OFF_WIDTH = 3;
    static final int OFF_X = 1;
    static final int OFF_Y = 2;
    public static final boolean OLD_WAY = false;
    static final int PERPENDICULAR = 1;
    static final int SCREEN = 2;
    public static final String TAG = "MotionPaths";
    static String[] names = {"position", "x", "y", "width", "height", "pathRotate"};
    LinkedHashMap<String, ConstraintAttribute> attributes;
    float height;
    int mAnimateCircleAngleTo;
    int mAnimateRelativeTo;
    int mDrawPath;
    Easing mKeyFrameEasing;
    int mMode;
    int mPathMotionArc;
    float mPathRotate;
    float mProgress;
    float mRelativeAngle;
    MotionController mRelativeToController;
    double[] mTempDelta;
    double[] mTempValue;
    float position;
    float time;
    float width;

    /* renamed from: x */
    float f53x;

    /* renamed from: y */
    float f54y;

    public MotionPaths() {
        this.mDrawPath = 0;
        this.mPathRotate = Float.NaN;
        this.mProgress = Float.NaN;
        this.mPathMotionArc = Key.UNSET;
        this.mAnimateRelativeTo = Key.UNSET;
        this.mRelativeAngle = Float.NaN;
        this.mRelativeToController = null;
        this.attributes = new LinkedHashMap<>();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
    }

    /* access modifiers changed from: package-private */
    public void initCartesian(KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        float position2 = ((float) c.mFramePosition) / 100.0f;
        this.time = position2;
        this.mDrawPath = c.mDrawPath;
        float scaleWidth = Float.isNaN(c.mPercentWidth) ? position2 : c.mPercentWidth;
        float scaleHeight = Float.isNaN(c.mPercentHeight) ? position2 : c.mPercentHeight;
        float f = endTimePoint.width;
        float f2 = startTimePoint.width;
        float scaleX = f - f2;
        float f3 = endTimePoint.height;
        float f4 = startTimePoint.height;
        float scaleY = f3 - f4;
        this.position = this.time;
        float f5 = startTimePoint.f53x;
        float position3 = startTimePoint.f54y;
        float endCenterX = endTimePoint.f53x + (f / 2.0f);
        float endCenterY = endTimePoint.f54y + (f3 / 2.0f);
        float pathVectorX = endCenterX - (f5 + (f2 / 2.0f));
        float pathVectorY = endCenterY - (position3 + (f4 / 2.0f));
        this.f53x = (float) ((int) ((f5 + (pathVectorX * position2)) - ((scaleX * scaleWidth) / 2.0f)));
        this.f54y = (float) ((int) ((position3 + (pathVectorY * position2)) - ((scaleY * scaleHeight) / 2.0f)));
        this.width = (float) ((int) (f2 + (scaleX * scaleWidth)));
        this.height = (float) ((int) (f4 + (scaleY * scaleHeight)));
        float dxdx = Float.isNaN(c.mPercentX) ? position2 : c.mPercentX;
        float dydx = Float.isNaN(c.mAltPercentY) ? 0.0f : c.mAltPercentY;
        float dydy = Float.isNaN(c.mPercentY) ? position2 : c.mPercentY;
        float dxdy = Float.isNaN(c.mAltPercentX) ? 0.0f : c.mAltPercentX;
        this.mMode = 0;
        this.f53x = (float) ((int) (((startTimePoint.f53x + (pathVectorX * dxdx)) + (pathVectorY * dxdy)) - ((scaleX * scaleWidth) / 2.0f)));
        this.f54y = (float) ((int) (((startTimePoint.f54y + (pathVectorX * dydx)) + (pathVectorY * dydy)) - ((scaleY * scaleHeight) / 2.0f)));
        this.mKeyFrameEasing = Easing.getInterpolator(c.mTransitionEasing);
        this.mPathMotionArc = c.mPathMotionArc;
    }

    public MotionPaths(int parentWidth, int parentHeight, KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        this.mDrawPath = 0;
        this.mPathRotate = Float.NaN;
        this.mProgress = Float.NaN;
        this.mPathMotionArc = Key.UNSET;
        this.mAnimateRelativeTo = Key.UNSET;
        this.mRelativeAngle = Float.NaN;
        this.mRelativeToController = null;
        this.attributes = new LinkedHashMap<>();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
        if (startTimePoint.mAnimateRelativeTo != Key.UNSET) {
            initPolar(parentWidth, parentHeight, c, startTimePoint, endTimePoint);
            return;
        }
        switch (c.mPositionType) {
            case 1:
                initPath(c, startTimePoint, endTimePoint);
                return;
            case 2:
                initScreen(parentWidth, parentHeight, c, startTimePoint, endTimePoint);
                return;
            default:
                initCartesian(c, startTimePoint, endTimePoint);
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void initPolar(int parentWidth, int parentHeight, KeyPosition c, MotionPaths s, MotionPaths e) {
        float f;
        float f2;
        float f3;
        float position2 = ((float) c.mFramePosition) / 100.0f;
        this.time = position2;
        this.mDrawPath = c.mDrawPath;
        this.mMode = c.mPositionType;
        float scaleWidth = Float.isNaN(c.mPercentWidth) ? position2 : c.mPercentWidth;
        float scaleHeight = Float.isNaN(c.mPercentHeight) ? position2 : c.mPercentHeight;
        float f4 = e.width;
        float f5 = s.width;
        float f6 = e.height;
        float f7 = s.height;
        this.position = this.time;
        this.width = (float) ((int) (f5 + ((f4 - f5) * scaleWidth)));
        this.height = (float) ((int) (f7 + ((f6 - f7) * scaleHeight)));
        float f8 = 1.0f - position2;
        switch (c.mPositionType) {
            case 1:
                float f9 = Float.isNaN(c.mPercentX) ? position2 : c.mPercentX;
                float f10 = e.f53x;
                float f11 = s.f53x;
                this.f53x = (f9 * (f10 - f11)) + f11;
                float f12 = Float.isNaN(c.mPercentY) ? position2 : c.mPercentY;
                float f13 = e.f54y;
                float f14 = s.f54y;
                this.f54y = (f12 * (f13 - f14)) + f14;
                break;
            case 2:
                if (Float.isNaN(c.mPercentX)) {
                    float f15 = e.f53x;
                    float f16 = s.f53x;
                    f = ((f15 - f16) * position2) + f16;
                } else {
                    f = c.mPercentX * Math.min(scaleHeight, scaleWidth);
                }
                this.f53x = f;
                if (Float.isNaN(c.mPercentY)) {
                    float f17 = e.f54y;
                    float f18 = s.f54y;
                    f2 = ((f17 - f18) * position2) + f18;
                } else {
                    f2 = c.mPercentY;
                }
                this.f54y = f2;
                break;
            default:
                if (Float.isNaN(c.mPercentX)) {
                    f3 = position2;
                } else {
                    f3 = c.mPercentX;
                }
                float f19 = e.f53x;
                float f20 = s.f53x;
                this.f53x = (f3 * (f19 - f20)) + f20;
                float f21 = Float.isNaN(c.mPercentY) ? position2 : c.mPercentY;
                float f22 = e.f54y;
                float f23 = s.f54y;
                this.f54y = (f21 * (f22 - f23)) + f23;
                break;
        }
        this.mAnimateRelativeTo = s.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(c.mTransitionEasing);
        this.mPathMotionArc = c.mPathMotionArc;
    }

    public void setupRelative(MotionController mc, MotionPaths relative) {
        double dx = (double) (((this.f53x + (this.width / 2.0f)) - relative.f53x) - (relative.width / 2.0f));
        double dy = (double) (((this.f54y + (this.height / 2.0f)) - relative.f54y) - (relative.height / 2.0f));
        this.mRelativeToController = mc;
        this.f53x = (float) Math.hypot(dy, dx);
        if (Float.isNaN(this.mRelativeAngle)) {
            this.f54y = (float) (Math.atan2(dy, dx) + 1.5707963267948966d);
        } else {
            this.f54y = (float) Math.toRadians((double) this.mRelativeAngle);
        }
    }

    /* access modifiers changed from: package-private */
    public void initScreen(int parentWidth, int parentHeight, KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        float position2 = ((float) c.mFramePosition) / 100.0f;
        this.time = position2;
        this.mDrawPath = c.mDrawPath;
        float scaleWidth = Float.isNaN(c.mPercentWidth) ? position2 : c.mPercentWidth;
        float scaleHeight = Float.isNaN(c.mPercentHeight) ? position2 : c.mPercentHeight;
        float f = endTimePoint.width;
        float f2 = startTimePoint.width;
        float scaleX = f - f2;
        float f3 = endTimePoint.height;
        float f4 = startTimePoint.height;
        float scaleY = f3 - f4;
        this.position = this.time;
        float f5 = startTimePoint.f53x;
        float position3 = startTimePoint.f54y;
        float endCenterX = endTimePoint.f53x + (f / 2.0f);
        float endCenterY = endTimePoint.f54y + (f3 / 2.0f);
        this.f53x = (float) ((int) ((f5 + ((endCenterX - (f5 + (f2 / 2.0f))) * position2)) - ((scaleX * scaleWidth) / 2.0f)));
        this.f54y = (float) ((int) ((position3 + ((endCenterY - (position3 + (f4 / 2.0f))) * position2)) - ((scaleY * scaleHeight) / 2.0f)));
        this.width = (float) ((int) (f2 + (scaleX * scaleWidth)));
        this.height = (float) ((int) (f4 + (scaleY * scaleHeight)));
        this.mMode = 2;
        if (!Float.isNaN(c.mPercentX)) {
            this.f53x = (float) ((int) (c.mPercentX * ((float) ((int) (((float) parentWidth) - this.width)))));
        }
        if (!Float.isNaN(c.mPercentY)) {
            this.f54y = (float) ((int) (c.mPercentY * ((float) ((int) (((float) parentHeight) - this.height)))));
        }
        this.mAnimateRelativeTo = this.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(c.mTransitionEasing);
        this.mPathMotionArc = c.mPathMotionArc;
    }

    /* access modifiers changed from: package-private */
    public void initPath(KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        float position2 = ((float) c.mFramePosition) / 100.0f;
        this.time = position2;
        this.mDrawPath = c.mDrawPath;
        float scaleWidth = Float.isNaN(c.mPercentWidth) ? position2 : c.mPercentWidth;
        float scaleHeight = Float.isNaN(c.mPercentHeight) ? position2 : c.mPercentHeight;
        float scaleX = endTimePoint.width - startTimePoint.width;
        float scaleY = endTimePoint.height - startTimePoint.height;
        this.position = this.time;
        float path = Float.isNaN(c.mPercentX) ? position2 : c.mPercentX;
        float f = startTimePoint.f53x;
        float f2 = startTimePoint.width;
        float f3 = startTimePoint.f54y;
        float position3 = startTimePoint.height;
        float pathVectorX = (endTimePoint.f53x + (endTimePoint.width / 2.0f)) - ((f2 / 2.0f) + f);
        float pathVectorY = (endTimePoint.f54y + (endTimePoint.height / 2.0f)) - (f3 + (position3 / 2.0f));
        this.f53x = (float) ((int) ((f + (pathVectorX * path)) - ((scaleX * scaleWidth) / 2.0f)));
        this.f54y = (float) ((int) ((f3 + (pathVectorY * path)) - ((scaleY * scaleHeight) / 2.0f)));
        this.width = (float) ((int) (f2 + (scaleX * scaleWidth)));
        this.height = (float) ((int) (position3 + (scaleY * scaleHeight)));
        float perpendicular = Float.isNaN(c.mPercentY) ? 0.0f : c.mPercentY;
        float normalY = pathVectorX * perpendicular;
        this.mMode = 1;
        float f4 = (float) ((int) ((startTimePoint.f53x + (pathVectorX * path)) - ((scaleX * scaleWidth) / 2.0f)));
        this.f53x = f4;
        float f5 = (float) ((int) ((startTimePoint.f54y + (pathVectorY * path)) - ((scaleY * scaleHeight) / 2.0f)));
        this.f54y = f5;
        this.f53x = f4 + ((-pathVectorY) * perpendicular);
        this.f54y = f5 + normalY;
        this.mAnimateRelativeTo = this.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(c.mTransitionEasing);
        this.mPathMotionArc = c.mPathMotionArc;
    }

    private static final float xRotate(float sin, float cos, float cx, float cy, float x, float y) {
        return (((x - cx) * cos) - ((y - cy) * sin)) + cx;
    }

    private static final float yRotate(float sin, float cos, float cx, float cy, float x, float y) {
        return ((x - cx) * sin) + ((y - cy) * cos) + cy;
    }

    private boolean diff(float a, float b) {
        if (Float.isNaN(a) || Float.isNaN(b)) {
            if (Float.isNaN(a) != Float.isNaN(b)) {
                return true;
            }
            return false;
        } else if (Math.abs(a - b) > 1.0E-6f) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void different(MotionPaths points, boolean[] mask, String[] custom, boolean arcMode) {
        boolean diffx = diff(this.f53x, points.f53x);
        boolean diffy = diff(this.f54y, points.f54y);
        int c = 0 + 1;
        mask[0] = mask[0] | diff(this.position, points.position);
        int c2 = c + 1;
        mask[c] = mask[c] | diffx | diffy | arcMode;
        int c3 = c2 + 1;
        mask[c2] = mask[c2] | diffx | diffy | arcMode;
        int c4 = c3 + 1;
        mask[c3] = mask[c3] | diff(this.width, points.width);
        int i = c4 + 1;
        mask[c4] = mask[c4] | diff(this.height, points.height);
    }

    /* access modifiers changed from: package-private */
    public void getCenter(double p, int[] toUse, double[] data, float[] point, int offset) {
        float f;
        float v_x = this.f53x;
        float v_y = this.f54y;
        float v_width = this.width;
        float v_height = this.height;
        for (int i = 0; i < toUse.length; i++) {
            float value = (float) data[i];
            switch (toUse[i]) {
                case 1:
                    v_x = value;
                    break;
                case 2:
                    v_y = value;
                    break;
                case 3:
                    v_width = value;
                    break;
                case 4:
                    v_height = value;
                    break;
            }
        }
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] pos = new float[2];
            motionController.getCenter(p, pos, new float[2]);
            float rx = pos[0];
            float ry = pos[1];
            float v_x2 = (float) ((((double) rx) + (((double) v_x) * Math.sin((double) v_y))) - ((double) (v_width / 2.0f)));
            f = 2.0f;
            v_y = (float) ((((double) ry) - (((double) v_x) * Math.cos((double) v_y))) - ((double) (v_height / 2.0f)));
            v_x = v_x2;
        } else {
            f = 2.0f;
        }
        point[offset] = (v_width / f) + v_x + 0.0f;
        point[offset + 1] = (v_height / f) + v_y + 0.0f;
    }

    /* JADX INFO: Multiple debug info for r0v6 float: [D('pos' float[]), D('drx' float)] */
    /* JADX INFO: Multiple debug info for r2v3 float: [D('v_x' float), D('dry' float)] */
    /* access modifiers changed from: package-private */
    public void getCenter(double p, int[] toUse, double[] data, float[] point, double[] vdata, float[] velocity) {
        float v_x = this.f53x;
        float v_y = this.f54y;
        float v_width = this.width;
        float v_height = this.height;
        float dv_x = 0.0f;
        float dv_y = 0.0f;
        float dv_width = 0.0f;
        float dv_height = 0.0f;
        for (int i = 0; i < toUse.length; i++) {
            float value = (float) data[i];
            float dvalue = (float) vdata[i];
            switch (toUse[i]) {
                case 1:
                    v_x = value;
                    dv_x = dvalue;
                    break;
                case 2:
                    v_y = value;
                    dv_y = dvalue;
                    break;
                case 3:
                    v_width = value;
                    dv_width = dvalue;
                    break;
                case 4:
                    v_height = value;
                    dv_height = dvalue;
                    break;
            }
        }
        float dangle = (dv_width / 2.0f) + dv_x;
        float dpos_y = (dv_height / 2.0f) + dv_y;
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] pos = new float[2];
            float[] vel = new float[2];
            motionController.getCenter(p, pos, vel);
            float rx = pos[0];
            float ry = pos[1];
            float drx = vel[0];
            float dry = vel[1];
            float v_x2 = (float) ((((double) rx) + (((double) v_x) * Math.sin((double) v_y))) - ((double) (v_width / 2.0f)));
            float v_y2 = (float) ((((double) ry) - (((double) v_x) * Math.cos((double) v_y))) - ((double) (v_height / 2.0f)));
            float dpos_x = (float) (((double) drx) + (((double) dv_x) * Math.sin((double) v_y)) + (Math.cos((double) v_y) * ((double) dv_y)));
            dpos_y = (float) ((((double) dry) - (((double) dv_x) * Math.cos((double) v_y))) + (Math.sin((double) v_y) * ((double) dv_y)));
            v_y = v_y2;
            dangle = dpos_x;
            v_x = v_x2;
        }
        point[0] = (v_width / 2.0f) + v_x + 0.0f;
        point[1] = (v_height / 2.0f) + v_y + 0.0f;
        velocity[0] = dangle;
        velocity[1] = dpos_y;
    }

    /* access modifiers changed from: package-private */
    public void getCenterVelocity(double p, int[] toUse, double[] data, float[] point, int offset) {
        float f;
        float v_x = this.f53x;
        float v_y = this.f54y;
        float v_width = this.width;
        float v_height = this.height;
        for (int i = 0; i < toUse.length; i++) {
            float value = (float) data[i];
            switch (toUse[i]) {
                case 1:
                    v_x = value;
                    break;
                case 2:
                    v_y = value;
                    break;
                case 3:
                    v_width = value;
                    break;
                case 4:
                    v_height = value;
                    break;
            }
        }
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] pos = new float[2];
            motionController.getCenter(p, pos, new float[2]);
            float rx = pos[0];
            float ry = pos[1];
            float v_x2 = (float) ((((double) rx) + (((double) v_x) * Math.sin((double) v_y))) - ((double) (v_width / 2.0f)));
            f = 2.0f;
            v_y = (float) ((((double) ry) - (((double) v_x) * Math.cos((double) v_y))) - ((double) (v_height / 2.0f)));
            v_x = v_x2;
        } else {
            f = 2.0f;
        }
        point[offset] = (v_width / f) + v_x + 0.0f;
        point[offset + 1] = (v_height / f) + v_y + 0.0f;
    }

    /* access modifiers changed from: package-private */
    public void getBounds(int[] toUse, double[] data, float[] point, int offset) {
        float f = this.f53x;
        float f2 = this.f54y;
        float v_width = this.width;
        float v_height = this.height;
        for (int i = 0; i < toUse.length; i++) {
            float value = (float) data[i];
            switch (toUse[i]) {
                case 3:
                    v_width = value;
                    break;
                case 4:
                    v_height = value;
                    break;
            }
        }
        point[offset] = v_width;
        point[offset + 1] = v_height;
    }

    /* JADX INFO: Multiple debug info for r5v7 float: [D('v_y' float), D('drx' float)] */
    /* access modifiers changed from: package-private */
    public void setView(float position2, View view, int[] toUse, double[] data, double[] slope, double[] cycle) {
        float v_x;
        float dangle;
        boolean remeasure;
        float v_height;
        float v_y;
        float delta_path;
        float dv_height;
        View view2 = view;
        float v_x2 = this.f53x;
        float v_y2 = this.f54y;
        float v_width = this.width;
        float v_height2 = this.height;
        float dv_x = 0.0f;
        float dv_y = 0.0f;
        float dv_width = 0.0f;
        float dvalue = 0.0f;
        float delta_path2 = 0.0f;
        float path_rotate = Float.NaN;
        if (toUse.length != 0) {
            v_x = v_x2;
            if (this.mTempValue.length <= toUse[toUse.length - 1]) {
                int scratch_data_length = toUse[toUse.length - 1] + 1;
                this.mTempValue = new double[scratch_data_length];
                this.mTempDelta = new double[scratch_data_length];
            }
        } else {
            v_x = v_x2;
        }
        Arrays.fill(this.mTempValue, Double.NaN);
        for (int i = 0; i < toUse.length; i++) {
            this.mTempValue[toUse[i]] = data[i];
            this.mTempDelta[toUse[i]] = slope[i];
        }
        int i2 = 0;
        float v_y3 = v_y2;
        float v_width2 = v_width;
        while (true) {
            double[] dArr = this.mTempValue;
            if (i2 < dArr.length) {
                double d = 0.0d;
                if (Double.isNaN(dArr[i2])) {
                    if (cycle == null) {
                        dv_height = dvalue;
                        delta_path = delta_path2;
                    } else if (cycle[i2] == 0.0d) {
                        dv_height = dvalue;
                        delta_path = delta_path2;
                    }
                    dvalue = dv_height;
                    delta_path2 = delta_path;
                    i2++;
                }
                if (cycle != null) {
                    d = cycle[i2];
                }
                double deltaCycle = d;
                if (!Double.isNaN(this.mTempValue[i2])) {
                    deltaCycle = this.mTempValue[i2] + deltaCycle;
                }
                float value = (float) deltaCycle;
                dv_height = dvalue;
                delta_path = delta_path2;
                dvalue = (float) this.mTempDelta[i2];
                switch (i2) {
                    case 0:
                        delta_path2 = value;
                        dvalue = dv_height;
                        continue;
                        i2++;
                    case 1:
                        dv_x = dvalue;
                        v_x = value;
                        dvalue = dv_height;
                        delta_path2 = delta_path;
                        continue;
                        i2++;
                    case 2:
                        v_y3 = value;
                        dv_y = dvalue;
                        dvalue = dv_height;
                        delta_path2 = delta_path;
                        continue;
                        i2++;
                    case 3:
                        v_width2 = value;
                        dv_width = dvalue;
                        dvalue = dv_height;
                        delta_path2 = delta_path;
                        continue;
                        i2++;
                    case 4:
                        v_height2 = value;
                        delta_path2 = delta_path;
                        continue;
                        i2++;
                    case 5:
                        path_rotate = value;
                        dvalue = dv_height;
                        delta_path2 = delta_path;
                        continue;
                        i2++;
                }
                dvalue = dv_height;
                delta_path2 = delta_path;
                i2++;
            } else {
                MotionController motionController = this.mRelativeToController;
                if (motionController != null) {
                    float[] pos = new float[2];
                    float[] vel = new float[2];
                    motionController.getCenter((double) position2, pos, vel);
                    float rx = pos[0];
                    float ry = pos[1];
                    float drx = vel[0];
                    float dry = vel[1];
                    float pos_x = (float) ((((double) rx) + (((double) v_x) * Math.sin((double) v_y3))) - ((double) (v_width2 / 2.0f)));
                    float pos_y = (float) ((((double) ry) - (((double) v_x) * Math.cos((double) v_y3))) - ((double) (v_height2 / 2.0f)));
                    v_height = v_height2;
                    dangle = v_width2;
                    float dpos_x = (float) (((double) drx) + (((double) dv_x) * Math.sin((double) v_y3)) + (((double) v_x) * Math.cos((double) v_y3) * ((double) dv_y)));
                    float dpos_y = (float) ((((double) dry) - (((double) dv_x) * Math.cos((double) v_y3))) + (((double) v_x) * Math.sin((double) v_y3) * ((double) dv_y)));
                    v_x = pos_x;
                    if (slope.length >= 2) {
                        slope[0] = (double) dpos_x;
                        remeasure = true;
                        slope[1] = (double) dpos_y;
                    } else {
                        remeasure = true;
                    }
                    if (!Float.isNaN(path_rotate)) {
                        view2 = view;
                        view2.setRotation((float) (((double) path_rotate) + Math.toDegrees(Math.atan2((double) dpos_y, (double) dpos_x))));
                    } else {
                        view2 = view;
                    }
                    v_y = pos_y;
                } else {
                    dangle = v_width2;
                    remeasure = true;
                    v_height = v_height2;
                    if (!Float.isNaN(path_rotate)) {
                        view2.setRotation((float) (((double) 0.0f) + ((double) path_rotate) + Math.toDegrees(Math.atan2((double) (dv_y + (dvalue / 2.0f)), (double) (dv_x + (dv_width / 2.0f))))));
                    }
                    v_y = v_y3;
                }
                if (view2 instanceof FloatLayout) {
                    ((FloatLayout) view2).layout(v_x, v_y, v_x + dangle, v_y + v_height);
                    return;
                }
                int l = (int) (v_x + 0.5f);
                int t = (int) (v_y + 0.5f);
                int r = (int) (v_x + 0.5f + dangle);
                int b = (int) (0.5f + v_y + v_height);
                int i_width = r - l;
                int i_height = b - t;
                if (i_width == view.getMeasuredWidth() && i_height == view.getMeasuredHeight()) {
                    remeasure = false;
                }
                if (remeasure) {
                    view2.measure(View.MeasureSpec.makeMeasureSpec(i_width, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(i_height, BasicMeasure.EXACTLY));
                }
                view2.layout(l, t, r, b);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void getRect(int[] toUse, double[] data, float[] path, int offset) {
        float angle;
        float v_x = this.f53x;
        float v_y = this.f54y;
        float v_width = this.width;
        float v_height = this.height;
        float alpha = 0.0f;
        float rotationX = 0.0f;
        int i = 0;
        while (i < toUse.length) {
            float value = (float) data[i];
            switch (toUse[i]) {
                case 1:
                    v_x = value;
                    break;
                case 2:
                    v_y = value;
                    break;
                case 3:
                    v_width = value;
                    break;
                case 4:
                    v_height = value;
                    break;
            }
            i++;
            alpha = alpha;
            rotationX = rotationX;
        }
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float rx = motionController.getCenterX();
            angle = 0.0f;
            v_y = (float) ((((double) this.mRelativeToController.getCenterY()) - (((double) v_x) * Math.cos((double) v_y))) - ((double) (v_height / 2.0f)));
            v_x = (float) ((((double) rx) + (((double) v_x) * Math.sin((double) v_y))) - ((double) (v_width / 2.0f)));
        } else {
            angle = 0.0f;
        }
        float x1 = v_x;
        float y1 = v_y;
        float x2 = v_x + v_width;
        float y2 = y1;
        float x3 = x2;
        float y3 = v_y + v_height;
        float x4 = x1;
        float y4 = y3;
        float cx = x1 + (v_width / 2.0f);
        float cy = y1 + (v_height / 2.0f);
        if (!Float.isNaN(Float.NaN)) {
            cx = x1 + ((x2 - x1) * Float.NaN);
        }
        if (!Float.isNaN(Float.NaN)) {
            cy = y1 + ((y3 - y1) * Float.NaN);
        }
        if (1.0f != 1.0f) {
            float midx = (x1 + x2) / 2.0f;
            x1 = ((x1 - midx) * 1.0f) + midx;
            x2 = ((x2 - midx) * 1.0f) + midx;
            x3 = ((x3 - midx) * 1.0f) + midx;
            x4 = ((x4 - midx) * 1.0f) + midx;
        }
        if (1.0f != 1.0f) {
            float midy = (y1 + y3) / 2.0f;
            y1 = ((y1 - midy) * 1.0f) + midy;
            y2 = ((y2 - midy) * 1.0f) + midy;
            y3 = ((y3 - midy) * 1.0f) + midy;
            y4 = ((y4 - midy) * 1.0f) + midy;
        }
        if (angle != 0.0f) {
            float sin = (float) Math.sin(Math.toRadians((double) angle));
            float cos = (float) Math.cos(Math.toRadians((double) angle));
            float tx1 = xRotate(sin, cos, cx, cy, x1, y1);
            float ty1 = yRotate(sin, cos, cx, cy, x1, y1);
            float tx2 = xRotate(sin, cos, cx, cy, x2, y2);
            float ty2 = yRotate(sin, cos, cx, cy, x2, y2);
            float tx3 = xRotate(sin, cos, cx, cy, x3, y3);
            float ty3 = yRotate(sin, cos, cx, cy, x3, y3);
            float tx4 = xRotate(sin, cos, cx, cy, x4, y4);
            float ty4 = yRotate(sin, cos, cx, cy, x4, y4);
            x1 = tx1;
            y1 = ty1;
            x2 = tx2;
            y2 = ty2;
            x3 = tx3;
            y3 = ty3;
            x4 = tx4;
            y4 = ty4;
        }
        int offset2 = offset + 1;
        path[offset] = x1 + 0.0f;
        int offset3 = offset2 + 1;
        path[offset2] = y1 + 0.0f;
        int offset4 = offset3 + 1;
        path[offset3] = x2 + 0.0f;
        int offset5 = offset4 + 1;
        path[offset4] = y2 + 0.0f;
        int offset6 = offset5 + 1;
        path[offset5] = x3 + 0.0f;
        int offset7 = offset6 + 1;
        path[offset6] = y3 + 0.0f;
        int offset8 = offset7 + 1;
        path[offset7] = x4 + 0.0f;
        int i2 = offset8 + 1;
        path[offset8] = y4 + 0.0f;
    }

    /* access modifiers changed from: package-private */
    public void setDpDt(float locationX, float locationY, float[] mAnchorDpDt, int[] toUse, double[] deltaData, double[] data) {
        float d_x = 0.0f;
        float d_y = 0.0f;
        float d_width = 0.0f;
        float d_height = 0.0f;
        for (int i = 0; i < toUse.length; i++) {
            float deltaV = (float) deltaData[i];
            float f = (float) data[i];
            switch (toUse[i]) {
                case 1:
                    d_x = deltaV;
                    break;
                case 2:
                    d_y = deltaV;
                    break;
                case 3:
                    d_width = deltaV;
                    break;
                case 4:
                    d_height = deltaV;
                    break;
            }
        }
        float deltaX = d_x - ((0.0f * d_width) / 2.0f);
        float deltaY = d_y - ((0.0f * d_height) / 2.0f);
        mAnchorDpDt[0] = ((1.0f - locationX) * deltaX) + ((deltaX + ((0.0f + 1.0f) * d_width)) * locationX) + 0.0f;
        mAnchorDpDt[1] = ((1.0f - locationY) * deltaY) + ((deltaY + ((0.0f + 1.0f) * d_height)) * locationY) + 0.0f;
    }

    /* access modifiers changed from: package-private */
    public void fillStandard(double[] data, int[] toUse) {
        float[] set = {this.position, this.f53x, this.f54y, this.width, this.height, this.mPathRotate};
        int c = 0;
        for (int i = 0; i < toUse.length; i++) {
            if (toUse[i] < set.length) {
                data[c] = (double) set[toUse[i]];
                c++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasCustomData(String name) {
        return this.attributes.containsKey(name);
    }

    /* access modifiers changed from: package-private */
    public int getCustomDataCount(String name) {
        ConstraintAttribute a = this.attributes.get(name);
        if (a == null) {
            return 0;
        }
        return a.numberOfInterpolatedValues();
    }

    /* access modifiers changed from: package-private */
    public int getCustomData(String name, double[] value, int offset) {
        ConstraintAttribute a = this.attributes.get(name);
        if (a == null) {
            return 0;
        }
        if (a.numberOfInterpolatedValues() == 1) {
            value[offset] = (double) a.getValueToInterpolate();
            return 1;
        }
        int N = a.numberOfInterpolatedValues();
        float[] f = new float[N];
        a.getValuesToInterpolate(f);
        int i = 0;
        while (i < N) {
            value[offset] = (double) f[i];
            i++;
            offset++;
        }
        return N;
    }

    /* access modifiers changed from: package-private */
    public void setBounds(float x, float y, float w, float h) {
        this.f53x = x;
        this.f54y = y;
        this.width = w;
        this.height = h;
    }

    public int compareTo(MotionPaths o) {
        return Float.compare(this.position, o.position);
    }

    public void applyParameters(ConstraintSet.Constraint c) {
        this.mKeyFrameEasing = Easing.getInterpolator(c.motion.mTransitionEasing);
        this.mPathMotionArc = c.motion.mPathMotionArc;
        this.mAnimateRelativeTo = c.motion.mAnimateRelativeTo;
        this.mPathRotate = c.motion.mPathRotate;
        this.mDrawPath = c.motion.mDrawPath;
        this.mAnimateCircleAngleTo = c.motion.mAnimateCircleAngleTo;
        this.mProgress = c.propertySet.mProgress;
        this.mRelativeAngle = c.layout.circleAngle;
        for (String s : c.mCustomConstraints.keySet()) {
            ConstraintAttribute attr = c.mCustomConstraints.get(s);
            if (attr != null && attr.isContinuous()) {
                this.attributes.put(s, attr);
            }
        }
    }

    public void configureRelativeTo(MotionController toOrbit) {
        toOrbit.getPos((double) this.mProgress);
    }
}
