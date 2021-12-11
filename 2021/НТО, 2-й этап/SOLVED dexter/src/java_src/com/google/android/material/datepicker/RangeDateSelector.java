package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.core.util.Pair;
import androidx.core.util.Preconditions;
import com.google.android.material.C0552R;
import com.google.android.material.internal.ManufacturerUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.textfield.TextInputLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

public class RangeDateSelector implements DateSelector<Pair<Long, Long>> {
    public static final Parcelable.Creator<RangeDateSelector> CREATOR = new Parcelable.Creator<RangeDateSelector>() {
        /* class com.google.android.material.datepicker.RangeDateSelector.C06333 */

        @Override // android.os.Parcelable.Creator
        public RangeDateSelector createFromParcel(Parcel source) {
            RangeDateSelector rangeDateSelector = new RangeDateSelector();
            rangeDateSelector.selectedStartItem = (Long) source.readValue(Long.class.getClassLoader());
            rangeDateSelector.selectedEndItem = (Long) source.readValue(Long.class.getClassLoader());
            return rangeDateSelector;
        }

        @Override // android.os.Parcelable.Creator
        public RangeDateSelector[] newArray(int size) {
            return new RangeDateSelector[size];
        }
    };
    private final String invalidRangeEndError = " ";
    private String invalidRangeStartError;
    private Long proposedTextEnd = null;
    private Long proposedTextStart = null;
    private Long selectedEndItem = null;
    private Long selectedStartItem = null;

    @Override // com.google.android.material.datepicker.DateSelector
    public void select(long selection) {
        Long l = this.selectedStartItem;
        if (l == null) {
            this.selectedStartItem = Long.valueOf(selection);
        } else if (this.selectedEndItem != null || !isValidRange(l.longValue(), selection)) {
            this.selectedEndItem = null;
            this.selectedStartItem = Long.valueOf(selection);
        } else {
            this.selectedEndItem = Long.valueOf(selection);
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public boolean isSelectionComplete() {
        Long l = this.selectedStartItem;
        return (l == null || this.selectedEndItem == null || !isValidRange(l.longValue(), this.selectedEndItem.longValue())) ? false : true;
    }

    public void setSelection(Pair<Long, Long> selection) {
        Long l;
        if (!(selection.first == null || selection.second == null)) {
            Preconditions.checkArgument(isValidRange(selection.first.longValue(), selection.second.longValue()));
        }
        Long l2 = null;
        if (selection.first == null) {
            l = null;
        } else {
            l = Long.valueOf(UtcDates.canonicalYearMonthDay(selection.first.longValue()));
        }
        this.selectedStartItem = l;
        if (selection.second != null) {
            l2 = Long.valueOf(UtcDates.canonicalYearMonthDay(selection.second.longValue()));
        }
        this.selectedEndItem = l2;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Pair<Long, Long> getSelection() {
        return new Pair<>(this.selectedStartItem, this.selectedEndItem);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<Pair<Long, Long>> getSelectedRanges() {
        if (this.selectedStartItem == null || this.selectedEndItem == null) {
            return new ArrayList();
        }
        ArrayList<Pair<Long, Long>> ranges = new ArrayList<>();
        ranges.add(new Pair<>(this.selectedStartItem, this.selectedEndItem));
        return ranges;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<Long> getSelectedDays() {
        ArrayList<Long> selections = new ArrayList<>();
        Long l = this.selectedStartItem;
        if (l != null) {
            selections.add(l);
        }
        Long l2 = this.selectedEndItem;
        if (l2 != null) {
            selections.add(l2);
        }
        return selections;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public int getDefaultThemeResId(Context context) {
        Resources res = context.getResources();
        DisplayMetrics display = res.getDisplayMetrics();
        return MaterialAttributes.resolveOrThrow(context, Math.min(display.widthPixels, display.heightPixels) > res.getDimensionPixelSize(C0552R.dimen.mtrl_calendar_maximum_default_fullscreen_minor_axis) ? C0552R.attr.materialCalendarTheme : C0552R.attr.materialCalendarFullscreenTheme, MaterialDatePicker.class.getCanonicalName());
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public String getSelectionDisplayString(Context context) {
        Resources res = context.getResources();
        Long l = this.selectedStartItem;
        if (l == null && this.selectedEndItem == null) {
            return res.getString(C0552R.string.mtrl_picker_range_header_unselected);
        }
        Long l2 = this.selectedEndItem;
        if (l2 == null) {
            return res.getString(C0552R.string.mtrl_picker_range_header_only_start_selected, DateStrings.getDateString(this.selectedStartItem.longValue()));
        } else if (l == null) {
            return res.getString(C0552R.string.mtrl_picker_range_header_only_end_selected, DateStrings.getDateString(this.selectedEndItem.longValue()));
        } else {
            Pair<String, String> dateRangeStrings = DateStrings.getDateRangeString(l, l2);
            return res.getString(C0552R.string.mtrl_picker_range_header_selected, dateRangeStrings.first, dateRangeStrings.second);
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public int getDefaultTitleResId() {
        return C0552R.string.mtrl_picker_range_header_title;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public View onCreateTextInputView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, CalendarConstraints constraints, final OnSelectionChangedListener<Pair<Long, Long>> listener) {
        View root = layoutInflater.inflate(C0552R.layout.mtrl_picker_text_input_date_range, viewGroup, false);
        final TextInputLayout startTextInput = (TextInputLayout) root.findViewById(C0552R.C0555id.mtrl_picker_text_input_range_start);
        final TextInputLayout endTextInput = (TextInputLayout) root.findViewById(C0552R.C0555id.mtrl_picker_text_input_range_end);
        EditText startEditText = startTextInput.getEditText();
        EditText endEditText = endTextInput.getEditText();
        if (ManufacturerUtils.isDateInputKeyboardMissingSeparatorCharacters()) {
            startEditText.setInputType(17);
            endEditText.setInputType(17);
        }
        this.invalidRangeStartError = root.getResources().getString(C0552R.string.mtrl_picker_invalid_range);
        SimpleDateFormat format = UtcDates.getTextInputFormat();
        Long l = this.selectedStartItem;
        if (l != null) {
            startEditText.setText(format.format(l));
            this.proposedTextStart = this.selectedStartItem;
        }
        Long l2 = this.selectedEndItem;
        if (l2 != null) {
            endEditText.setText(format.format(l2));
            this.proposedTextEnd = this.selectedEndItem;
        }
        String formatHint = UtcDates.getTextInputHint(root.getResources(), format);
        startTextInput.setPlaceholderText(formatHint);
        endTextInput.setPlaceholderText(formatHint);
        startEditText.addTextChangedListener(new DateFormatTextWatcher(formatHint, format, constraints, startTextInput) {
            /* class com.google.android.material.datepicker.RangeDateSelector.C06311 */

            /* access modifiers changed from: package-private */
            @Override // com.google.android.material.datepicker.DateFormatTextWatcher
            public void onValidDate(Long day) {
                RangeDateSelector.this.proposedTextStart = day;
                RangeDateSelector.this.updateIfValidTextProposal(startTextInput, endTextInput, listener);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.android.material.datepicker.DateFormatTextWatcher
            public void onInvalidDate() {
                RangeDateSelector.this.proposedTextStart = null;
                RangeDateSelector.this.updateIfValidTextProposal(startTextInput, endTextInput, listener);
            }
        });
        endEditText.addTextChangedListener(new DateFormatTextWatcher(formatHint, format, constraints, endTextInput) {
            /* class com.google.android.material.datepicker.RangeDateSelector.C06322 */

            /* access modifiers changed from: package-private */
            @Override // com.google.android.material.datepicker.DateFormatTextWatcher
            public void onValidDate(Long day) {
                RangeDateSelector.this.proposedTextEnd = day;
                RangeDateSelector.this.updateIfValidTextProposal(startTextInput, endTextInput, listener);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.android.material.datepicker.DateFormatTextWatcher
            public void onInvalidDate() {
                RangeDateSelector.this.proposedTextEnd = null;
                RangeDateSelector.this.updateIfValidTextProposal(startTextInput, endTextInput, listener);
            }
        });
        ViewUtils.requestFocusAndShowKeyboard(startEditText);
        return root;
    }

    private boolean isValidRange(long start, long end) {
        return start <= end;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateIfValidTextProposal(TextInputLayout startTextInput, TextInputLayout endTextInput, OnSelectionChangedListener<Pair<Long, Long>> listener) {
        Long l = this.proposedTextStart;
        if (l == null || this.proposedTextEnd == null) {
            clearInvalidRange(startTextInput, endTextInput);
            listener.onIncompleteSelectionChanged();
        } else if (isValidRange(l.longValue(), this.proposedTextEnd.longValue())) {
            this.selectedStartItem = this.proposedTextStart;
            this.selectedEndItem = this.proposedTextEnd;
            listener.onSelectionChanged(getSelection());
        } else {
            setInvalidRange(startTextInput, endTextInput);
            listener.onIncompleteSelectionChanged();
        }
    }

    private void clearInvalidRange(TextInputLayout start, TextInputLayout end) {
        if (start.getError() != null && this.invalidRangeStartError.contentEquals(start.getError())) {
            start.setError(null);
        }
        if (end.getError() != null && " ".contentEquals(end.getError())) {
            end.setError(null);
        }
    }

    private void setInvalidRange(TextInputLayout start, TextInputLayout end) {
        start.setError(this.invalidRangeStartError);
        end.setError(" ");
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.selectedStartItem);
        dest.writeValue(this.selectedEndItem);
    }
}
