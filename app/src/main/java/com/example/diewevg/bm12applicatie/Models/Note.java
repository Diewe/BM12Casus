package com.example.diewevg.bm12applicatie.Models;

import java.util.List;

/**
 * Created by Diewevg on 25-1-2018.
 */

public class Note {
    String text;
    Student student;
    Teacher teacher;

    public Note() {}

    public Note(String note, Student student)
    {
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Student getStudent() { return student; }

    public void setStudent(Student student) { this.student = student; }

    public Teacher getTeacher() { return teacher; }

    public void setTeacher(Teacher teacher) { this.teacher = teacher; }
}
