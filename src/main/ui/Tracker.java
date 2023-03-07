package ui;

import model.Board;
import model.Mood;
import model.Pixel;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// A mood tracker application
public class Tracker {
    private Scanner input;
    private String choice;
    MoodListDisplay ml = new MoodListDisplay();
    private static final String JSON_STORE = "./data/board.json";
    private Board board;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // runs tracker
    public Tracker() throws FileNotFoundException {
        board = new Board();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTracker();
    }

    // MODIFIES: this
    // EFFECTS: displays beginning instructions and process user's inputs
    public void runTracker() {
        boolean keepGoing = true;
        System.out.println("Welcome to your personalized mood tracker\n");
        displayStartMenu();
        choice = input.next();
        while (!(choice.equals("c") || choice.equals("ld"))) {
            System.out.println("Invalid choice. Please choose again:\n");
            choice = input.next();
        }
        processChoice(choice);

        while (keepGoing) {
            displayChoiceMenu();
            choice = input.next();

            while (!(choice.equals("r") || choice.equals("v") || choice.equals("s") || choice.equals("q")
                    || choice.equals("a") || choice.equals("sv"))) {
                System.out.println("Invalid choice. Please choose again:\n");
                choice = input.next();
            }

            if (choice.equals("q")) {
                keepGoing = false;
            } else {
                processChoice(choice);
            }
        }
    }

    public void displayStartMenu() {
        System.out.println("\nc --> create new mood board");
        System.out.println("ld --> load mood board from file\n");
    }

    // EFFECTS: prints choice menu
    public void displayChoiceMenu() {
        System.out.println("\nv --> view mood board");
        System.out.println("r --> record mood");
        System.out.println("s --> summarize mood");
        System.out.println("a --> add days");
        System.out.println("sv --> save mood board to file");
        System.out.println("q --> quit tracker\n");
    }

    // EFFECTS: process user's inputs
    public void processChoice(String c) {
        if (c.equals("c")) {
            makeBoard();
        } else if (c.equals("ld")) {
            loadBoard();
        } else if (c.equals("v")) {
            displayBoard();
        } else if (c.equals("r")) {
            record();
        } else if (c.equals("s")) {
            moodSummary();
        } else if (c.equals("a")) {
            addToBoard();
        } else if (c.equals("sv")) {
            saveBoard();
        }
    }

    // EFFECTS: makes a default tracker with no moods recorded based on user's chosen size
    public void makeBoard() {
        input = new Scanner(System.in);
        System.out.println("Enter number of days you want to track:");
        int size = input.nextInt();
        System.out.println("Enter your starting date (month):");
        int startMonth = input.nextInt();
        System.out.println("Enter your starting date (day):");
        int startDay = input.nextInt();
        board.createBoard(size, startMonth, startDay);
    }

    // EFFECTS: load board from file
    public void loadBoard() {
        try {
            board = jsonReader.read();
            if (board.getSize() == 0) {
                System.out.println("The current board from file is empty, please start by creating a new board");
                makeBoard();
            } else {
                System.out.println("Loaded board from " + JSON_STORE);
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    // EFFECTS: display mood board
    public void displayBoard() {
        int count = 1;
        for (Pixel p : board.getPixels()) {
            System.out.println("Day " + count + ":");
            System.out.println("   Month:" + p.getMonth());
            System.out.println("   Day:" + p.getDay());
            System.out.println("   Mood:" + p.getMood().getEmotion() + "\n");
            count++;
        }
    }

    // MODIFIES: this
    // EFFECTS: allows you to record a mood for a specific day
    public void record() {
        System.out.println("Enter the day number you want to track:");
        int pixelToTrack = input.nextInt();
        displayMoods();
        int moodNum = input.nextInt();
        board.recordMood(pixelToTrack, moodNum);
        System.out.println("Your mood has been tracked.\n");
    }

    // EFFECTS: displays mood menu
    public void displayMoods() {
        System.out.println("Choose from the following moods:\n");
        ml.printMoodList();
    }

    // EFFECTS: gives a summary for how many days you've recorded each mood
    public void moodSummary() {
        int totalDays = board.getSize();
        for (Mood m: ml.getMoodList()) {
            System.out.println(m.getEmotion() + ": " + board.countMoodOccurences(m) + "/" + totalDays);
        }
    }

    //MODIFIES: this
    //EFFECTS: asks how many days the user would like to add, and adds it to board
    public void addToBoard() {
        System.out.println("How many days would you like to add?");
        int daysToAdd = input.nextInt();
        board.addDayToBoard(daysToAdd);
    }

    // EFFECTS: save mood board to file
    public void saveBoard() {
        try {
            jsonWriter.open();
            jsonWriter.write(board);
            jsonWriter.close();
            System.out.println("Saved current mood board to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

}
