package com.example.bpdctimetableapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.FragmentActivity;


public class AddReminder extends FragmentActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private Button pickTimeButton, pickDateButton, addReminderButton;
    private TextView pickTimeTextView, pickDateTextView;
    private EditText reminderTitleET, reminderTagsET;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reminder);

        AppCompatCheckBox checkBox = findViewById(R.id.all_day_checkbox);
        pickTimeButton = findViewById(R.id.pick_time_button);
        pickDateButton = findViewById(R.id.pick_date_button);
        pickTimeTextView = findViewById(R.id.pick_time_tv);
        pickDateTextView = findViewById(R.id.pick_date_tv);
        addReminderButton = findViewById(R.id.button_add_reminder);
        reminderTitleET = findViewById(R.id.reminder_title_ET);
        reminderTagsET = findViewById(R.id.reminder_tags_ET);

        addReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReminderModel reminderModel = null;

                try {
                    String reminderDateTime = pickDateTextView.getText().toString() +  pickTimeTextView.getText().toString();
                    reminderModel = new ReminderModel(-1, reminderTitleET.getText().toString(), reminderDateTime );
                }
                catch (Exception e) {
                    Toast.makeText(AddReminder.this, "Error creating reminder.", Toast.LENGTH_LONG).show();
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(AddReminder.this);
                int reminderId = 0;
                //insert reminder title and date into database
                try {
                    reminderId = databaseHelper.addReminder(reminderModel);
                }
                catch (Exception e) {
                    Toast.makeText(AddReminder.this, "Error inserting reminder.", Toast.LENGTH_LONG).show();
                }

                ReminderTagModel reminderTagModel = null;
                try {
                    reminderTagModel = new ReminderTagModel(reminderId, reminderTagsET.getText().toString());
                }
                catch (Exception e) {
                    Toast.makeText(AddReminder.this, "Error adding reminder tags." , Toast.LENGTH_LONG).show();
                }

                //insert reminder tags into database
                try {
                    databaseHelper.addReminderTags(reminderTagModel);
                }
                catch (Exception e) {
                    Toast.makeText(AddReminder.this, "Error inserting reminder tags.", Toast.LENGTH_LONG).show();
                }

                databaseHelper.close();
                Intent intent = new Intent(AddReminder.this, MainActivity.class);
                startActivity(intent);
            }
        });

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