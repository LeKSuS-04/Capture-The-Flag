package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.material.C0552R;
import com.google.android.material.timepicker.TimeModel;
import java.util.Collection;

/* access modifiers changed from: package-private */
public class MonthAdapter extends BaseAdapter {
    static final int MAXIMUM_WEEKS = UtcDates.getUtcCalendar().getMaximum(4);
    final CalendarConstraints calendarConstraints;
    CalendarStyle calendarStyle;
    final DateSelector<?> dateSelector;
    final Month month;
    private Collection<Long> previouslySelectedDates;

    MonthAdapter(Month month2, DateSelector<?> dateSelector2, CalendarConstraints calendarConstraints2) {
        this.month = month2;
        this.dateSelector = dateSelector2;
        this.calendarConstraints = calendarConstraints2;
        this.previouslySelectedDates = dateSelector2.getSelectedDays();
    }

    public boolean hasStableIds() {
        return true;
    }

    public Long getItem(int position) {
        if (position < this.month.daysFromStartOfWeekToFirstOfMonth() || position > lastPositionInMonth()) {
            return null;
        }
        return Long.valueOf(this.month.getDay(positionToDay(position)));
    }

    public long getItemId(int position) {
        return (long) (position / this.month.daysInWeek);
    }

    public int getCount() {
        return this.month.daysInMonth + firstPositionInMonth();
    }

    public TextView getView(int position, View convertView, ViewGroup parent) {
        initializeStyles(parent.getContext());
        TextView day = (TextView) convertView;
        if (convertView == null) {
            day = (TextView) LayoutInflater.from(parent.getContext()).inflate(C0552R.layout.mtrl_calendar_day, parent, false);
        }
        int offsetPosition = position - firstPositionInMonth();
        if (offsetPosition < 0 || offsetPosition >= this.month.daysInMonth) {
            day.setVisibility(8);
            day.setEnabled(false);
        } else {
            int dayNumber = offsetPosition + 1;
            day.setTag(this.month);
            day.setText(String.format(day.getResources().getConfiguration().locale, TimeModel.NUMBER_FORMAT, Integer.valueOf(dayNumber)));
            long dayInMillis = this.month.getDay(dayNumber);
            if (this.month.year == Month.current().year) {
                day.setContentDescription(DateStrings.getMonthDayOfWeekDay(dayInMillis));
            } else {
                day.setContentDescription(DateStrings.getYearMonthDayOfWeekDay(dayInMillis));
            }
            day.setVisibility(0);
            day.setEnabled(true);
        }
        Long date = getItem(position);
        if (date == null) {
            return day;
        }
        updateSelectedState(day, date.longValue());
        return day;
    }

    public void updateSelectedStates(MaterialCalendarGridView monthGrid) {
        for (Long date : this.previouslySelectedDates) {
            updateSelectedStateForDate(monthGrid, date.longValue());
        }
        DateSelector<?> dateSelector2 = this.dateSelector;
        if (dateSelector2 != null) {
            for (Long date2 : dateSelector2.getSelectedDays()) {
                updateSelectedStateForDate(monthGrid, date2.longValue());
            }
            this.previouslySelectedDates = this.dateSelector.getSelectedDays();
        }
    }

    private void updateSelectedStateForDate(MaterialCalendarGridView monthGrid, long date) {
        if (Month.create(date).equals(this.month)) {
            updateSelectedState((TextView) monthGrid.getChildAt(monthGrid.getAdapter().dayToPosition(this.month.getDayOfMonth(date)) - monthGrid.getFirstVisiblePosition()), date);
        }
    }

    private void updateSelectedState(TextView day, long date) {
        CalendarItemStyle style;
        if (day != null) {
            if (this.calendarConstraints.getDateValidator().isValid(date)) {
                day.setEnabled(true);
                if (isSelected(date)) {
                    style = this.calendarStyle.selectedDay;
                } else if (UtcDates.getTodayCalendar().getTimeInMillis() == date) {
                    style = this.calendarStyle.todayDay;
                } else {
                    style = this.calendarStyle.day;
                }
            } else {
                day.setEnabled(false);
                style = this.calendarStyle.invalidDay;
            }
            style.styleItem(day);
        }
    }

    private boolean isSelected(long date) {
        for (Long l : this.dateSelector.getSelectedDays()) {
            if (UtcDates.canonicalYearMonthDay(date) == UtcDates.canonicalYearMonthDay(l.longValue())) {
                return true;
            }
        }
        return false;
    }

    private void initializeStyles(Context context) {
        if (this.calendarStyle == null) {
            this.calendarStyle = new CalendarStyle(context);
        }
    }

    /* access modifiers changed from: package-private */
    public int firstPositionInMonth() {
        return this.month.daysFromStartOfWeekToFirstOfMonth();
    }

    /* access modifiers changed from: package-private */
    public int lastPositionInMonth() {
        return (this.month.daysFromStartOfWeekToFirstOfMonth() + this.month.daysInMonth) - 1;
    }

    /* access modifiers changed from: package-private */
    public int positionToDay(int position) {
        return (position - this.month.daysFromStartOfWeekToFirstOfMonth()) + 1;
    }

    /* access modifiers changed from: package-private */
    public int dayToPosition(int day) {
        return firstPositionInMonth() + (day - 1);
    }

    /* access modifiers changed from: package-private */
    public boolean withinMonth(int position) {
        return position >= firstPositionInMonth() && position <= lastPositionInMonth();
    }

    /* access modifiers changed from: package-private */
    public boolean isFirstInRow(int position) {
        return position % this.month.daysInWeek == 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isLastInRow(int position) {
        return (position + 1) % this.month.daysInWeek == 0;
    }
}
