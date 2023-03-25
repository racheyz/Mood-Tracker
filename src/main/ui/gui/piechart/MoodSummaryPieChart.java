package ui.gui.piechart;

import model.Board;
import model.Mood;
import ui.console.MoodListDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// A pie chart representing the mood summary
public class MoodSummaryPieChart extends JComponent {

    private List<Slice> slices;
    private MoodListDisplay moodListDisplay;

    public MoodSummaryPieChart(Board board) {
        this.moodListDisplay = new MoodListDisplay();
        this.slices = setSlices(board);
    }

    // EFFECTS: creates a list of slices representing each mood using data from board
    public List<Slice> setSlices(Board board) {
        List<Slice> slices = new ArrayList<>();
        for (Mood m: moodListDisplay.getMoodList()) {
            Slice slice = new Slice(board.countMoodOccurences(m), m.getColour());
            slices.add(slice);
        }
        return slices;
    }

    //
    public void paint(Graphics g) {
        drawPie((Graphics2D) g, getBounds(), slices);
    }

    // EFFECTS: creates the pie chart
    public void drawPie(Graphics2D g, Rectangle area, List<Slice> slices) {
        double total = 0.0D;
        for (int i = 0; i < slices.size(); i++) {
            total += slices.get(i).getValue();
        }

        double curValue = 0.0D;
        int startAngle = 0;
        for (int i = 0; i < slices.size(); i++) {
            startAngle = (int) (curValue * 360 / total);
            int arcAngle = (int) (slices.get(i).getValue() * 360 / total);

            g.setColor(slices.get(i).getColour());
            g.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle);
            curValue += slices.get(i).getValue();
        }
    }
}

