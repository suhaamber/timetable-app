package com.example.bpdctimetableapp;

public class ClassDayHour {
    private int classHour;
    private int classDay;

    public ClassDayHour(int classHour, int classDay) {
        this.classHour = classHour;
        this.classDay = classDay;
    }

    public ClassDayHour() {

    }

    public int getClassHour() {
        return classHour;
    }

    public void setClassHour(int classHour) {
        this.classHour = classHour;
    }

    public int getClassDay() {
        return classDay;
    }

    public void setClassDay(int classDay) {
        this.classDay = classDay;
    }
}
