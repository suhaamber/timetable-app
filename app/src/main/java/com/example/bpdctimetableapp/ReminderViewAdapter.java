package com.example.bpdctimetableapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReminderViewAdapter extends RecyclerView.Adapter<ReminderViewAdapter.ReminderViewHolder> {

    private ArrayList<ReminderViewCard> reminderViewCards;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onReminderClick(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ReminderViewHolder extends RecyclerView.ViewHolder {
        public TextView reminderTitleTV;
        public TextView tagsTV;
        public TextView dateTimeTV;


        public ReminderViewHolder(@NonNull View itemView, final OnItemClickListener listener, ArrayList<ReminderViewCard> reminderViewCards) {
            super(itemView);
            reminderTitleTV = itemView.findViewById(R.id.reminder_title);
            tagsTV = itemView.findViewById(R.id.tags);
            dateTimeTV = itemView.findViewById(R.id.date_time);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(listener!=null) {
                        int position = getBindingAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                            listener.onReminderClick(position, v);
                    }
                }
            });

        }
    }

    public ReminderViewAdapter(ArrayList<ReminderViewCard> reminderViewCards) {
        this.reminderViewCards = reminderViewCards;
    }

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_card, parent, false);
        ReminderViewHolder reminderViewHolder = new ReminderViewHolder(v, mListener, reminderViewCards);
        return reminderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
        ReminderViewCard currentItem = reminderViewCards.get(position);

        holder.reminderTitleTV.setText(currentItem.getReminderTitle());
        holder.tagsTV.setText(currentItem.getReminderTags());
        holder.dateTimeTV.setText(currentItem.getReminderDateTime());

    }

    @Override
    public int getItemCount() {
        return reminderViewCards.size();
    }



}
