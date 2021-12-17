package com.example.bpdctimetableapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeSectionAdapter extends RecyclerView.Adapter<HomeSectionAdapter.HomeCardViewHolder> {

    private ArrayList<HomeData.HomeCard> homeCards;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onCardClick(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class HomeCardViewHolder extends RecyclerView.ViewHolder {

        public TextView courseNameTV, instructorNameTV, classTimingTV;

        public HomeCardViewHolder(@NonNull View itemView, OnItemClickListener listener, ArrayList<HomeData.HomeCard> homeCards) {
            super(itemView);
            //set ids to textviews in the card
            courseNameTV = itemView.findViewById(R.id.course_name);
            instructorNameTV = itemView.findViewById(R.id.instructor_name);
            classTimingTV = itemView.findViewById(R.id.class_timing);

            //implement listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null) {
                        int position = getBindingAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                            listener.onCardClick(position, v);
                    }
                }
            });
        }
    }

    public HomeSectionAdapter(ArrayList<HomeData.HomeCard> homeCards) {
        this.homeCards = homeCards;
    }

    @NonNull
    @Override
    public HomeCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card, parent, false);
        HomeCardViewHolder homeCardViewHolder = new HomeCardViewHolder(v, mListener, homeCards);
        return homeCardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCardViewHolder holder, int position) {
        HomeData.HomeCard currentItem = homeCards.get(position);

        //set text here
        holder.courseNameTV.setText(currentItem.getCourseName());
        holder.instructorNameTV.setText(currentItem.getInstructorName());
        holder.classTimingTV.setText(currentItem.getClassTiming());

    }

    @Override
    public int getItemCount() {
        return homeCards.size();
    }

}
