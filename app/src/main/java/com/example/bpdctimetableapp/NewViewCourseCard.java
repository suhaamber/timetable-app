package com.example.bpdctimetableapp;

public class NewViewCourseCard {

    private String courseName;
    private String instructorName;
    //TODO: find a better way to represent class hours in the course view
    private String classHours;

    public NewViewCourseCard(String courseName, String instructorName, String classHours) {
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.classHours = classHours;
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

    public String getClassHours() {
        return classHours;
    }

    public void setClassHours(String classHours) {
        this.classHours = classHours;
    }
}
