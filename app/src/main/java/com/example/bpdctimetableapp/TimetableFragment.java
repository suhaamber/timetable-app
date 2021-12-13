package com.example.bpdctimetableapp;

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
        ClassHourTextViewIds tempIds = new ClassHourTextViewIds();
        for(TimetableModel timetable: timetableList) {
            TextView temp = rootView.findViewById(tempIds.ids.get(timetable.getClassDay()).get(timetable.getClassHour()));
            String className = timetable.getCourseName() + "\n" + timetable.getClassType();
            temp.setText(className);

            //try creating on click listener here
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        //create a listener for each textbox to lead to activity with course information

        return rootView;
    }

    public class ClassHourTextViewIds {
        public ArrayList<ArrayList<Integer>> ids;

        public ClassHourTextViewIds() {
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
    }
    //to get a class hour, use ClassHourTextViewIds.ids.get(day).get(hour)


}
