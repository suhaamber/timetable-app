package com.example.bpdctimetableapp;

import java.util.ArrayList;

public class NewCourseCard {

    private String classType;
    private ArrayList<ClassDayHour> classDayHours;
    private String classHourLabel;
    private String buttonText;

    public NewCourseCard(String buttonText) {
        classDayHours = new ArrayList<>();
        this.buttonText = buttonText;
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

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getClassHourLabel() {
        return classHourLabel;
    }

    public void setClassHourLabel(String string) {
        classHourLabel = string;
    }
}
