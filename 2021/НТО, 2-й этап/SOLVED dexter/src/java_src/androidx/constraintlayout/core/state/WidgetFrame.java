package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.parser.CLElement;
import androidx.constraintlayout.core.parser.CLKey;
import androidx.constraintlayout.core.parser.CLNumber;
import androidx.constraintlayout.core.parser.CLObject;
import androidx.constraintlayout.core.parser.CLParsingException;
import androidx.constraintlayout.core.state.Transition;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class WidgetFrame {
    private static final boolean OLD_SYSTEM = true;
    public static float phone_orientation = Float.NaN;
    public float alpha = Float.NaN;
    public int bottom = 0;
    public float interpolatedPos = Float.NaN;
    public int left = 0;
    public final HashMap<String, CustomVariable> mCustom = new HashMap<>();
    public String name = null;
    public float pivotX = Float.NaN;
    public float pivotY = Float.NaN;
    public int right = 0;
    public float rotationX = Float.NaN;
    public float rotationY = Float.NaN;
    public float rotationZ = Float.NaN;
    public float scaleX = Float.NaN;
    public float scaleY = Float.NaN;
    public int top = 0;
    public float translationX = Float.NaN;
    public float translationY = Float.NaN;
    public float translationZ = Float.NaN;
    public int visibility = 0;
    public ConstraintWidget widget = null;

    public int width() {
        return Math.max(0, this.right - this.left);
    }

    public int height() {
        return Math.max(0, this.bottom - this.top);
    }

    public WidgetFrame() {
    }

    public WidgetFrame(ConstraintWidget widget2) {
        this.widget = widget2;
    }

    public WidgetFrame(WidgetFrame frame) {
        this.widget = frame.widget;
        this.left = frame.left;
        this.top = frame.top;
        this.right = frame.right;
        this.bottom = frame.bottom;
        updateAttributes(frame);
    }

    public void updateAttributes(WidgetFrame frame) {
        this.pivotX = frame.pivotX;
        this.pivotY = frame.pivotY;
        this.rotationX = frame.rotationX;
        this.rotationY = frame.rotationY;
        this.rotationZ = frame.rotationZ;
        this.translationX = frame.translationX;
        this.translationY = frame.translationY;
        this.translationZ = frame.translationZ;
        this.scaleX = frame.scaleX;
        this.scaleY = frame.scaleY;
        this.alpha = frame.alpha;
        this.visibility = frame.visibility;
        this.mCustom.clear();
        if (frame != null) {
            for (CustomVariable c : frame.mCustom.values()) {
                this.mCustom.put(c.getName(), c.copy());
            }
        }
    }

    public boolean isDefaultTransform() {
        if (!Float.isNaN(this.rotationX) || !Float.isNaN(this.rotationY) || !Float.isNaN(this.rotationZ) || !Float.isNaN(this.translationX) || !Float.isNaN(this.translationY) || !Float.isNaN(this.translationZ) || !Float.isNaN(this.scaleX) || !Float.isNaN(this.scaleY) || !Float.isNaN(this.alpha)) {
            return false;
        }
        return OLD_SYSTEM;
    }

    /* JADX INFO: Multiple debug info for r13v4 float: [D('startAlpha' float), D('progressPosition' float)] */
    /* JADX INFO: Multiple debug info for r14v3 float: [D('startHeight' int), D('endAlpha' float)] */
    public static void interpolate(int parentWidth, int parentHeight, WidgetFrame frame, WidgetFrame start, WidgetFrame end, Transition transition, float progress) {
        int startWidth;
        int startHeight;
        float startAlpha;
        int endHeight;
        int startY;
        int startX;
        float f;
        float progressPosition;
        int interpolateStartFrame;
        int frameNumber = (int) (progress * 100.0f);
        int startX2 = start.left;
        int startY2 = start.top;
        int endX = end.left;
        int endY = end.top;
        int startWidth2 = start.right - start.left;
        int startHeight2 = start.bottom - start.top;
        int endWidth = end.right - end.left;
        int endHeight2 = end.bottom - end.top;
        float progressPosition2 = start.alpha;
        float endAlpha = end.alpha;
        if (start.visibility == 8) {
            startX2 = (int) (((float) startX2) - (((float) endWidth) / 2.0f));
            startY2 = (int) (((float) startY2) - (((float) endHeight2) / 2.0f));
            startHeight = endHeight2;
            if (Float.isNaN(progressPosition2)) {
                startWidth = endWidth;
                startAlpha = 0.0f;
            } else {
                startWidth = endWidth;
                startAlpha = progressPosition2;
            }
        } else {
            startAlpha = progressPosition2;
            startWidth = startWidth2;
            startHeight = startHeight2;
        }
        int startX3 = startX2;
        if (end.visibility == 8) {
            endX = (int) (((float) endX) - (((float) startWidth) / 2.0f));
            endY = (int) (((float) endY) - (((float) startHeight) / 2.0f));
            endWidth = startWidth;
            endHeight = startHeight;
            if (Float.isNaN(endAlpha)) {
                endAlpha = 0.0f;
            }
        } else {
            endHeight = endHeight2;
        }
        if (Float.isNaN(startAlpha) && !Float.isNaN(endAlpha)) {
            startAlpha = 1.0f;
        }
        if (!Float.isNaN(startAlpha) && Float.isNaN(endAlpha)) {
            endAlpha = 1.0f;
        }
        if (frame.widget == null || !transition.hasPositionKeyframes()) {
            f = progress;
            startY = startY2;
            progressPosition = progress;
            startX = startX3;
        } else {
            Transition.KeyPosition firstPosition = transition.findPreviousPosition(frame.widget.stringId, frameNumber);
            int startY3 = startY2;
            Transition.KeyPosition lastPosition = transition.findNextPosition(frame.widget.stringId, frameNumber);
            if (firstPosition == lastPosition) {
                lastPosition = null;
            }
            int interpolateEndFrame = 100;
            if (firstPosition != null) {
                startX3 = (int) (firstPosition.f35x * ((float) parentWidth));
                int startY4 = (int) (firstPosition.f36y * ((float) parentHeight));
                interpolateStartFrame = firstPosition.frame;
                startY3 = startY4;
            } else {
                interpolateStartFrame = 0;
            }
            if (lastPosition != null) {
                endY = (int) (lastPosition.f36y * ((float) parentHeight));
                interpolateEndFrame = lastPosition.frame;
                endX = (int) (lastPosition.f35x * ((float) parentWidth));
            }
            f = progress;
            progressPosition = ((100.0f * f) - ((float) interpolateStartFrame)) / ((float) (interpolateEndFrame - interpolateStartFrame));
            startY = startY3;
            startX = startX3;
        }
        frame.widget = start.widget;
        int i = (int) (((float) startX) + (((float) (endX - startX)) * progressPosition));
        frame.left = i;
        int i2 = (int) (((float) startY) + (((float) (endY - startY)) * progressPosition));
        frame.top = i2;
        frame.right = i + ((int) (((1.0f - f) * ((float) startWidth)) + (((float) endWidth) * f)));
        frame.bottom = i2 + ((int) (((1.0f - f) * ((float) startHeight)) + (((float) endHeight) * f)));
        frame.pivotX = interpolate(start.pivotX, end.pivotX, 0.5f, f);
        frame.pivotY = interpolate(start.pivotY, end.pivotY, 0.5f, f);
        frame.rotationX = interpolate(start.rotationX, end.rotationX, 0.0f, f);
        frame.rotationY = interpolate(start.rotationY, end.rotationY, 0.0f, f);
        frame.rotationZ = interpolate(start.rotationZ, end.rotationZ, 0.0f, f);
        frame.scaleX = interpolate(start.scaleX, end.scaleX, 1.0f, f);
        frame.scaleY = interpolate(start.scaleY, end.scaleY, 1.0f, f);
        frame.translationX = interpolate(start.translationX, end.translationX, 0.0f, f);
        frame.translationY = interpolate(start.translationY, end.translationY, 0.0f, f);
        frame.translationZ = interpolate(start.translationZ, end.translationZ, 0.0f, f);
        frame.alpha = interpolate(startAlpha, endAlpha, 1.0f, f);
    }

    private static float interpolate(float start, float end, float defaultValue, float progress) {
        boolean isStartUnset = Float.isNaN(start);
        boolean isEndUnset = Float.isNaN(end);
        if (isStartUnset && isEndUnset) {
            return Float.NaN;
        }
        if (isStartUnset) {
            start = defaultValue;
        }
        if (isEndUnset) {
            end = defaultValue;
        }
        return ((end - start) * progress) + start;
    }

    public float centerX() {
        int i = this.left;
        return ((float) i) + (((float) (this.right - i)) / 2.0f);
    }

    public float centerY() {
        int i = this.top;
        return ((float) i) + (((float) (this.bottom - i)) / 2.0f);
    }

    public WidgetFrame update() {
        ConstraintWidget constraintWidget = this.widget;
        if (constraintWidget != null) {
            this.left = constraintWidget.getLeft();
            this.top = this.widget.getTop();
            this.right = this.widget.getRight();
            this.bottom = this.widget.getBottom();
            updateAttributes(this.widget.frame);
        }
        return this;
    }

    public WidgetFrame update(ConstraintWidget widget2) {
        if (widget2 == null) {
            return this;
        }
        this.widget = widget2;
        update();
        return this;
    }

    public void addCustomColor(String name2, int color) {
        setCustomAttribute(name2, TypedValues.Custom.TYPE_COLOR, color);
    }

    public int getCustomColor(String name2) {
        if (this.mCustom.containsKey(name2)) {
            return this.mCustom.get(name2).getColorValue();
        }
        return -21880;
    }

    public void addCustomFloat(String name2, float value) {
        setCustomAttribute(name2, TypedValues.Custom.TYPE_FLOAT, value);
    }

    public float getCustomFloat(String name2) {
        if (this.mCustom.containsKey(name2)) {
            return this.mCustom.get(name2).getFloatValue();
        }
        return Float.NaN;
    }

    public void setCustomAttribute(String name2, int type, float value) {
        if (this.mCustom.containsKey(name2)) {
            this.mCustom.get(name2).setFloatValue(value);
        } else {
            this.mCustom.put(name2, new CustomVariable(name2, type, value));
        }
    }

    public void setCustomAttribute(String name2, int type, int value) {
        if (this.mCustom.containsKey(name2)) {
            this.mCustom.get(name2).setIntValue(value);
        } else {
            this.mCustom.put(name2, new CustomVariable(name2, type, value));
        }
    }

    public void setCustomAttribute(String name2, int type, boolean value) {
        if (this.mCustom.containsKey(name2)) {
            this.mCustom.get(name2).setBooleanValue(value);
        } else {
            this.mCustom.put(name2, new CustomVariable(name2, type, value));
        }
    }

    public void setCustomAttribute(String name2, int type, String value) {
        if (this.mCustom.containsKey(name2)) {
            this.mCustom.get(name2).setStringValue(value);
        } else {
            this.mCustom.put(name2, new CustomVariable(name2, type, value));
        }
    }

    public CustomVariable getCustomAttribute(String name2) {
        return this.mCustom.get(name2);
    }

    public Set<String> getCustomAttributeNames() {
        return this.mCustom.keySet();
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean setValue(String key, CLElement value) throws CLParsingException {
        char c;
        switch (key.hashCode()) {
            case -1881940865:
                if (key.equals("phone_orientation")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -1383228885:
                if (key.equals("bottom")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case -1349088399:
                if (key.equals("custom")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case -1249320806:
                if (key.equals("rotationX")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1249320805:
                if (key.equals("rotationY")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1249320804:
                if (key.equals("rotationZ")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1225497657:
                if (key.equals("translationX")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1225497656:
                if (key.equals("translationY")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1225497655:
                if (key.equals("translationZ")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -987906986:
                if (key.equals("pivotX")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -987906985:
                if (key.equals("pivotY")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -908189618:
                if (key.equals("scaleX")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -908189617:
                if (key.equals("scaleY")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 115029:
                if (key.equals("top")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 3317767:
                if (key.equals("left")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 92909918:
                if (key.equals("alpha")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 108511772:
                if (key.equals("right")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 642850769:
                if (key.equals("interpolatedPos")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.pivotX = value.getFloat();
                break;
            case 1:
                this.pivotY = value.getFloat();
                break;
            case 2:
                this.rotationX = value.getFloat();
                break;
            case 3:
                this.rotationY = value.getFloat();
                break;
            case 4:
                this.rotationZ = value.getFloat();
                break;
            case 5:
                this.translationX = value.getFloat();
                break;
            case 6:
                this.translationY = value.getFloat();
                break;
            case 7:
                this.translationZ = value.getFloat();
                break;
            case '\b':
                this.scaleX = value.getFloat();
                break;
            case '\t':
                this.scaleY = value.getFloat();
                break;
            case '\n':
                this.alpha = value.getFloat();
                break;
            case 11:
                this.interpolatedPos = value.getFloat();
                break;
            case '\f':
                phone_orientation = value.getFloat();
                break;
            case '\r':
                this.top = value.getInt();
                break;
            case 14:
                this.left = value.getInt();
                break;
            case 15:
                this.right = value.getInt();
                break;
            case 16:
                this.bottom = value.getInt();
                break;
            case 17:
                parseCustom(value);
                break;
            default:
                return false;
        }
        return OLD_SYSTEM;
    }

    /* access modifiers changed from: package-private */
    public void parseCustom(CLElement custom) throws CLParsingException {
        CLObject obj = (CLObject) custom;
        int n = obj.size();
        for (int i = 0; i < n; i++) {
            CLKey k = (CLKey) obj.get(i);
            k.content();
            CLElement v = k.getValue();
            String vStr = v.content();
            if (vStr.matches("#[0-9a-fA-F]+")) {
                setCustomAttribute(k.content(), TypedValues.Custom.TYPE_COLOR, Integer.parseInt(vStr.substring(1), 16));
            } else if (v instanceof CLNumber) {
                setCustomAttribute(k.content(), TypedValues.Custom.TYPE_FLOAT, v.getFloat());
            } else {
                setCustomAttribute(k.content(), TypedValues.Custom.TYPE_STRING, vStr);
            }
        }
    }

    public StringBuilder serialize(StringBuilder ret) {
        return serialize(ret, false);
    }

    public StringBuilder serialize(StringBuilder ret, boolean sendPhoneOrientation) {
        ret.append("{\n");
        add(ret, "left", this.left);
        add(ret, "top", this.top);
        add(ret, "right", this.right);
        add(ret, "bottom", this.bottom);
        add(ret, "pivotX", this.pivotX);
        add(ret, "pivotY", this.pivotY);
        add(ret, "rotationX", this.rotationX);
        add(ret, "rotationY", this.rotationY);
        add(ret, "rotationZ", this.rotationZ);
        add(ret, "translationX", this.translationX);
        add(ret, "translationY", this.translationY);
        add(ret, "translationZ", this.translationZ);
        add(ret, "scaleX", this.scaleX);
        add(ret, "scaleY", this.scaleY);
        add(ret, "alpha", this.alpha);
        add(ret, "visibility", this.left);
        add(ret, "interpolatedPos", this.interpolatedPos);
        if (sendPhoneOrientation) {
            add(ret, "phone_orientation", phone_orientation);
        }
        if (sendPhoneOrientation) {
            add(ret, "phone_orientation", phone_orientation);
        }
        if (this.mCustom.size() != 0) {
            ret.append("custom : {\n");
            for (String s : this.mCustom.keySet()) {
                CustomVariable value = this.mCustom.get(s);
                ret.append(s);
                ret.append(": ");
                switch (value.getType()) {
                    case TypedValues.Custom.TYPE_INT:
                        ret.append(value.getIntegerValue());
                        ret.append(",\n");
                        break;
                    case TypedValues.Custom.TYPE_FLOAT:
                    case TypedValues.Custom.TYPE_DIMENSION:
                        ret.append(value.getFloatValue());
                        ret.append(",\n");
                        break;
                    case TypedValues.Custom.TYPE_COLOR:
                        ret.append("'");
                        ret.append(CustomVariable.colorString(value.getIntegerValue()));
                        ret.append("',\n");
                        break;
                    case TypedValues.Custom.TYPE_STRING:
                        ret.append("'");
                        ret.append(value.getStringValue());
                        ret.append("',\n");
                        break;
                    case TypedValues.Custom.TYPE_BOOLEAN:
                        ret.append("'");
                        ret.append(value.getBooleanValue());
                        ret.append("',\n");
                        break;
                }
            }
            ret.append("}\n");
        }
        ret.append("}\n");
        return ret;
    }

    private static void add(StringBuilder s, String title, int value) {
        s.append(title);
        s.append(": ");
        s.append(value);
        s.append(",\n");
    }

    private static void add(StringBuilder s, String title, float value) {
        if (!Float.isNaN(value)) {
            s.append(title);
            s.append(": ");
            s.append(value);
            s.append(",\n");
        }
    }

    /* access modifiers changed from: package-private */
    public void printCustomAttributes() {
        String ss;
        StackTraceElement s = new Throwable().getStackTrace()[1];
        String ss2 = (".(" + s.getFileName() + ":" + s.getLineNumber() + ") " + s.getMethodName()) + " " + (hashCode() % 1000);
        if (this.widget != null) {
            ss = ss2 + "/" + (this.widget.hashCode() % 1000) + " ";
        } else {
            ss = ss2 + "/NULL ";
        }
        HashMap<String, CustomVariable> hashMap = this.mCustom;
        if (hashMap != null) {
            Iterator<String> it = hashMap.keySet().iterator();
            while (it.hasNext()) {
                System.out.println(ss + this.mCustom.get(it.next()).toString());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void logv(String str) {
        String ss;
        StackTraceElement s = new Throwable().getStackTrace()[1];
        String ss2 = (".(" + s.getFileName() + ":" + s.getLineNumber() + ") " + s.getMethodName()) + " " + (hashCode() % 1000);
        if (this.widget != null) {
            ss = ss2 + "/" + (this.widget.hashCode() % 1000);
        } else {
            ss = ss2 + "/NULL";
        }
        System.out.println(ss + " " + str);
    }
}
