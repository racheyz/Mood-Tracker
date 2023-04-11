package model;

import org.json.JSONObject;

// Represents a pixel on the board with a corresponding month, day, and mood
public class Pixel {

    private int month;
    private int day;
    private Mood mood;

    // EFFECTS: make a pixel with month, day, and mood
    public Pixel(int m, int d, Mood mood) {
        this.month = m;
        this.day = d;
        this.mood = mood;
    }

    // EFFECTS: writes the pixel to json
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("month", month);
        json.put("day", day);
        json.put("mood", mood.toJson());
        return json;
    }

    // MODIFIES: this
    // EFFECTS: setter for mood
    public void setMood(Mood mood) {
        this.mood = mood;
        EventLog.getInstance().logEvent(new Event("Recorded a mood"));
    }

    // getters
    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public Mood getMood() {
        return mood;
    }

}
