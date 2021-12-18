package com.example.bpdctimetableapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<HomeData> homeDataArrayList;
    private RelativeLayout parentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        parentView = rootView.findViewById(R.id.parent_view);

        FloatingActionButton fabCourses = rootView.findViewById(R.id.fab_courses);
        fabCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCourse(v);
            }
        });

        FloatingActionButton fabReminders = rootView.findViewById(R.id.fab_reminder);
        fabReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReminder(v);
            }
        });

        FloatingActionButton fabAttendance = rootView.findViewById(R.id.fab_attendance);
        fabAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAttendance(v);
            }
        });



        //create cards
        //access database and create cards
        createCards();

        //attach adapter and build recycler view
        buildRecyclerView();

        return rootView;
    }

    public void createCards() {
        DatabaseHelper db = new DatabaseHelper(HomeFragment.this.getContext());
        homeDataArrayList = db.getSchedule();

        homeDataArrayList.get(0).setSectionDate("Sunday");
        homeDataArrayList.get(1).setSectionDate("Monday");
        homeDataArrayList.get(2).setSectionDate("Tuesday");
        homeDataArrayList.get(3).setSectionDate("Wednesday");
        homeDataArrayList.get(4).setSectionDate("Thursday");

    }

    public void buildRecyclerView() {
        recyclerView = parentView.findViewById(R.id.view_timetable);
        layoutManager = new LinearLayoutManager(this.getContext());
        homeAdapter = new HomeAdapter(homeDataArrayList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        //TODO: add listener for schedule cards
    }

    public void addCourse(View view) {
        Intent addCourseIntent = new Intent(getActivity(), AddCourse.class);
        startActivity(addCourseIntent);
    }

    public void addReminder(View view) {
        Intent addReminderIntent = new Intent(getActivity(), AddReminder.class);
        startActivity(addReminderIntent);
    }

    public void addAttendance(View view) {
        Intent addAttendanceIntent = new Intent(getActivity(), AddAttendance.class);
        startActivity(addAttendanceIntent);
    }

}
