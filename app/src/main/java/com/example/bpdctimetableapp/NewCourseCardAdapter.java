package com.example.bpdctimetableapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewCourseCardAdapter extends RecyclerView.Adapter<NewCourseCardAdapter.NewCourseViewHolder> {

    private ArrayList<NewCourseCard> newCourseCards;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onSelectClassClick(int position, View view);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public static class NewCourseViewHolder extends RecyclerView.ViewHolder{

        public ImageButton deleteCourseCard;
        public Button selectClassHours;
        public TextView classHourTextView;
        public Spinner spinner;

        public NewCourseViewHolder(@NonNull View itemView, final OnItemClickListener listener, ArrayList<NewCourseCard> newCourseCards) {
            super(itemView);
            deleteCourseCard = itemView.findViewById(R.id.button_delete_course_card);
            selectClassHours = itemView.findViewById(R.id.select_class_hours_button);
            spinner = itemView.findViewById(R.id.class_type_spinner);
            classHourTextView = itemView.findViewById((R.id.class_hours_tv));

            selectClassHours.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getBindingAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onSelectClassClick(position, v);
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

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    newCourseCards.get(getBindingAdapterPosition()).setClassType(spinner.getItemAtPosition(position).toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    newCourseCards.get(getBindingAdapterPosition()).setClassType("Lecture");
                }
            });
        }
    }

    @NonNull
    @Override
    public NewCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_course_card, parent, false);
        NewCourseViewHolder newCourseViewHolder = new NewCourseViewHolder(v, mListener, newCourseCards);
        return newCourseViewHolder;
    }

    public NewCourseCardAdapter(ArrayList<NewCourseCard> newCourseCards1) {
        newCourseCards = newCourseCards1;
    }

    @Override
    public void onBindViewHolder(@NonNull NewCourseViewHolder holder, int position) {
        NewCourseCard currentItem = newCourseCards.get(position);

        holder.classHourTextView.setText(currentItem.getClassHourLabel());
        holder.selectClassHours.setText(currentItem.getButtonText());

    }

    @Override
    public int getItemCount() {
        return newCourseCards.size();
    }

}
