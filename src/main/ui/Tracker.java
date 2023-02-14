package ui;

import model.Board;
import model.Mood;
import model.Pixel;

import java.util.List;
import java.util.Scanner;

// A mood tracker application
public class Tracker {

    private Scanner input;
    MoodList ml = new MoodList();

    // runs tracker
    public Tracker() {
        runTracker();
    }

    // MODIFIES: this
    // EFFECTS: displays beginning instructions and process user's inputs
    public void runTracker() {
        boolean keepGoing = true;
        System.out.println("Welcome to your personalized mood tracker\n");
        System.out.println("Let's get started by making your custom board!\n");
        Board board = makeBoard();

        while (keepGoing) {
            displayChoiceMenu();
            String choice = input.next();

            while (!(choice.equals("r") || choice.equals("v") || choice.equals("s") || choice.equals("q"))) {
                System.out.println("Invalid choice. Please choose again:\n");
                choice = input.next();
            }

            if (choice.equals("q")) {
                keepGoing = false;
            } else {
                processChoice(choice, board);
            }
        }
    }

    // EFFECTS: makes a default tracker with no moods recorded based on user's input
    public Board makeBoard() {
        input = new Scanner(System.in);
        System.out.println("Enter number of days you want to track:");
        int size = input.nextInt();
        System.out.println("Enter your starting date (month):");
        int startMonth = input.nextInt();
        System.out.println("Enter your starting date (day):");
        int startDay = input.nextInt();
        Board b = new Board();
        b.createBoard(size, startMonth, startDay);
        return b;
    }

    // EFFECTS: prints choice menu
    public void displayChoiceMenu() {
        System.out.println("\nv --> view mood board");
        System.out.println("r --> record mood");
        System.out.println("s --> summarize mood");
        System.out.println("q --> quit tracker\n");
    }


    // EFFECTS: process user's inputs
    public void processChoice(String c, Board b) {
        if (c.equals("v")) {
            displayBoard(b);
        } else if (c.equals("r")) {
            record(b);
        } else if (c.equals("s")) {
            moodSummary(b);
        }
    }

    // EFFECTS: display mood board
    public void displayBoard(Board b) {
        int count = 1;
        for (Pixel p : b.getPixels()) {
            System.out.println("Day " + count + ":");
            System.out.println("   Month:" + p.getMonth());
            System.out.println("   Day:" + p.getDay());
            System.out.println("   Mood:" + p.getMood().getEmotion() + "\n");
            count++;
        }
    }

    // MODIFIES: this
    // EFFECTS: allows you to record a mood for a specific day
    public void record(Board b) {
        System.out.println("Enter the day number you want to track:");
        int pixelToTrack = input.nextInt();
        displayMoods();
        int moodNum = input.nextInt();
        b.recordMood(pixelToTrack, moodNum);
        System.out.println("Your mood has been tracked.\n");
    }

    // EFFECTS: displays mood menu
    public void displayMoods() {
        System.out.println("Choose from the following moods:\n");
        ml.printMoodList();
    }

    // EFFECTS: gives a summary for how many days you've recorded each mood
    public void moodSummary(Board b) {
        int totalDays = b.getSize();
        for (Mood m: ml.getMoodList()) {
            System.out.println(m.getEmotion() + ": " + b.countMoodOccurences(m) + "/" + totalDays);
        }
    }
}
