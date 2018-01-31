package com.example.diewevg.bm12applicatie.Models;

/**
 * Created by Diewevg on 25-1-2018.
 */

public class Feedback {
    Integer rating;
    String note;

    public Feedback() {}

    public Feedback(Integer rating, String note)
    {
        this.rating = rating;
        this.note = note;
    }

    public Integer getRating(){
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
