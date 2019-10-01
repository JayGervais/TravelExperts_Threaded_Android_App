package com.example.day10_assignment_v1.booking;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookingDateSelect
{
    public static void BookDate(final Context cont, final TextView startDate,
                                final TextView endDate, final Button dateButton)
    {
        final Calendar calStartDate = Calendar.getInstance();
        final Calendar calEndDate = Calendar.getInstance();

        // calendar start date
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                calStartDate.set(Calendar.YEAR, year);
                calStartDate.set(Calendar.MONTH, month);
                calStartDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel()
            {
                String myFormat = "yyy/MM/dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                startDate.setText(sdf.format(calStartDate.getTime()));
                dateButton.setEnabled(true);
            }
        };

        // end date picker
        startDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(cont, date, calStartDate
                        .get(Calendar.YEAR), calStartDate.get(Calendar.MONTH),
                        calStartDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final DatePickerDialog.OnDateSetListener dateEnd = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                calEndDate.set(Calendar.YEAR, year);
                calEndDate.set(Calendar.MONTH, month);
                calEndDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel()
            {
                String myFormat = "yyy/MM/dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                endDate.setText(sdf.format(calEndDate.getTime()));
            }
        };

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(cont, dateEnd, calEndDate
                        .get(Calendar.YEAR), calEndDate.get(Calendar.MONTH),
                        calEndDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

}
