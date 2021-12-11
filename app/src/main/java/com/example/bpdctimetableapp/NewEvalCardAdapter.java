package com.example.bpdctimetableapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewEvalCardAdapter extends RecyclerView.Adapter<NewEvalCardAdapter.NewEvalViewHolder> {

    private ArrayList<NewEvalCard> newEvalCards;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
        void onSetDateClick(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class NewEvalViewHolder extends RecyclerView.ViewHolder {
        public ImageButton deleteEvalCard;
        public Button selectEvalDateButton;
        public TextView dateTextView;
        public Spinner spinner;

        public NewEvalViewHolder(@NonNull View itemView, final OnItemClickListener listener, ArrayList<NewEvalCard> newEvalCards) {
            super(itemView);
            deleteEvalCard = itemView.findViewById(R.id.button_delete_eval_card);
            selectEvalDateButton = itemView.findViewById(R.id.select_eval_date_button);
            dateTextView = itemView.findViewById(R.id.eval_date_textview);

            spinner = itemView.findViewById(R.id.eval_type_spinner);
            ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(itemView.getContext(), R.array.eval_type_values, android.R.layout.simple_spinner_item);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerAdapter);

            deleteEvalCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getBindingAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });

            selectEvalDateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getBindingAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onSetDateClick(position, v);
                        }
                    }
                }
            });

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    newEvalCards.get(getBindingAdapterPosition()).setEvalType(spinner.getItemAtPosition(position).toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    newEvalCards.get(getBindingAdapterPosition()).setEvalType("Mid-semester Test");
                }
            });
        }
    }

    @NonNull
    @Override
    public NewEvalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_eval_card, parent, false);
        NewEvalViewHolder newEvalViewHolder = new NewEvalViewHolder(v, mListener, newEvalCards);
        return newEvalViewHolder;

    }

    public NewEvalCardAdapter(ArrayList<NewEvalCard> newEvalCards1) {
        newEvalCards = newEvalCards1;
    }

    @Override
    public void onBindViewHolder(@NonNull NewEvalViewHolder holder, int position) {
        NewEvalCard currentItem = newEvalCards.get(position);

        if(newEvalCards.get(position).getEvalType() == null) {
            holder.spinner.setSelection(0);
        }
        else {
            holder.spinner.setSelection(((ArrayAdapter)holder.spinner.getAdapter()).getPosition(newEvalCards.get(position).getEvalType()));
        }

        holder.selectEvalDateButton.setText(currentItem.getButtonText());
        holder.dateTextView.setText(currentItem.getEvalDate());

    }

    @Override
    public int getItemCount() {
        return newEvalCards.size();
    }


}
