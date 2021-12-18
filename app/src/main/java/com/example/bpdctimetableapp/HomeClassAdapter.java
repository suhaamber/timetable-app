package com.example.bpdctimetableapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeClassAdapter extends RecyclerView.Adapter<HomeClassAdapter.HomeClassCardViewHolder> {

    private ArrayList<HomeClassCard> homeClassCards;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onCardClick(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class HomeClassCardViewHolder extends RecyclerView.ViewHolder {

        public TextView courseNameTV, instructorNameTV, classTimingTV;

        public HomeClassCardViewHolder(@NonNull View itemView, OnItemClickListener listener, ArrayList<HomeClassCard> homeClassCards) {
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

    public HomeClassAdapter(ArrayList<HomeClassCard> homeClassCards) {
        this.homeClassCards = homeClassCards;
    }

    @NonNull
    @Override
    public HomeClassCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_class_card, parent, false);
        HomeClassCardViewHolder homeClassCardViewHolder = new HomeClassCardViewHolder(v, mListener, homeClassCards);
        return homeClassCardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeClassCardViewHolder holder, int position) {
        HomeClassCard currentItem = homeClassCards.get(position);

        //set text here
        holder.courseNameTV.setText(currentItem.getCourseName());
        holder.instructorNameTV.setText(currentItem.getInstructorName());
        holder.classTimingTV.setText(currentItem.getClassTiming());

    }

    @Override
    public int getItemCount() {
        return homeClassCards.size();
    }

}
