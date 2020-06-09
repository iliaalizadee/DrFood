package com.khawrizmi.iliaalizadeh.doctorfood;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class FetchAdvice {
    public int id;
    public String date, drname, data,title,target;


    public FetchAdvice(JSONObject jsonObject) {
        try {
            this.title= new String(jsonObject.getString("title").getBytes("UTF-8"));
            this.id = jsonObject.getInt("id");
            this.date = new String(jsonObject.getString("date").getBytes( "UTF-8"));
            this.drname = new String(jsonObject.getString("drname").getBytes( "UTF-8"));
            this.data = new String(jsonObject.getString("data").getBytes( "UTF-8"));
            this.target = new String(jsonObject.getString("target").getBytes("UTF-8"));

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
