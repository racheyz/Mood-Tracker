package ui;

import ui.gui.StartPage;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        new StartPage();
/*        try {
            new Tracker();
        } catch (FileNotFoundException e) {
            System.out.println("Error in loading file...");
        }*/
    }
}
