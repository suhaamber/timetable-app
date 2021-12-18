package com.example.bpdctimetableapp;

public class HomeCard {
    private int courseId;
    private String courseName;
    private String instructorName;
    private String classTiming;

    public HomeCard(int courseId, String courseName, String instructorName, String classTiming) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.classTiming = classTiming;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getClassTiming() {
        return classTiming;
    }

    public void setClassTiming(String classTiming) {
        this.classTiming = classTiming;
    }
}
