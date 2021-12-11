package com.example.bpdctimetableapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewViewCourseAdapter extends RecyclerView.Adapter<NewViewCourseAdapter.NewCourseViewHolder> {

    private ArrayList<NewViewCourseCard> newViewCourseCards;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onCourseClick(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class NewCourseViewHolder extends RecyclerView.ViewHolder {
        public TextView courseNameTV;
        public TextView instructorNameTV;
        public TextView classHoursTV;
        public TextView attendanceTV;

        public NewCourseViewHolder(@NonNull View itemView, final OnItemClickListener listener, ArrayList<NewViewCourseCard> newViewCourseCards) {
            super(itemView);
            courseNameTV = itemView.findViewById(R.id.course_name);
            instructorNameTV = itemView.findViewById(R.id.instructor_name);
            classHoursTV = itemView.findViewById(R.id.class_hours);
            attendanceTV = itemView.findViewById(R.id.attendance);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null) {
                        int position = getBindingAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                            listener.onCourseClick(position, v);
                    }
                }
            });

        }
    }

    public NewViewCourseAdapter(ArrayList<NewViewCourseCard> newViewCourseCards) {
        this.newViewCourseCards = newViewCourseCards;
    }


    @NonNull
    @Override
    public NewViewCourseAdapter.NewCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_card, parent, false);
        NewCourseViewHolder newCourseViewHolder = new NewCourseViewHolder(v, mListener, newViewCourseCards);
        return newCourseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewViewCourseAdapter.NewCourseViewHolder holder, int position) {
        NewViewCourseCard currentItem = newViewCourseCards.get(position);

        holder.courseNameTV.setText(currentItem.getCourseName());
        holder.instructorNameTV.setText(currentItem.getInstructorName());
        holder.classHoursTV.setText(currentItem.getClassHours());
    }

    @Override
    public int getItemCount() {
        return newViewCourseCards.size();
    }
}
