package com.example.bpdctimetableapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.FragmentActivity;


public class AddReminder extends FragmentActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private Button pickTimeButton, pickDateButton;
    private TextView pickTimeTextView, pickDateTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reminder);

        AppCompatCheckBox checkBox = findViewById(R.id.all_day_checkbox);
        pickTimeButton = findViewById(R.id.pick_time_button);
        pickDateButton = findViewById(R.id.pick_date_button);
        pickTimeTextView = findViewById(R.id.pick_time_tv);
        pickDateTextView = findViewById(R.id.pick_date_tv);

        pickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate(v);
            }
        });

        pickTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickTime(v);
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAllDay(v);
            }
        });
    }

    public void checkAllDay(View view) {
        AppCompatCheckBox checkBox = (AppCompatCheckBox) view;
        if(checkBox.isChecked()) {
            pickTimeButton.setVisibility(View.INVISIBLE);
            pickTimeTextView.setVisibility(View.INVISIBLE);
        }
        else {
            pickTimeButton.setVisibility(View.VISIBLE);
            pickTimeTextView.setVisibility(View.VISIBLE);
        }
    }

    public void pickDate(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void pickTime(View v) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this, this, Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE), true);
        timePickerDialog.show();    
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String setDate = String.format("%d-%d-%d", dayOfMonth, month, year);
        pickDateTextView.setText(setDate.toString());
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String setTime = String.format("%d:%d", hourOfDay, minute);
        pickTimeTextView.setText(setTime.toString());
    }
}