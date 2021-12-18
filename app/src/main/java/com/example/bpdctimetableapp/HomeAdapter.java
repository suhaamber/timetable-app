package com.example.bpdctimetableapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private ArrayList<HomeData> homeData;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private RecyclerView.RecycledViewPool viewPool2 = new RecyclerView.RecycledViewPool();
    //no listener needed for parent recycler view

    public static class HomeViewHolder extends RecyclerView.ViewHolder {

        private TextView sectionDateTV;
        private TextView noClassTodayTV;
        private RecyclerView sectionRecyclerView;
        private RecyclerView evalRecyclerView;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionDateTV = itemView.findViewById(R.id.section_date);
            noClassTodayTV = itemView.findViewById(R.id.no_class_today);
            sectionRecyclerView = itemView.findViewById(R.id.section_recycler_view);
            evalRecyclerView = itemView.findViewById(R.id.eval_recycler_view);
        }
    }

    public HomeAdapter(ArrayList<HomeData> homeData) {
        this.homeData = homeData;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_section, parent, false);
        HomeViewHolder homeViewHolder = new HomeViewHolder(view);
        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        HomeData currentItem = homeData.get(position);
        holder.sectionDateTV.setText(currentItem.getSectionDate());
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.sectionRecyclerView.getContext(),
                LinearLayoutManager.VERTICAL,
                false);

        layoutManager.setInitialPrefetchItemCount(currentItem.getHomeClassCardsSize());

        HomeClassAdapter homeClassAdapter = new HomeClassAdapter(
                currentItem.getHomeClassCards());
        holder.sectionRecyclerView.setLayoutManager(layoutManager);
        holder.sectionRecyclerView.setAdapter(homeClassAdapter);
        holder.sectionRecyclerView.setRecycledViewPool(viewPool);

        //implement card listener here
        homeClassAdapter.setOnItemClickListener(new HomeClassAdapter.OnItemClickListener() {
            @Override
            public void onCardClick(int position, View view) {
                int courseId = currentItem.getHomeClassCards().get(position).getCourseId();
                Intent intent = new Intent(view.getContext(), CourseView.class);
                intent.putExtra("COURSE_ID", courseId);
                view.getContext().startActivity(intent);
            }
        });

        //if no classes, show text view with no class today text
        if(currentItem.getHomeClassCardsSize()==0) {
            holder.noClassTodayTV.setVisibility(TextView.VISIBLE);
        }

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(
                holder.evalRecyclerView.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager2.setInitialPrefetchItemCount(currentItem.getHomeEvalCardsSize());

        HomeEvalAdapter homeEvalAdapter = new HomeEvalAdapter(currentItem.getHomeEvalCards());
        holder.evalRecyclerView.setLayoutManager(layoutManager2);
        holder.evalRecyclerView.setAdapter(homeEvalAdapter);
        holder.evalRecyclerView.setRecycledViewPool(viewPool2);

        //evaluation listener
        homeEvalAdapter.setOnItemClickListener(new HomeEvalAdapter.OnItemClickListener() {
            @Override
            public void onCardClick(int position, View view) {
                int courseId = currentItem.getHomeEvalCards().get(position).getCourseId();
                Intent intent = new Intent(view.getContext(), CourseView.class);
                intent.putExtra("COURSE_ID", courseId);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeData.size();
    }

}
