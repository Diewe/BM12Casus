package com.example.diewevg.bm12applicatie.Models;

/**
 * Created by Diewevg on 25-1-2018.
 */

public class Teacher {
    String voornaam;
    String achternaam;
    String email;

    public Teacher() {}

    public Teacher(String voornaam, String achternaam, String email)
    {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
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

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
