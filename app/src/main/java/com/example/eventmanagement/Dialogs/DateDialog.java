package com.example.eventmanagement.Dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eventmanagement.R;

public class DateDialog extends DatePickerDialog {
    private final DatePickerDialog.OnDateSetListener onDateSetListener;

    public DateDialog(@NonNull Context context, @Nullable OnDateSetListener listener, int year, int month, int dayOfMonth) {
        super(context, R.style.datePickerDialogTheme, listener, year, month, dayOfMonth);
        onDateSetListener = listener;
    }

    @Override
    public void onClick(@NonNull DialogInterface dialog, int which) {
        super.onClick(dialog, which);
    }
}

