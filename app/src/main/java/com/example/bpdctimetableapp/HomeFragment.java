package com.example.bpdctimetableapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.getbase.floatingactionbutton.FloatingActionButton;


public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

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

        return rootView;

    }

    public void addCourse(View view) {
        Intent addCourseIntent = new Intent(getActivity(), AddCourse.class);
        startActivity(addCourseIntent);
    }

    public void addReminder(View view) {
        Intent addReminderIntent = new Intent(getActivity(), AddReminder.class);
        startActivity(addReminderIntent);
    }

}
