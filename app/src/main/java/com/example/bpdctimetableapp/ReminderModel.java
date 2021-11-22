package com.example.bpdctimetableapp;

public class ReminderModel {

    private int reminderId;
    private String reminderTitle;
    private String reminderDateTime;

    public ReminderModel(int reminderId,String reminderTitle, String reminderDateTime) {
        this.reminderId = reminderId;
        this.reminderTitle = reminderTitle;
        this.reminderDateTime = reminderDateTime;
    }

    public String getReminderTitle() {
        return reminderTitle;
    }

    public void setReminderTitle(String reminderTitle) {
        this.reminderTitle = reminderTitle;
    }

    public ReminderModel() {
    }

    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public String getReminderDateTime() {
        return reminderDateTime;
    }

    public void setReminderDateTime(String reminderDateTime) {
        this.reminderDateTime = reminderDateTime;
    }
}
