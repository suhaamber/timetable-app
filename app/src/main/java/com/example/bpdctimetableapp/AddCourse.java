package com.example.bpdctimetableapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class AddCourse extends Activity {

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
    private DatePickerDialog.OnDateSetListener dateListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);


        parentView = findViewById(R.id.parent_view);
        setDate = "";

        newCourseCards = new ArrayList<>();
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
                }
                catch (Exception e) {
                    Toast.makeText(AddCourse.this, "Error creating course.", Toast.LENGTH_LONG).show();
                }

                //insert the course name and instructor into the database
                //collect the course ID
                DatabaseHelper databaseHelper = new DatabaseHelper(AddCourse.this);
                int courseId = databaseHelper.addCourse(courseModel);

                TimetableModel timetableModel = null;

                //collect course card information
                //insert course hours information into database
                for(int i=0; i<newCourseCards.size(); i++) {
                    String classType = newCourseCards.get(i).getClassType();
                    ArrayList<ClassDayHour> tempClassHour = newCourseCards.get(i).getClassDayHours();
                    int sizeOfClassHours = tempClassHour.size();
                    for (int j = 0; j < sizeOfClassHours; j++) {
                        try {
                            int hour = tempClassHour.get(j).getClassHour();
                            int day = tempClassHour.get(j).getClassDay();
                            timetableModel = new TimetableModel(courseId, classType, hour, day);
                        } catch (Exception e) {
                            Toast.makeText(AddCourse.this, "Error creating course card index " + i, Toast.LENGTH_LONG).show();
                        }
                        boolean success = databaseHelper.addTimetable(timetableModel);
                        if (!success) {
                            Toast.makeText(AddCourse.this, "Error inserting course card at index " + i, Toast.LENGTH_LONG).show();
                        }
                    }
                }

                //collect eval card information
                //insert evaluation details into database
                EvaluationModel evaluationModel = null;
                for (int i = 0; i < newEvalCards.size(); i++) {
                    String evalType = newEvalCards.get(i).getEvalType();
                    String evalDate = newEvalCards.get(i).getEvalDate();
                    try {
                        evaluationModel = new EvaluationModel(courseId, evalType, evalDate);
                    }
                    catch (Exception e) {
                        Toast.makeText(AddCourse.this, "Error creating eval card at index " + i, Toast.LENGTH_LONG).show();
                    }
                    boolean success = databaseHelper.addEvaluation(evaluationModel);
                    if (!success) {
                        Toast.makeText(AddCourse.this, "Error inserting eval card at index " + i, Toast.LENGTH_LONG).show();
                    }
                }

                databaseHelper.close();
                Intent intent = new Intent(AddCourse.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void createEvalCards() {
        newEvalCards = new ArrayList<>();
    }

    public void buildCourseRecyclerView() {
        recyclerViewCourse = findViewById(R.id.recyclerViewClasses);
        layoutManagerCourse = new LinearLayoutManager(this);
        adapterCourse = new NewCourseCardAdapter(newCourseCards);

        recyclerViewCourse.setLayoutManager(layoutManagerCourse);
        recyclerViewCourse.setAdapter(adapterCourse);

        adapterCourse.setOnItemClickListener(new NewCourseCardAdapter.OnItemClickListener() {
            @Override
            public void onSelectClassClick(int this_position, View view) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View selectClassPopup = inflater.inflate(R.layout.select_class_hours, AddCourse.this.findViewById(R.id.popup_element));

                selectHoursPopup = new PopupWindow(
                        selectClassPopup,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                selectHoursPopup.setElevation(5.0f);
                Button doneButton = selectClassPopup.findViewById(R.id.done_button_popup);
                TextView classHoursTV = findViewById(R.id.class_hours_tv);

                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ClassDayHour temp;
                        String textViewString = "";
                        ClassHoursIds classHoursIds = new ClassHoursIds();
                        for(int dayCount = 0; dayCount < 5 ; dayCount++)
                            for(int hourCount = 0; hourCount < 9 ; hourCount++ ) {
                                CheckBox checkBox = selectClassPopup.findViewById(classHoursIds.ids.get(dayCount).get(hourCount));
                                if (checkBox.isChecked()) {
                                    temp = new ClassDayHour(hourCount+1, dayCount+1);
                                    textViewString += String.valueOf(dayCount) + String.valueOf(hourCount) + " ";
                                    AddCourse.this.newCourseCards.get(this_position).addClassDayHours(temp);
                                }
                            }
                            selectHoursPopup.dismiss();

                            //show chosen hours in the course card
                            newCourseCards.get(this_position).setButtonText(getResources().getString(R.string.edit_class_hours_button));
                            newCourseCards.get(this_position).setClassHourLabel(textViewString);
                            adapterCourse.notifyItemChanged(this_position);

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

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddCourse.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String temp = String.format("%d-%d-%d", dayOfMonth, (month+1), year);
                                newEvalCards.get(position).dateLabelChange(temp);
                                newEvalCards.get(position).setEvalDate(temp);
                                newEvalCards.get(position).setButtonText(getString(R.string.edit_eval_date));
                                adapterEval.notifyItemChanged(position);
                            }
                        },
                        year, month, day
                );
                datePickerDialog.show();
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
        newCourseCards.add(new NewCourseCard(getResources().getString(R.string.select_class_hours_button)));
        adapterCourse.notifyDataSetChanged();
    }

    public void addNewEvalCard(View view) {
        newEvalCards.add((new NewEvalCard(null,null, getString(R.string.select_eval_date_button))));
        adapterEval.notifyDataSetChanged();
    }

    //public void pickDate(View view) {
      //  DatePickerDialog datePickerDialog = new DatePickerDialog(
        //        this, this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
      //  datePickerDialog.show();
    //}
}
