package androidx.dynamicanimation.animation;

import android.util.FloatProperty;

public abstract class FloatPropertyCompat<T> {
    final String mPropertyName;

    public abstract float getValue(T t);

    public abstract void setValue(T t, float f);

    public FloatPropertyCompat(String name) {
        this.mPropertyName = name;
    }

    public static <T> FloatPropertyCompat<T> createFloatPropertyCompat(final FloatProperty<T> property) {
        return new FloatPropertyCompat<T>(property.getName()) {
            /* class androidx.dynamicanimation.animation.FloatPropertyCompat.C02871 */

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(T object) {
                return ((Float) property.get(object)).floatValue();
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(T object, float value) {
                property.setValue(object, value);
            }
        };
    }
}
