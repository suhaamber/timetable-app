package com.example.bpdctimetableapp;

public class CourseModel {

    private int courseId;
    private String courseName;
    private String instructorName;

    public CourseModel(int courseId, String courseName, String instructorName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructorName = instructorName;
    }

    public CourseModel() {
        //default constructor
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
}
