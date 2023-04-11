package ui.gui.piechart;

import java.awt.*;

// represents a slice in the pie chart with a value and a colour
public class Slice {

    private double value;
    private Color colour;

    // REQUIRES: value >= 0
    // EFFECTS: constructs a slice with given value and colour
    public Slice(double value, Color c) {
        this.value = value;
        this.colour = c;
    }

    // getters
    public double getValue() {
        return value;
    }

    public Color getColour() {
        return colour;
    }
}
