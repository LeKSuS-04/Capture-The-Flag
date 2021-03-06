package com.google.android.material.timepicker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.C0552R;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.timepicker.ClockHandView;
import java.util.Locale;

/* access modifiers changed from: package-private */
public class TimePickerView extends ConstraintLayout implements TimePickerControls {
    private final ClockFaceView clockFace;
    private final ClockHandView clockHandView;
    private final Chip hourView;
    private final Chip minuteView;
    private OnDoubleTapListener onDoubleTapListener;
    private OnPeriodChangeListener onPeriodChangeListener;
    private OnSelectionChange onSelectionChangeListener;
    private final View.OnClickListener selectionListener;
    private final MaterialButtonToggleGroup toggle;

    /* access modifiers changed from: package-private */
    public interface OnDoubleTapListener {
        void onDoubleTap();
    }

    /* access modifiers changed from: package-private */
    public interface OnPeriodChangeListener {
        void onPeriodChange(int i);
    }

    /* access modifiers changed from: package-private */
    public interface OnSelectionChange {
        void onSelectionChanged(int i);
    }

    public TimePickerView(Context context) {
        this(context, null);
    }

    public TimePickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimePickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.selectionListener = new View.OnClickListener() {
            /* class com.google.android.material.timepicker.TimePickerView.View$OnClickListenerC07851 */

            public void onClick(View v) {
                if (TimePickerView.this.onSelectionChangeListener != null) {
                    TimePickerView.this.onSelectionChangeListener.onSelectionChanged(((Integer) v.getTag(C0552R.C0555id.selection_type)).intValue());
                }
            }
        };
        LayoutInflater.from(context).inflate(C0552R.layout.material_timepicker, this);
        this.clockFace = (ClockFaceView) findViewById(C0552R.C0555id.material_clock_face);
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) findViewById(C0552R.C0555id.material_clock_period_toggle);
        this.toggle = materialButtonToggleGroup;
        materialButtonToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            /* class com.google.android.material.timepicker.TimePickerView.C07862 */

            @Override // com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                int period = checkedId == C0552R.C0555id.material_clock_period_pm_button ? 1 : 0;
                if (TimePickerView.this.onPeriodChangeListener != null && isChecked) {
                    TimePickerView.this.onPeriodChangeListener.onPeriodChange(period);
                }
            }
        });
        this.minuteView = (Chip) findViewById(C0552R.C0555id.material_minute_tv);
        this.hourView = (Chip) findViewById(C0552R.C0555id.material_hour_tv);
        this.clockHandView = (ClockHandView) findViewById(C0552R.C0555id.material_clock_hand);
        setupDoubleTap();
        setUpDisplay();
    }

    private void setupDoubleTap() {
        final GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            /* class com.google.android.material.timepicker.TimePickerView.C07873 */

            public boolean onDoubleTap(MotionEvent e) {
                boolean ret = super.onDoubleTap(e);
                if (TimePickerView.this.onDoubleTapListener != null) {
                    TimePickerView.this.onDoubleTapListener.onDoubleTap();
                }
                return ret;
            }
        });
        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            /* class com.google.android.material.timepicker.TimePickerView.View$OnTouchListenerC07884 */

            public boolean onTouch(View v, MotionEvent event) {
                if (((Checkable) v).isChecked()) {
                    return gestureDetector.onTouchEvent(event);
                }
                return false;
            }
        };
        this.minuteView.setOnTouchListener(onTouchListener);
        this.hourView.setOnTouchListener(onTouchListener);
    }

    public void setMinuteHourDelegate(AccessibilityDelegateCompat clickActionDelegate) {
        ViewCompat.setAccessibilityDelegate(this.hourView, clickActionDelegate);
    }

    public void setHourClickDelegate(AccessibilityDelegateCompat clickActionDelegate) {
        ViewCompat.setAccessibilityDelegate(this.minuteView, clickActionDelegate);
    }

    private void setUpDisplay() {
        this.minuteView.setTag(C0552R.C0555id.selection_type, 12);
        this.hourView.setTag(C0552R.C0555id.selection_type, 10);
        this.minuteView.setOnClickListener(this.selectionListener);
        this.hourView.setOnClickListener(this.selectionListener);
    }

    @Override // com.google.android.material.timepicker.TimePickerControls
    public void setValues(String[] values, int contentDescription) {
        this.clockFace.setValues(values, contentDescription);
    }

    @Override // com.google.android.material.timepicker.TimePickerControls
    public void setHandRotation(float rotation) {
        this.clockHandView.setHandRotation(rotation);
    }

    public void setHandRotation(float rotation, boolean animate) {
        this.clockHandView.setHandRotation(rotation, animate);
    }

    public void setAnimateOnTouchUp(boolean animating) {
        this.clockHandView.setAnimateOnTouchUp(animating);
    }

    @Override // com.google.android.material.timepicker.TimePickerControls
    public void updateTime(int period, int hourOfDay, int minute) {
        this.toggle.check(period == 1 ? C0552R.C0555id.material_clock_period_pm_button : C0552R.C0555id.material_clock_period_am_button);
        Locale current = getResources().getConfiguration().locale;
        String minuteFormatted = String.format(current, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(minute));
        String hourFormatted = String.format(current, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(hourOfDay));
        this.minuteView.setText(minuteFormatted);
        this.hourView.setText(hourFormatted);
    }

    @Override // com.google.android.material.timepicker.TimePickerControls
    public void setActiveSelection(int selection) {
        boolean z = true;
        this.minuteView.setChecked(selection == 12);
        Chip chip = this.hourView;
        if (selection != 10) {
            z = false;
        }
        chip.setChecked(z);
    }

    public void addOnRotateListener(ClockHandView.OnRotateListener onRotateListener) {
        this.clockHandView.addOnRotateListener(onRotateListener);
    }

    public void setOnActionUpListener(ClockHandView.OnActionUpListener onActionUpListener) {
        this.clockHandView.setOnActionUpListener(onActionUpListener);
    }

    /* access modifiers changed from: package-private */
    public void setOnPeriodChangeListener(OnPeriodChangeListener onPeriodChangeListener2) {
        this.onPeriodChangeListener = onPeriodChangeListener2;
    }

    /* access modifiers changed from: package-private */
    public void setOnSelectionChangeListener(OnSelectionChange onSelectionChangeListener2) {
        this.onSelectionChangeListener = onSelectionChangeListener2;
    }

    /* access modifiers changed from: package-private */
    public void setOnDoubleTapListener(OnDoubleTapListener listener) {
        this.onDoubleTapListener = listener;
    }

    public void showToggle() {
        this.toggle.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (changedView == this && visibility == 0) {
            updateToggleConstraints();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateToggleConstraints();
    }

    private void updateToggleConstraints() {
        if (this.toggle.getVisibility() == 0) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this);
            int sideToClear = 1;
            if (ViewCompat.getLayoutDirection(this) == 0) {
                sideToClear = 2;
            }
            constraintSet.clear(C0552R.C0555id.material_clock_display, sideToClear);
            constraintSet.applyTo(this);
        }
    }
}
