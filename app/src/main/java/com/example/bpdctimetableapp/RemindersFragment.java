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

public class RemindersFragment extends Fragment {

    private RecyclerView recyclerViewReminder;
    private ReminderViewAdapter reminderViewAdapter;
    private RecyclerView.LayoutManager layoutManagerReminder;
    private ArrayList<ReminderViewCard> reminderViewCards;
    private ConstraintLayout parentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_reminders, container, false);
        parentView = rootView.findViewById(R.id.parent_view);

        //create course cards
        //access database and create cards
        createReminderCards();

        //attach adapter and layout manager
        buildReminderRecyclerView();
        return rootView;
    }

    private void createReminderCards() {
        DatabaseHelper databaseHelper = new DatabaseHelper(RemindersFragment.this.getContext());
        reminderViewCards = databaseHelper.getReminders();
    }

    private void buildReminderRecyclerView() {
        recyclerViewReminder = parentView.findViewById(R.id.view_reminders_recycler_view);
        layoutManagerReminder = new LinearLayoutManager(this.getContext());
        reminderViewAdapter = new ReminderViewAdapter(reminderViewCards);

        recyclerViewReminder.setLayoutManager(layoutManagerReminder);
        recyclerViewReminder.setAdapter(reminderViewAdapter);
        recyclerViewReminder.setNestedScrollingEnabled(false);

        reminderViewAdapter.setOnItemClickListener(new ReminderViewAdapter.OnItemClickListener() {
            @Override
            public void onReminderClick(int position, View view) {
                //create a new activity to show reminder details
                //create intent and pass reminder ID
            }
        });
    }
}
