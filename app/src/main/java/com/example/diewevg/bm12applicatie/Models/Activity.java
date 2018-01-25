package com.example.diewevg.bm12applicatie.Models;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Diewevg on 25-1-2018.
 */

public class Activity {
    String activityType;
    Date date;
    Time startTime;
    Time endTime;


    public Activity() {}

    public Activity(String activityType, Date date, Time startTime, Time endTime)
    {
        this.activityType = activityType;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getActivityType(){
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime(){
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime(){
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
