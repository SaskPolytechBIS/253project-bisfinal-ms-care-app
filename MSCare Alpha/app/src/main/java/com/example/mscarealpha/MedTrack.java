package com.example.mscarealpha;

import org.json.JSONException;
import org.json.JSONObject;

public class MedTrack {
    private String mMed;
    private String mDos;
    private String mTimes;

    private static final String JSON_MED = "title";

    private static final String JSON_DOSAGE = "dosage";

    private static final String JSON_TIMES = "times";

    public String getMed(){ return mMed; }

    public void setMed(String mMed){ this.mMed = mMed; }

    public String getDos(){ return mDos; }

    public void setDos(String mDos){ this.mDos = mDos; }

    public String getTimes(){ return mTimes; }

    public void setTimes(String mTimes){ this.mTimes = mTimes; }

    public MedTrack(JSONObject jo) throws JSONException{
        mMed = jo.getString(JSON_MED);
        mDos = jo.getString(JSON_DOSAGE);
        mTimes = jo.getString(JSON_TIMES);
    }

    public MedTrack(){

    }

    public JSONObject convertToJSON() throws JSONException{
        JSONObject jo = new JSONObject();

        jo.put(JSON_MED, mMed);
        jo.put(JSON_DOSAGE, mDos);
        jo.put(JSON_TIMES, mTimes);


        return jo;
    }
}
