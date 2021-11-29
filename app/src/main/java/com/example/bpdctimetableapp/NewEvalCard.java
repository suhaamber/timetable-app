package com.example.bpdctimetableapp;

public class NewEvalCard {

    public String evalType;
    //date format: DD-MM-YYYY
    public String evalDate;
    public String buttonText;

    public NewEvalCard(String evalType, String evalDate, String buttonText) {
        this.evalType = evalType;
        this.evalDate = evalDate;
        this.buttonText = buttonText;
    }

    public String getEvalType() {
        return evalType;
    }

    public void setEvalType(String evalType) {
        this.evalType = evalType;
    }

    public void dateLabelChange(String date) {
        evalDate = date;
    }

    public void setButtonText(String text) {
        buttonText = text;
    }

    public String getEvalDate() {
        return evalDate;
    }

    public void setEvalDate(String evalDate) {
        this.evalDate = evalDate;
    }

    public String getButtonText() {
        return buttonText;
    }


}
