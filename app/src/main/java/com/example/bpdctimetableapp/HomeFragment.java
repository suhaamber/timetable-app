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
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<HomeData> homeDataArrayList;
    private RelativeLayout parentView;
    private FloatingActionsMenu floatingActionsMenu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        parentView = rootView.findViewById(R.id.parent_view);

        floatingActionsMenu = rootView.findViewById(R.id.floating_actions_menu);

        FloatingActionButton fabCourses = rootView.findViewById(R.id.fab_courses);
        fabCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
                addCourse(v);
            }
        });

        FloatingActionButton fabReminders = rootView.findViewById(R.id.fab_reminder);
        fabReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
                addReminder(v);
            }
        });

        FloatingActionButton fabAttendance = rootView.findViewById(R.id.fab_attendance);
        fabAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
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
        //get today's date and show timetable for 14 days from now
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
        Calendar calendar = Calendar.getInstance();
        String today = df.format(calendar.getTime());
        homeDataArrayList = db.getSchedule(today.substring(0, 3));

    }

    public void buildRecyclerView() {
        recyclerView = parentView.findViewById(R.id.view_timetable);
        layoutManager = new LinearLayoutManager(this.getContext());
        homeAdapter = new HomeAdapter(homeDataArrayList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setNestedScrollingEnabled(false);
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
