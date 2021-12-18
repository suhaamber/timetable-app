package com.example.bpdctimetableapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class TimetableFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_timetable, container, false);

        //open database, get courses and display
        DatabaseHelper db = new DatabaseHelper(this.getContext());
        ArrayList<TimetableModel> timetableList = db.getTimetable();
        for(TimetableModel timetable: timetableList) {
            TextView temp = rootView.findViewById(ids.get(timetable.getClassDay()).get(timetable.getClassHour()-1));
            String className = timetable.getCourseName() + "\n" + timetable.getClassType();
            temp.setText(className);

            Context fragmentContext = this.getContext();
            //creates a listener for each box with a lecture
            //leads to a new activity with cou
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createCourseActivity(timetable.getCourseId());
                }
            });
        }

        return rootView;
    }

    private void createCourseActivity(int courseId) {
        Intent intent = new Intent(getActivity(), CourseView.class);
        intent.putExtra("COURSE_ID", courseId);
        getActivity().startActivity(intent);
    }

    public static ArrayList<ArrayList<Integer>> ids;

    public TimetableFragment() {
        ids = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(R.id.tile_11);
        temp.add(R.id.tile_21);
        temp.add(R.id.tile_31);
        temp.add(R.id.tile_41);
        temp.add(R.id.tile_51);
        temp.add(R.id.tile_61);
        temp.add(R.id.tile_71);
        temp.add(R.id.tile_81);
        temp.add(R.id.tile_91);
        ids.add(temp);

        temp = new ArrayList<>();
        temp.add(R.id.tile_12);
        temp.add(R.id.tile_22);
        temp.add(R.id.tile_32);
        temp.add(R.id.tile_42);
        temp.add(R.id.tile_52);
        temp.add(R.id.tile_62);
        temp.add(R.id.tile_72);
        temp.add(R.id.tile_82);
        temp.add(R.id.tile_92);

        ids.add(temp);

        temp = new ArrayList<>();
        temp.add(R.id.tile_13);
        temp.add(R.id.tile_23);
        temp.add(R.id.tile_33);
        temp.add(R.id.tile_43);
        temp.add(R.id.tile_53);
        temp.add(R.id.tile_63);
        temp.add(R.id.tile_73);
        temp.add(R.id.tile_83);
        temp.add(R.id.tile_93);

        ids.add(temp);

        temp = new ArrayList<>();
        temp.add(R.id.tile_14);
        temp.add(R.id.tile_24);
        temp.add(R.id.tile_34);
        temp.add(R.id.tile_44);
        temp.add(R.id.tile_54);
        temp.add(R.id.tile_64);
        temp.add(R.id.tile_74);
        temp.add(R.id.tile_84);
        temp.add(R.id.tile_94);

        ids.add(temp);

        temp = new ArrayList<>();
        temp.add(R.id.tile_15);
        temp.add(R.id.tile_25);
        temp.add(R.id.tile_35);
        temp.add(R.id.tile_45);
        temp.add(R.id.tile_55);
        temp.add(R.id.tile_65);
        temp.add(R.id.tile_75);
        temp.add(R.id.tile_85);
        temp.add(R.id.tile_95);

        ids.add(temp);
    }
    //to get a class hour, use ClassHourTextViewIds.ids.get(day).get(hour)


}
