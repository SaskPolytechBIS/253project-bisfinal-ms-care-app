package com.example.mscarealpha.ui.symptoms;

public class Symptom {
    public String getSymptomName;
    private String bodyPart;
    private String symptomName;
    private int painLevel;
    private String notes;
    private long timestamp; // Added to store the time of logging

    // Constructor
    public Symptom(String bodyPart, String symptomName, int painLevel, String notes, long timestamp) {
        this.bodyPart = bodyPart;
        this.symptomName = symptomName;
        this.painLevel = painLevel;
        this.notes = notes;
        this.timestamp = timestamp;
    }

    // Getters
    public String getBodyPart() {
        return bodyPart;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public int getPainLevel() {
        return painLevel;
    }

    public String getNotes() {
        return notes;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }

    public void setPainLevel(int painLevel) {
        this.painLevel = painLevel;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
