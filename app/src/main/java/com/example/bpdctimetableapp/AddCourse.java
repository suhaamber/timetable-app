package com.example.bpdctimetableapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddCourse extends Activity {

    private RecyclerView recyclerViewCourse, recyclerViewEval;
    private NewCourseCardAdapter adapterCourse;
    private NewEvalCardAdapter adapterEval;
    private RecyclerView.LayoutManager layoutManagerCourse, layoutManagerEval;
    private ArrayList<NewCourseCard> newCourseCards;
    private ArrayList<NewEvalCard> newEvalCards;
    private ImageButton addCourseCard, addEvalCard;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);

        createCourseCards();
        buildCourseRecyclerView();

        createEvalCards();
        buildEvalRecyclerView();

        addCourseCard = findViewById(R.id.button_add_course_card);
        addEvalCard = findViewById(R.id.button_add_eval_card);

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

    }

    public void createCourseCards() {
        newCourseCards = new ArrayList<>();
        //newCourseCards.add(new NewCourseCard());
    }

    public void createEvalCards() {
        newEvalCards = new ArrayList<>();
        newEvalCards.add(new NewEvalCard());
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
            public void onItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {
                removeEvalItem(position);
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
        newEvalCards.add((new NewEvalCard()));
        adapterEval.notifyDataSetChanged();
    }
}
