package com.example.bpdctimetableapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewCourseCardAdapter extends RecyclerView.Adapter<NewCourseCardAdapter.NewCourseViewHolder> {

    private ArrayList<NewCourseCard> newCourseCards;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public static class NewCourseViewHolder extends RecyclerView.ViewHolder{

        public ImageButton deleteCourseCard;

        public NewCourseViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            deleteCourseCard = itemView.findViewById(R.id.button_delete_course_card);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getBindingAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            deleteCourseCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener !=null) {
                        int position  = getBindingAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public NewCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_course_card, parent, false);
        NewCourseViewHolder newCourseViewHolder = new NewCourseViewHolder(v, mListener);
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
