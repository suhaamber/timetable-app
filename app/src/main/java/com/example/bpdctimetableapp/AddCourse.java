package com.example.bpdctimetableapp;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddCourse extends Activity {

    private RecyclerView recyclerViewCourse;
    private RecyclerView.Adapter adapterCourse;
    private RecyclerView.LayoutManager layoutManagerCourse;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);

        ArrayList<NewCourseCard> newCourseCards = new ArrayList<>();
        //newCourseCards.add(new NewCourseCard());


        recyclerViewCourse = findViewById(R.id.recyclerViewClasses);
        layoutManagerCourse = new LinearLayoutManager(this);
        adapterCourse = new NewCourseCardAdapter(newCourseCards);

        recyclerViewCourse.setLayoutManager(layoutManagerCourse);
        recyclerViewCourse.setAdapter(adapterCourse);
    }
}
