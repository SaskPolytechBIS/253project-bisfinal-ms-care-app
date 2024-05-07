package com.example.mscarealpha.ui.notes;

import org.json.JSONException;
import org.json.JSONObject;

public class AppointmentNotes {
    private String mTitle;
    private String mDescription;
    private boolean mQuestions;
    private boolean mNotes;
    private boolean mTodo;

    private static final String JSON_TITLE = "title";
    private static final String JSON_DESCRIPTION = "description";
    private static final String JSON_QUESTIONS = "questions";
    private static final String JSON_NOTES = "notes";
    private static final String JSON_TODO = "todo";


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public boolean isQuestions() {
        return mQuestions;
    }

    public void setQuestions(boolean mQuestions) {
        this.mQuestions = mQuestions;
    }

    public boolean isNotes() {
        return mNotes;
    }

    public void setNotes(boolean mNotes) {
        this.mNotes = mNotes;
    }

    public boolean isTodo() {
        return mTodo;
    }

    public void setTodo(boolean mTodo) {
        this.mTodo = mTodo;
    }

    // Constructor
    // Only used when new is called with a JSONObject
    public AppointmentNotes(JSONObject jo) throws JSONException {

        mTitle =  jo.getString(JSON_TITLE);
        mDescription = jo.getString(JSON_DESCRIPTION);
        mQuestions = jo.getBoolean(JSON_QUESTIONS);
        mNotes = jo.getBoolean(JSON_NOTES);
        mTodo = jo.getBoolean(JSON_TODO);

    }

    // Now we must provide an empty default constructor
    // for when we create a Note as we provide a
    // specialized constructor.
    public AppointmentNotes (){

    }

    public JSONObject convertToJSON() throws JSONException{

        JSONObject jo = new JSONObject();

        jo.put(JSON_TITLE, mTitle);
        jo.put(JSON_DESCRIPTION, mDescription);
        jo.put(JSON_QUESTIONS, mQuestions);
        jo.put(JSON_NOTES, mNotes);
        jo.put(JSON_TODO, mTodo);

        return jo;
    }

}
