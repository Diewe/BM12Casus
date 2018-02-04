package com.example.diewevg.bm12applicatie.Models;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Diewevg on 25-1-2018.
 */

public class Activity {
    Integer id;
    String activityType;
    String date;
    String startTime;
    String endTime;


    public Activity() {}

    public Activity(Integer id, String activityType, String date, String startTime, String endTime)
    {
        this.id = id;
        this.activityType = activityType;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id;}

    public String getActivityType(){
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime(){
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime(){
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
