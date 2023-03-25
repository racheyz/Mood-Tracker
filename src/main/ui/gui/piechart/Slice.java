package ui.gui.piechart;

import java.awt.*;

// represents a slice in the pie chart with a value and a colour
public class Slice {

    private double value;
    private Color colour;

    public Slice(double value, Color c) {
        this.value = value;
        this.colour = c;
    }

    public double getValue() {
        return value;
    }

    public Color getColour() {
        return colour;
    }
}
