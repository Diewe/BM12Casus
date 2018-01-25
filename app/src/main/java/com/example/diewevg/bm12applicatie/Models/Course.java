package com.example.diewevg.bm12applicatie.Models;

/**
 * Created by Diewevg on 25-1-2018.
 */

public class Course {
    String name;
    String courseCode;

    public Course() {}

    public Course(String name, String courseCode)
    {
        this.name = name;
        this.courseCode = courseCode;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseCode(){
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
