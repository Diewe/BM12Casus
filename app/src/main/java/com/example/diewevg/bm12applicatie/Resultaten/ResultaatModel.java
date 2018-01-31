package com.example.diewevg.bm12applicatie.Resultaten;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Diewevg on 30-1-2018.
 */

public class ResultaatModel {
    public String cijfer;
    public String cursus;

    public ResultaatModel(String cijfer, String cursus) {
        this.cijfer = cijfer;
        this.cursus = cursus;
    }

    public ResultaatModel(JSONObject object){
        try {
            this.cijfer = object.getString("cijfer");
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
}
