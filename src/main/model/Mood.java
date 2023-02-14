package model;

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

    //getters
    public Color getColour() {
        return colour;
    }

    public String getEmotion() {
        return emotion;
    }


}
