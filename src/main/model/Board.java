package model;


import org.json.JSONArray;
import org.json.JSONObject;
import ui.MoodListDisplay;

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
    // EFFECTS: given the size, add number of pixels for board with corresponding month and day.
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

            if (startM == 12) {
                startM = 0;
            }
            startD = 1;
            startM += 1;
        }
    }

    // REQUIRES: daysToAdd > 0, pixels has atleast length one
    // MODIFIES: this
    // EFFECTS: add the given number of days to end of mood board, month and day should continue from the last day in
    //          mood board
    public void addDayToBoard(int daysToAdd) {

        int newStartMonth;
        int newStartDay;
        int originalBoardSize = this.getSize();
        int monthOfLastPixelInBoard = this.getPixels().get(originalBoardSize - 1).getMonth();
        int dayOfLastPixelInBoard = this.getPixels().get(originalBoardSize - 1).getDay();

        if ((dayOfLastPixelInBoard == 31 && MONTHS_WITH31DAYS.contains(monthOfLastPixelInBoard))
                || (dayOfLastPixelInBoard == 31 && MONTHS_WITH30DAYS.contains(monthOfLastPixelInBoard))
                || (dayOfLastPixelInBoard == 28 && monthOfLastPixelInBoard == 2)) {
            newStartDay = 1;
            newStartMonth = monthOfLastPixelInBoard + 1;
        } else {
            newStartDay = dayOfLastPixelInBoard + 1;
            newStartMonth = monthOfLastPixelInBoard;
        }

        if (newStartMonth == 13) {
            newStartMonth = 1;
        }
        createBoard(daysToAdd, newStartMonth, newStartDay);
    }


    // REQUIRES: pixelnum must be < pixels.size(), moodNum must be < moodList.size()
    // MODIFIES: this
    // EFFECTS: set the pixel of pixelnum's mood field to the mood associated with the given moodNum
    public void recordMood(int pixelnum, int moodNum) {
        int index = pixelnum - 1;
        MoodListDisplay ml = new MoodListDisplay();
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

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("mood board", pixelsToJson());
        return json;
    }


    // EFFECTS: returns pixels in board as a JSON array
    private JSONArray pixelsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pixel p : pixels) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

    // REQUIRES: pixel to add has day and month that continues from the last pixel in current board
    // MODIFIES: this
    // EFFECTS: adds pixel to end of board
    public void addPixel(Pixel pixel) {
        pixels.add(pixel);
    }
}
