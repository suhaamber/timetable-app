package com.example.bpdctimetableapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CourseView extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_course);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent newIntent = this.getIntent();
        Bundle extras = newIntent.getExtras();
        int courseId = extras.getInt("COURSE_ID");

        DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<String> classList = db.getTimetable(courseId);
        CourseModel courseDetails = db.getCourseName(courseId);
        ArrayList<String> evaluationDetails = db.getEvaluationDetails(courseId);

        TextView courseNameTV = findViewById(R.id.course_name_et);
        courseNameTV.setText(courseDetails.getCourseName());

        TextView instructorNameTV = findViewById(R.id.instructor_name_et);
        instructorNameTV.setText(courseDetails.getInstructorName());

        ListView courseListView = findViewById(R.id.class_list_view);
        ArrayAdapter courseAdapter = new ArrayAdapter<>(this, R.layout.course_list_item, classList);
        courseListView.setAdapter(courseAdapter);

        ListView evaluationListView = findViewById(R.id.eval_list_view);
        ArrayAdapter evalAdapter = new ArrayAdapter<>(this, R.layout.course_list_item, evaluationDetails);
        evaluationListView.setAdapter(evalAdapter);
    }
}
