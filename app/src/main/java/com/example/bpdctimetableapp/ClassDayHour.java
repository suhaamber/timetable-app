package com.example.bpdctimetableapp;

public class ClassDayHour {
    private String classType;
    private int classHour;
    private int classDay;

    public ClassDayHour(int classHour, int classDay) {
        this.classHour = classHour;
        this.classDay = classDay;
    }

    public ClassDayHour(String classType, int classHour, int classDay) {
        this.classHour = classHour;
        this.classDay = classDay;
        this.classType = classType;
    }

    public ClassDayHour() {

    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
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
