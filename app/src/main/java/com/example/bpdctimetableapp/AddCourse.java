package com.example.bpdctimetableapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddCourse extends Activity {

    private RecyclerView recyclerViewCourse;
    private NewCourseCardAdapter adapterCourse;
    private RecyclerView.LayoutManager layoutManagerCourse;
    private ArrayList<NewCourseCard> newCourseCards;
    private ImageButton addCourseCard;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);

        createCourseCards();
        buildCourseRecyclerView();

        addCourseCard = findViewById(R.id.button_add_course_card);

        addCourseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewCourseCard(v);
            }
        });

    }

    public void createCourseCards() {
        newCourseCards = new ArrayList<>();
        //newCourseCards.add(new NewCourseCard());

    }

    public void buildCourseRecyclerView() {
        recyclerViewCourse = findViewById(R.id.recyclerViewClasses);
        layoutManagerCourse = new LinearLayoutManager(this);
        adapterCourse = new NewCourseCardAdapter(newCourseCards);

        recyclerViewCourse.setLayoutManager(layoutManagerCourse);
        recyclerViewCourse.setAdapter(adapterCourse);

        adapterCourse.setOnItemClickListener(new NewCourseCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    public void removeItem(int position) {
        newCourseCards.remove(position);
        adapterCourse.notifyItemRemoved(position);
    }

    public void addNewCourseCard(View view) {
        newCourseCards.add(new NewCourseCard());
        adapterCourse.notifyDataSetChanged();
    }
}
