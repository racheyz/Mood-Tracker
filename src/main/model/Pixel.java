package model;

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

    // MODIFIES: this
    // EFFECTS: setter for mood
    public void setMood(Mood mood) {
        this.mood = mood;
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