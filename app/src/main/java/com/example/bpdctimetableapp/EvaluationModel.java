package com.example.bpdctimetableapp;

public class EvaluationModel {

    private int courseId;
    private String evaluationType;
    private String evaluationDate;

    public EvaluationModel(int courseId, String evaluationType, String evaluationDate) {
        this.courseId = courseId;
        this.evaluationType = evaluationType;
        this.evaluationDate = evaluationDate;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(String evaluationType) {
        this.evaluationType = evaluationType;
    }

    public String getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(String evaluationDate) {
        this.evaluationDate = evaluationDate;
    }
}
