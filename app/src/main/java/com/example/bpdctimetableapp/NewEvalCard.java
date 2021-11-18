package com.example.bpdctimetableapp;

public class NewEvalCard {

    public String evalType;
    public String evalDate;
    public String buttonText;

    public NewEvalCard(String evalDate, String buttonText) {
        this.evalDate = evalDate;
        this.buttonText = buttonText;

    }

    public void dateLabelChange(String date) {
        evalDate = date;
    }

    public void buttonTextChange(String text) {
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

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }
}
