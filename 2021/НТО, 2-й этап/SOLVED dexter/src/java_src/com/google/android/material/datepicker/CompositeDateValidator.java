package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.util.Preconditions;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.List;

public final class CompositeDateValidator implements CalendarConstraints.DateValidator {
    private static final Operator ALL_OPERATOR = new Operator() {
        /* class com.google.android.material.datepicker.CompositeDateValidator.C06072 */

        @Override // com.google.android.material.datepicker.CompositeDateValidator.Operator
        public boolean isValid(List<CalendarConstraints.DateValidator> validators, long date) {
            for (CalendarConstraints.DateValidator validator : validators) {
                if (validator != null && !validator.isValid(date)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.android.material.datepicker.CompositeDateValidator.Operator
        public int getId() {
            return 2;
        }
    };
    private static final Operator ANY_OPERATOR = new Operator() {
        /* class com.google.android.material.datepicker.CompositeDateValidator.C06061 */

        @Override // com.google.android.material.datepicker.CompositeDateValidator.Operator
        public boolean isValid(List<CalendarConstraints.DateValidator> validators, long date) {
            for (CalendarConstraints.DateValidator validator : validators) {
                if (validator != null && validator.isValid(date)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.android.material.datepicker.CompositeDateValidator.Operator
        public int getId() {
            return 1;
        }
    };
    private static final int COMPARATOR_ALL_ID = 2;
    private static final int COMPARATOR_ANY_ID = 1;
    public static final Parcelable.Creator<CompositeDateValidator> CREATOR = new Parcelable.Creator<CompositeDateValidator>() {
        /* class com.google.android.material.datepicker.CompositeDateValidator.C06083 */

        @Override // android.os.Parcelable.Creator
        public CompositeDateValidator createFromParcel(Parcel source) {
            Operator operator;
            List<CalendarConstraints.DateValidator> validators = source.readArrayList(CalendarConstraints.DateValidator.class.getClassLoader());
            int id = source.readInt();
            if (id == 2) {
                operator = CompositeDateValidator.ALL_OPERATOR;
            } else if (id == 1) {
                operator = CompositeDateValidator.ANY_OPERATOR;
            } else {
                operator = CompositeDateValidator.ALL_OPERATOR;
            }
            return new CompositeDateValidator((List) Preconditions.checkNotNull(validators), operator);
        }

        @Override // android.os.Parcelable.Creator
        public CompositeDateValidator[] newArray(int size) {
            return new CompositeDateValidator[size];
        }
    };
    private final Operator operator;
    private final List<CalendarConstraints.DateValidator> validators;

    /* access modifiers changed from: private */
    public interface Operator {
        int getId();

        boolean isValid(List<CalendarConstraints.DateValidator> list, long j);
    }

    private CompositeDateValidator(List<CalendarConstraints.DateValidator> validators2, Operator operator2) {
        this.validators = validators2;
        this.operator = operator2;
    }

    public static CalendarConstraints.DateValidator allOf(List<CalendarConstraints.DateValidator> validators2) {
        return new CompositeDateValidator(validators2, ALL_OPERATOR);
    }

    public static CalendarConstraints.DateValidator anyOf(List<CalendarConstraints.DateValidator> validators2) {
        return new CompositeDateValidator(validators2, ANY_OPERATOR);
    }

    @Override // com.google.android.material.datepicker.CalendarConstraints.DateValidator
    public boolean isValid(long date) {
        return this.operator.isValid(this.validators, date);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.validators);
        dest.writeInt(this.operator.getId());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompositeDateValidator)) {
            return false;
        }
        CompositeDateValidator that = (CompositeDateValidator) o;
        if (!this.validators.equals(that.validators) || this.operator.getId() != that.operator.getId()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.validators.hashCode();
    }
}
