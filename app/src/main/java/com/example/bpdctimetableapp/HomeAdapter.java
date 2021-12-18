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
    //no listener needed for parent recycler view

    public static class HomeViewHolder extends RecyclerView.ViewHolder {

        private TextView sectionDateTV;
        private TextView noClassTodayTV;
        private RecyclerView sectionRecyclerView;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionDateTV = itemView.findViewById(R.id.section_date);
            noClassTodayTV = itemView.findViewById(R.id.no_class_today);
            sectionRecyclerView = itemView.findViewById(R.id.section_recycler_view);
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

        layoutManager.setInitialPrefetchItemCount(currentItem.getHomeCardsSize());

        HomeSectionAdapter homeSectionAdapter = new HomeSectionAdapter(
                currentItem.getHomeCards());
        holder.sectionRecyclerView.setLayoutManager(layoutManager);
        holder.sectionRecyclerView.setAdapter(homeSectionAdapter);
        holder.sectionRecyclerView.setRecycledViewPool(viewPool);

        //implement card listener here
        homeSectionAdapter.setOnItemClickListener(new HomeSectionAdapter.OnItemClickListener() {
            @Override
            public void onCardClick(int position, View view) {
                int courseId = currentItem.getHomeCards().get(position).getCourseId();
                Intent intent = new Intent(view.getContext(), CourseView.class);
                intent.putExtra("COURSE_ID", courseId);
                view.getContext().startActivity(intent);
            }
        });

        //if no classes, show text view with no class today text
        if(currentItem.getHomeCardsSize()==0) {
            holder.noClassTodayTV.setVisibility(TextView.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return homeData.size();
    }

}
