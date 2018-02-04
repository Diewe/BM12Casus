package com.example.diewevg.bm12applicatie.Leeractiviteit;

import com.example.diewevg.bm12applicatie.Resultaten.ResultaatModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Diewevg on 30-1-2018.
 */

public class LeeractiviteitModel {
    public String soort;
    public String cursus;

    public LeeractiviteitModel(String soort, String cursus) {
        this.soort = soort;
        this.cursus = cursus;
    }

    public LeeractiviteitModel(JSONObject object){
        try {
            this.soort = object.getString("soort");
            this.cursus = object.getString("cursus");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Factory method to convert an array of JSON objects into a list of objects
    // User.fromJson(jsonArray);
    public static ArrayList<ResultaatModel> fromJson(JSONArray jsonObjects) {
        ArrayList<ResultaatModel> users = new ArrayList<ResultaatModel>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                users.add(new ResultaatModel(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public String getSoort(){
        return soort;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }
}
