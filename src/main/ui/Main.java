package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new Tracker();
        } catch (FileNotFoundException e) {
            System.out.println("Error in loading file...");
        }
    }
}
