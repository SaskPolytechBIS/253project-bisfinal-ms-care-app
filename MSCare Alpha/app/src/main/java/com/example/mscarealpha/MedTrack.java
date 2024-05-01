package com.example.mscarealpha;

import org.json.JSONException;
import org.json.JSONObject;

public class MedTrack {
    private String mMed;
    private String mDos;

    private static final String JSON_TITLE = "title";



    public String getMed(){ return mMed; }

    public void setMed(String mMed){ this.mMed = mMed; }


    public MedTrack(JSONObject jo) throws JSONException{
        mMed = jo.getString(JSON_TITLE);
    }

    public MedTrack(){

    }

    public JSONObject convertToJSON() throws JSONException{
        JSONObject jo = new JSONObject();

        jo.put(JSON_TITLE, mMed);


        return jo;
    }
}
