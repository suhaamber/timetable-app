package com.example.bpdctimetableapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewEvalCardAdapter extends RecyclerView.Adapter<NewEvalCardAdapter.NewEvalViewHolder> {

    private ArrayList<NewEvalCard> newEvalCards;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class NewEvalViewHolder extends RecyclerView.ViewHolder {
        public ImageButton deleteEvalCard;

        public NewEvalViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            deleteEvalCard = itemView.findViewById(R.id.button_delete_eval_card);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getBindingAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

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
        }
    }

    @NonNull
    @Override
    public NewEvalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_eval_card, parent, false);
        NewEvalViewHolder newEvalViewHolder = new NewEvalViewHolder(v, mListener);
        return newEvalViewHolder;

    }

    public NewEvalCardAdapter(ArrayList<NewEvalCard> newEvalCards1) {
        newEvalCards = newEvalCards1;
    }

    @Override
    public void onBindViewHolder(@NonNull NewEvalViewHolder holder, int position) {
        NewEvalCard currentItem = newEvalCards.get(position);
    }

    @Override
    public int getItemCount() {
        return newEvalCards.size();
    }


}
