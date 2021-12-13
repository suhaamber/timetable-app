package com.example.bpdctimetableapp;

public class TimetableModel {

    private int courseId;
    private String courseName;
    private String classType;
    private int classHour;
    private int classDay;

    public TimetableModel(int courseId, String courseName, String classType, int classHour, int classDay) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.classType = classType;
        this.classHour = classHour;
        this.classDay = classDay;
    }

    public TimetableModel(int courseId, String classType, int classHour, int classDay) {
        this.courseId = courseId;
        this.classType = classType;
        this.classHour = classHour;
        this.classDay = classDay;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public TimetableModel() {
        //default constructor
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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
