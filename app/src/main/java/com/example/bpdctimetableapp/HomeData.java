package com.example.bpdctimetableapp;

import java.util.ArrayList;

public class HomeData {
    private int id;
    private String sectionDate;

    public String getSectionDate() {
        return sectionDate;
    }

    public void setSectionDate(String sectionDate) {
        this.sectionDate = sectionDate;
    }

    public ArrayList<HomeCard> getHomeCards() {
        return homeCards;
    }

    public void setHomeCards(ArrayList<HomeCard> homeCards) {
        this.homeCards = homeCards;
    }

    public int getHomeCardsSize() {
        return homeCards.size();
    }

    private ArrayList<HomeCard> homeCards;

    public class HomeCard {
        private String courseName;
        private String instructorName;
        private String classTiming;

        public HomeCard(String courseName, String instructorName, String classTiming) {
            this.courseName = courseName;
            this.instructorName = instructorName;
            this.classTiming = classTiming;
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
}
