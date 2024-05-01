package com.example.mscarealpha;

import org.json.JSONException;
import org.json.JSONObject;

public class MedTrack {
    private String mMed;
    private String mDos;

    private static final String JSON_MED = "title";

    private static final String JSON_DESCRIPTION = "description";

    public String getMed(){ return mMed; }

    public void setMed(String mMed){ this.mMed = mMed; }

    public String getDos(){ return mDos; }

    public void setDos(String mDos){ this.mDos = mDos; }

    public MedTrack(JSONObject jo) throws JSONException{
        mMed = jo.getString(JSON_MED);
        mDos = jo.getString(JSON_DESCRIPTION);
    }

    public MedTrack(){

    }

    public JSONObject convertToJSON() throws JSONException{
        JSONObject jo = new JSONObject();

        jo.put(JSON_MED, mMed);
        jo.put(JSON_DESCRIPTION, mDos);


        return jo;
    }
}
