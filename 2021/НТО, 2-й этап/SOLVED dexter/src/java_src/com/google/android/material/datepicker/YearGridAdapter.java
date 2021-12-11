package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.C0552R;
import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.timepicker.TimeModel;
import java.util.Calendar;
import java.util.Locale;

/* access modifiers changed from: package-private */
public class YearGridAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final MaterialCalendar<?> materialCalendar;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;

        ViewHolder(TextView view) {
            super(view);
            this.textView = view;
        }
    }

    YearGridAdapter(MaterialCalendar<?> materialCalendar2) {
        this.materialCalendar = materialCalendar2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(C0552R.layout.mtrl_calendar_year, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        int year = getYearForPosition(position);
        String navigateYear = viewHolder.textView.getContext().getString(C0552R.string.mtrl_picker_navigate_to_year_description);
        viewHolder.textView.setText(String.format(Locale.getDefault(), TimeModel.NUMBER_FORMAT, Integer.valueOf(year)));
        viewHolder.textView.setContentDescription(String.format(navigateYear, Integer.valueOf(year)));
        CalendarStyle styles = this.materialCalendar.getCalendarStyle();
        Calendar calendar = UtcDates.getTodayCalendar();
        CalendarItemStyle style = calendar.get(1) == year ? styles.todayYear : styles.year;
        for (Long day : this.materialCalendar.getDateSelector().getSelectedDays()) {
            calendar.setTimeInMillis(day.longValue());
            if (calendar.get(1) == year) {
                style = styles.selectedYear;
            }
        }
        style.styleItem(viewHolder.textView);
        viewHolder.textView.setOnClickListener(createYearClickListener(year));
    }

    private View.OnClickListener createYearClickListener(final int year) {
        return new View.OnClickListener() {
            /* class com.google.android.material.datepicker.YearGridAdapter.View$OnClickListenerC06371 */

            public void onClick(View view) {
                YearGridAdapter.this.materialCalendar.setCurrentMonth(YearGridAdapter.this.materialCalendar.getCalendarConstraints().clamp(Month.create(year, YearGridAdapter.this.materialCalendar.getCurrentMonth().month)));
                YearGridAdapter.this.materialCalendar.setSelector(MaterialCalendar.CalendarSelector.DAY);
            }
        };
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.materialCalendar.getCalendarConstraints().getYearSpan();
    }

    /* access modifiers changed from: package-private */
    public int getPositionForYear(int year) {
        return year - this.materialCalendar.getCalendarConstraints().getStart().year;
    }

    /* access modifiers changed from: package-private */
    public int getYearForPosition(int position) {
        return this.materialCalendar.getCalendarConstraints().getStart().year + position;
    }
}
