package com.example.bpdctimetableapp;

import java.util.ArrayList;

public class ClassHoursIds {

    //to get a class hour use ids.get(day).get(hour)
    public ArrayList<ArrayList<Integer>> ids;

    public ClassHoursIds() {
        ids = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(R.id.popup_11);
        temp.add(R.id.popup_21);
        temp.add(R.id.popup_31);
        temp.add(R.id.popup_41);
        temp.add(R.id.popup_51);
        temp.add(R.id.popup_61);
        temp.add(R.id.popup_71);
        temp.add(R.id.popup_81);
        temp.add(R.id.popup_91);
        ids.add(temp);

        temp = new ArrayList<>();
        temp.add(R.id.popup_12);
        temp.add(R.id.popup_22);
        temp.add(R.id.popup_32);
        temp.add(R.id.popup_42);
        temp.add(R.id.popup_52);
        temp.add(R.id.popup_62);
        temp.add(R.id.popup_72);
        temp.add(R.id.popup_82);
        temp.add(R.id.popup_92);

        ids.add(temp);

        temp = new ArrayList<>();
        temp.add(R.id.popup_13);
        temp.add(R.id.popup_23);
        temp.add(R.id.popup_33);
        temp.add(R.id.popup_43);
        temp.add(R.id.popup_53);
        temp.add(R.id.popup_63);
        temp.add(R.id.popup_73);
        temp.add(R.id.popup_83);
        temp.add(R.id.popup_93);

        ids.add(temp);

        temp = new ArrayList<>();
        temp.add(R.id.popup_14);
        temp.add(R.id.popup_24);
        temp.add(R.id.popup_34);
        temp.add(R.id.popup_44);
        temp.add(R.id.popup_54);
        temp.add(R.id.popup_64);
        temp.add(R.id.popup_74);
        temp.add(R.id.popup_84);
        temp.add(R.id.popup_94);

        ids.add(temp);

        temp = new ArrayList<>();
        temp.add(R.id.popup_15);
        temp.add(R.id.popup_25);
        temp.add(R.id.popup_35);
        temp.add(R.id.popup_45);
        temp.add(R.id.popup_55);
        temp.add(R.id.popup_65);
        temp.add(R.id.popup_75);
        temp.add(R.id.popup_85);
        temp.add(R.id.popup_95);

        ids.add(temp);

    }
}
