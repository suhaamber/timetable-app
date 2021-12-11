package com.example.bpdctimetableapp;

public class ReminderViewCard {
    private String reminderTitle;
    private String reminderTags;
    private String reminderDateTime;

    public ReminderViewCard(String reminderTitle, String reminderTags, String reminderDateTime) {
        this.reminderTitle = reminderTitle;
        this.reminderTags = reminderTags;
        this.reminderDateTime = reminderDateTime;
    }

    public String getReminderTitle() {
        return reminderTitle;
    }

    public void setReminderTitle(String reminderTitle) {
        this.reminderTitle = reminderTitle;
    }

    public String getReminderTags() {
        return reminderTags;
    }

    public void setReminderTags(String reminderTags) {
        this.reminderTags = reminderTags;
    }

    public String getReminderDateTime() {
        return reminderDateTime;
    }

    public void setReminderDateTime(String reminderDateTime) {
        this.reminderDateTime = reminderDateTime;
    }
}
