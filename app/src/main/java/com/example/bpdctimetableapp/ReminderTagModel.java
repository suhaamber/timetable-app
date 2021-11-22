package com.example.bpdctimetableapp;

public class ReminderTagModel {

    private int reminderId;
    private String reminderTag;

    public ReminderTagModel(int reminderId, String reminderTag) {
        this.reminderId = reminderId;
        this.reminderTag = reminderTag;
    }

    public ReminderTagModel() {
    }

    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public String getReminderTag() {
        return reminderTag;
    }

    public void setReminderTag(String reminderTag) {
        this.reminderTag = reminderTag;
    }
}
