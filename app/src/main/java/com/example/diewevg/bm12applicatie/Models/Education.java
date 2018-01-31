package com.example.diewevg.bm12applicatie.Models;

/**
 * Created by Diewevg on 25-1-2018.
 */

public class Education {
    String name;

    public Education() {}

    public Education(String name)
    {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
