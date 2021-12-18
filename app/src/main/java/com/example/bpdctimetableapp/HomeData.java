package com.example.bpdctimetableapp;

import java.util.ArrayList;

public class HomeData {
    private int id;
    private String sectionDate;
    private ArrayList<HomeClassCard> homeClassCards;
    private ArrayList<HomeEvalCard> homeEvalCards;

    public HomeData() {
        homeClassCards = new ArrayList<>();
        homeEvalCards = new ArrayList<>();
    }

    public String getSectionDate() {
        return sectionDate;
    }

    public void setSectionDate(String sectionDate) {
        this.sectionDate = sectionDate;
    }

    public ArrayList<HomeClassCard> getHomeClassCards() {
        return homeClassCards;
    }

    public void addHomeClassCards(HomeClassCard homeClassCard) {
        homeClassCards.add(homeClassCard);
    }

    public void addHomeEvalCards(HomeEvalCard homeEvalCard) {
        homeEvalCards.add(homeEvalCard);
    }

    public ArrayList<HomeEvalCard> getHomeEvalCards() {
        return homeEvalCards;
    }

    public int getHomeClassCardsSize() {
        return homeClassCards.size();
    }

    public int getHomeEvalCardsSize() {
        return homeEvalCards.size();
    }
}
