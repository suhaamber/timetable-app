package com.example.bpdctimetableapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewCourseCardAdapter extends RecyclerView.Adapter<NewCourseCardAdapter.NewCourseViewHolder> {

    private ArrayList<NewCourseCard> newCourseCards;

    public static class NewCourseViewHolder extends RecyclerView.ViewHolder{

        public NewCourseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public NewCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_course_card, parent, false);
        NewCourseViewHolder newCourseViewHolder = new NewCourseViewHolder(v);
        return newCourseViewHolder;
    }

    public NewCourseCardAdapter(ArrayList<NewCourseCard> newCourseCards1) {
        newCourseCards = newCourseCards1;
    }

    @Override
    public void onBindViewHolder(@NonNull NewCourseViewHolder holder, int position) {
        NewCourseCard currentItem = newCourseCards.get(position);

    }

    @Override
    public int getItemCount() {
        return newCourseCards.size();
    }

}
