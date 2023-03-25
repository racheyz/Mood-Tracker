package model;

import org.json.JSONObject;

import java.awt.*;

// Represents a mood option with a number, colour and emotion.
public class Mood {
    private Color colour;
    private String emotion;

    //make a mood with a colour and emotion;
    public Mood(Color c, String e) {
        this.colour = c;
        this.emotion = e;
    }

    // EFFECTS: writes the given mood to json
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("colour", colour.getRGB());
        json.put("emotion", emotion);
        return json;
    }

    //getters
    public Color getColour() {
        return colour;
    }

    public String getEmotion() {
        return emotion;
    }


}
