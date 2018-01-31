package com.example.diewevg.bm12applicatie.Models;

/**
 * Created by Diewevg on 21-1-2018.
 */

public class Student {
    String voornaam;
    String achternaam;
    String studentnummer;
    String email;

    public Student() {}

    public Student(String voornaam, String achternaam, String studentnummer, String email)
    {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.studentnummer = studentnummer;
        this.email = email;
    }

    public String getVoornaam(){
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam(){
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getStudentnummer(){
        return studentnummer;
    }

    public void setStudentnummer(String studentnummer) {
        this.studentnummer = studentnummer;
    }
}
