package model;


import ui.MoodList;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// a mood tracker board containing an arbitrary amount of days
public class Board {

    private static final List<Integer> MONTHS_WITH31DAYS = Arrays.asList(1,3,5,7,8,10,12);
    private static final List<Integer> MONTHS_WITH30DAYS = Arrays.asList(4,6,9,11);

    private List<Pixel> pixels;

    //make a tracker board with an empty array of pixels
    public Board() {
        pixels = new ArrayList<>();
    }

    // REQUIRES: size > 0, 1 <= startM <= 12
    //           if startM is in MONTHS_WITH31DAYS: 1 <= startD <= 31
    //           if startM is in MONTHS_WITH30DAYS: 1 <= startD <= 30
    //           if startM == 2: 1 <= startD <= 28
    // MODIFIES: this
    // EFFECTS: given the size, make number of pixels for board with corresponding month and day.
    //          First pixel begins with the starting month and day.
    public void createBoard(int size, int startM, int startD) {
        int daysLeftInMonth;
        Mood emptymood = new Mood(Color.BLACK, " ");

        while (size > 0) {
            if (MONTHS_WITH31DAYS.contains(startM)) {
                daysLeftInMonth = Math.min(size, 32 - startD);
            } else if (MONTHS_WITH30DAYS.contains(startM)) {
                daysLeftInMonth = Math.min(size,31 - startD);
            } else {
                daysLeftInMonth = Math.min(size, 29 - startD);
            }

            for (int i = 1;i <= daysLeftInMonth;i++) {
                pixels.add(new Pixel(startM,startD,emptymood));
                startD++;
                size -= 1;
            }
            startD = 1;
            startM += 1;
        }
    }

    // REQUIRES: pixelnum must be < pixels.size(), moodNum must be < moodList.size()
    // MODIFIES: this
    // EFFECTS: set the pixel of pixelnum's mood field to the mood associated with the given moodNum
    public void recordMood(int pixelnum, int moodNum) {
        int index = pixelnum - 1;
        MoodList ml = new MoodList();
        Mood mood = ml.getMoodList().get(moodNum);
        this.pixels.get(index).setMood(mood);
    }

    // EFFECTS: count the number of times a mood is tracked in a board
    public int countMoodOccurences(Mood mood) {
        int count = 0;
        for (Pixel p: pixels) {
            if (p.getMood().getEmotion().equals(mood.getEmotion())) {
                count++;
            }
        }
        return count;
    }

    // EFFECTS: return length of pixels
    public int getSize() {
        return pixels.size();
    }

    // getters for fields
    public List<Pixel> getPixels() {
        return pixels;
    }



}
