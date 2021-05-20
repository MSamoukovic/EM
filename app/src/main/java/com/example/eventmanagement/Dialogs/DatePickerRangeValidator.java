package com.example.eventmanagement.Dialogs;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.material.datepicker.CalendarConstraints;

public class DatePickerRangeValidator implements CalendarConstraints.DateValidator {
    long minDate, maxDate;

    public DatePickerRangeValidator(long minDate, long maxDate) {
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    DatePickerRangeValidator(Parcel parcel) {
        minDate = parcel.readLong();
        maxDate = parcel.readLong();
    }

    @Override
    public boolean isValid(long date) {
        return !(minDate > date || maxDate < date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(minDate);
        dest.writeLong(maxDate);
    }

    public static final Parcelable.Creator<DatePickerRangeValidator> CREATOR = new Parcelable.Creator<DatePickerRangeValidator>() {

        @Override
        public DatePickerRangeValidator createFromParcel(Parcel parcel) {
            return new DatePickerRangeValidator(parcel);
        }

        @Override
        public DatePickerRangeValidator[] newArray(int size) {
            return new DatePickerRangeValidator[size];
        }
    };
}

