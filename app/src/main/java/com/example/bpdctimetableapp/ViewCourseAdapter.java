package com.example.bpdctimetableapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewCourseAdapter extends RecyclerView.Adapter<ViewCourseAdapter.CourseViewHolder> {

    private ArrayList<ViewCourseCard> viewCourseCards;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onCourseClick(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        public TextView courseNameTV;
        public TextView instructorNameTV;
        public TextView classHoursTV;
        public TextView attendanceTV;

        public CourseViewHolder(@NonNull View itemView, final OnItemClickListener listener, ArrayList<ViewCourseCard> viewCourseCards) {
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

    public ViewCourseAdapter(ArrayList<ViewCourseCard> viewCourseCards) {
        this.viewCourseCards = viewCourseCards;
    }


    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_card, parent, false);
        CourseViewHolder courseViewHolder = new CourseViewHolder(v, mListener, viewCourseCards);
        return courseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        ViewCourseCard currentItem = viewCourseCards.get(position);

        holder.courseNameTV.setText(currentItem.getCourseName());
        holder.instructorNameTV.setText(currentItem.getInstructorName());
        holder.classHoursTV.setText(currentItem.getClassHours());
    }

    @Override
    public int getItemCount() {
        return viewCourseCards.size();
    }
}
