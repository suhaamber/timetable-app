package com.example.bpdctimetableapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddCourse extends Activity implements DatePickerDialog.OnDateSetListener {

    private RecyclerView recyclerViewCourse, recyclerViewEval;
    private NewCourseCardAdapter adapterCourse;
    private NewEvalCardAdapter adapterEval;
    private RecyclerView.LayoutManager layoutManagerCourse, layoutManagerEval;
    private ArrayList<NewCourseCard> newCourseCards;
    private ArrayList<NewEvalCard> newEvalCards;
    private ImageButton addCourseCard, addEvalCard;
    private String setDate;
    private PopupWindow selectHoursPopup;
    private ScrollView parentView;
    private Button addCourse;
    private EditText courseNameET, instructorNameET;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);


        parentView = findViewById(R.id.parent_view);
        setDate = "";

        createCourseCards();
        buildCourseRecyclerView();

        createEvalCards();
        buildEvalRecyclerView();

        addCourseCard = findViewById(R.id.button_add_course_card);
        addEvalCard = findViewById(R.id.button_add_eval_card);
        addCourse = findViewById(R.id.button_add_course);
        courseNameET = findViewById(R.id.course_name_et);
        instructorNameET = findViewById(R.id.instructor_name_et);

        addCourseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewCourseCard(v);
            }
        });

        addEvalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewEvalCard(v);
            }
        });

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseModel courseModel = null;
                try {
                    //add conditions to check whether row is empty or not AKA the edit text have text or not, else do not enter the information.
                    courseModel = new CourseModel(-1, courseNameET.getText().toString(), instructorNameET.getText().toString());
                    Toast.makeText(AddCourse.this, "Successfully created course!", Toast.LENGTH_LONG).show();
                }
                catch (Exception e) {
                    Toast.makeText(AddCourse.this, "Error creating course.", Toast.LENGTH_LONG).show();
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(AddCourse.this);
                boolean success = databaseHelper.addCourse(courseModel);
                databaseHelper.close();

            }
        });

    }

    public void createCourseCards() {
        newCourseCards = new ArrayList<>();
        //newCourseCards.add(new NewCourseCard());
    }

    public void createEvalCards() {
        newEvalCards = new ArrayList<>();
        newEvalCards.add(new NewEvalCard("", getString(R.string.select_eval_date_button)));
    }

    public void buildCourseRecyclerView() {
        recyclerViewCourse = findViewById(R.id.recyclerViewClasses);
        layoutManagerCourse = new LinearLayoutManager(this);
        adapterCourse = new NewCourseCardAdapter(newCourseCards);

        recyclerViewCourse.setLayoutManager(layoutManagerCourse);
        recyclerViewCourse.setAdapter(adapterCourse);

        adapterCourse.setOnItemClickListener(new NewCourseCardAdapter.OnItemClickListener() {
            @Override
            public void onSelectClassClick(int position, View view) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View selectClassPopup = inflater.inflate(R.layout.select_class_hours, null);

                selectHoursPopup = new PopupWindow(
                        selectClassPopup,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                selectHoursPopup.setElevation(5.0f);
                Button doneButton = selectClassPopup.findViewById(R.id.done_button_popup);
                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectHoursPopup.dismiss();
                    }
                });

                selectHoursPopup.showAtLocation(parentView, Gravity.CENTER, 0, 0);
            }

            @Override
            public void onDeleteClick(int position) {
                removeCourseItem(position);
            }
        });
    }

    public void buildEvalRecyclerView() {
        recyclerViewEval = findViewById(R.id.recyclerViewEval);
        layoutManagerEval = new LinearLayoutManager(this);
        adapterEval = new NewEvalCardAdapter(newEvalCards);

        recyclerViewEval.setLayoutManager(layoutManagerEval);
        recyclerViewEval.setAdapter(adapterEval);

        adapterEval.setOnItemClickListener(new NewEvalCardAdapter.OnItemClickListener() {

            @Override
            public void onDeleteClick(int position) {
                removeEvalItem(position);
            }

            @Override
            public void onSetDateClick(int position, View view) {
                pickDate(view);
                newEvalCards.get(position).dateLabelChange(setDate);
                newEvalCards.get(position).buttonTextChange(getString(R.string.edit_eval_date));
                adapterEval.notifyItemChanged(position);
            }
        });
    }

    public void removeCourseItem(int position) {
        newCourseCards.remove(position);
        adapterCourse.notifyItemRemoved(position);
    }

    public void removeEvalItem(int position) {
        newEvalCards.remove(position);
        adapterEval.notifyItemRemoved(position);
    }

    public void addNewCourseCard(View view) {
        newCourseCards.add(new NewCourseCard());
        adapterCourse.notifyDataSetChanged();
    }

    public void addNewEvalCard(View view) {
        newEvalCards.add((new NewEvalCard("", getString(R.string.select_eval_date_button))));
        adapterEval.notifyDataSetChanged();
    }

    public void pickDate(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        setDate = String.format("%d-%d-%d", dayOfMonth, month, year);
    }
}
