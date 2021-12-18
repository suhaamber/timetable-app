package com.example.bpdctimetableapp;

import java.util.ArrayList;

public class HomeData {
    private int id;
    private String sectionDate;
    private ArrayList<HomeCard> homeCards;

    public HomeData() {
        homeCards = new ArrayList<>();
    }

    public String getSectionDate() {
        return sectionDate;
    }

    public void setSectionDate(String sectionDate) {
        this.sectionDate = sectionDate;
    }

    public ArrayList<HomeCard> getHomeCards() {
        return homeCards;
    }

    public void addHomeCards(HomeCard homeCard) {
        homeCards.add(homeCard);
    }

    public int getHomeCardsSize() {
        return homeCards.size();
    }
}
