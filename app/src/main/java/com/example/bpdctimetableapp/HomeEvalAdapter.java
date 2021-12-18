package com.example.bpdctimetableapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeEvalAdapter extends RecyclerView.Adapter<HomeEvalAdapter.HomeEvalCardViewHolder>{
    private ArrayList<HomeEvalCard> homeEvalCards;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onCardClick(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class HomeEvalCardViewHolder extends RecyclerView.ViewHolder {

        private TextView courseNameTV;
        private TextView evalTypeTV;

        public HomeEvalCardViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            courseNameTV = itemView.findViewById(R.id.course_name);
            evalTypeTV = itemView.findViewById(R.id.eval_type);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null) {
                        int position = getBindingAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION)
                            listener.onCardClick(position, v);
                    }
                }
            });
        }
    }

    public HomeEvalAdapter(ArrayList<HomeEvalCard> homeEvalCards) {
        this.homeEvalCards = homeEvalCards;
    }

    @NonNull
    @Override
    public HomeEvalCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_eval_card, parent, false);
        HomeEvalCardViewHolder homeEvalCardViewHolder = new HomeEvalCardViewHolder(v, mListener);
        return homeEvalCardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeEvalCardViewHolder holder, int position) {
        HomeEvalCard currentItem = homeEvalCards.get(position);

        holder.courseNameTV.setText(currentItem.getCourseName());
        holder.evalTypeTV.setText(currentItem.getEvalType());
    }

    @Override
    public int getItemCount() {
        return homeEvalCards.size();
    }

}
