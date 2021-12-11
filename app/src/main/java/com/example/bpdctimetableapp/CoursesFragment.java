package com.example.bpdctimetableapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CoursesFragment extends Fragment {

    private RecyclerView recyclerViewCourse;
    private NewViewCourseAdapter newViewCourseAdapter;
    private RecyclerView.LayoutManager layoutManagerCourse;
    private ArrayList<NewViewCourseCard> newViewCourseCards;
    private ConstraintLayout parentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_courses, container, false);
        parentView = rootView.findViewById(R.id.parent_view);

        //create course cards
        //access database and create cards
        createCourseCards();

        //attach adapter and layout manager
        buildCourseRecyclerView();


        return rootView;
    }

    private void createCourseCards() {

        //access database and insert information into cards
        DatabaseHelper db = new DatabaseHelper(CoursesFragment.this.getContext());
        newViewCourseCards = db.getCourses();

    }

    private void buildCourseRecyclerView() {
        recyclerViewCourse = parentView.findViewById(R.id.view_courses_recycler_view);
        layoutManagerCourse = new LinearLayoutManager(this.getContext());
        newViewCourseAdapter = new NewViewCourseAdapter(newViewCourseCards);

        recyclerViewCourse.setLayoutManager(layoutManagerCourse);
        recyclerViewCourse.setAdapter(newViewCourseAdapter);
        recyclerViewCourse.setNestedScrollingEnabled(false);

        newViewCourseAdapter.setOnItemClickListener(new NewViewCourseAdapter.OnItemClickListener() {
            @Override
            public void onCourseClick(int position, View view) {
                //create a new activity to show course details
                //create an intent and pass on the courseID
            }
        });



    }
}
