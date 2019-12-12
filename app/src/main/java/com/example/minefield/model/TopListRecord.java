package com.example.minefield.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class TopListRecord {
    Date timeOfAchievment;
    int points;

    public Date getTimeOfAchievment() {
        return timeOfAchievment;
    }

    public int getPoints() {
        return points;
    }

    public TopListRecord(Date timeOfAchievment, int points) {
        this.timeOfAchievment = timeOfAchievment;
        this.points = points;
    }

    public static ArrayList<TopListRecord> createTopList(int numberOfEntries){
        Random rand = new Random();
        ArrayList<TopListRecord> toplist = new ArrayList<TopListRecord>();
        for(int i = 0; i < numberOfEntries; ++i){
            toplist.add(new TopListRecord(Calendar.getInstance().getTime(), rand.nextInt(30000)));
        }
        return toplist;
    }
}
