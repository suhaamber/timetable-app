package com.example.bpdctimetableapp;

import java.util.ArrayList;

public class NewCourseCard {

    private String classType;
    private ArrayList<ClassDayHour> classDayHours;

    public NewCourseCard() {
        classDayHours = new ArrayList<>();
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public ArrayList<ClassDayHour> getClassDayHours() {
        return classDayHours;
    }

    public void addClassDayHours(ClassDayHour classDayHour) {
        this.classDayHours.add(classDayHour);
    }
}
