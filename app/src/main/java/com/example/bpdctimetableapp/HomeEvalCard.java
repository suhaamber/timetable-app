package com.example.bpdctimetableapp;

public class HomeEvalCard {
    private int courseId;
    private String courseName;
    private String evalType;

    public HomeEvalCard(int courseId, String courseName, String evalType) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.evalType = evalType;
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

    public String getEvalType() {
        return evalType;
    }

    public void setEvalType(String evalType) {
        this.evalType = evalType;
    }
}
