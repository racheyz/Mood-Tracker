package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.MoodListDisplay;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


class BoardTest {

    private Board testBoard;

    @BeforeEach
    public void setUp() {
        testBoard = new Board();
    }

    @Test
    public void testConstructor() {
        assertTrue(testBoard.getPixels().isEmpty());
    }

    @Test
    public void testCreateBoardTypicalMonth() {
        testBoard.createBoard(31, 1, 1);
        assertEquals(31, testBoard.getPixels().size());
        assertEquals(1, testBoard.getPixels().get(0).getMonth());
        assertEquals(1, testBoard.getPixels().get(0).getDay());
        assertEquals(1, testBoard.getPixels().get(30).getMonth());
        assertEquals(31, testBoard.getPixels().get(30).getDay());
        assertEquals(" ", testBoard.getPixels().get(10).getMood().getEmotion());
        assertEquals(Color.BLACK, testBoard.getPixels().get(10).getMood().getColour());
    }

    @Test
    public void testCreateBoardDiffTypicalMonth() {
        testBoard.createBoard(28, 2, 1);
        assertEquals(28, testBoard.getPixels().size());
        assertEquals(2, testBoard.getPixels().get(0).getMonth());
        assertEquals(1, testBoard.getPixels().get(0).getDay());
        assertEquals(2, testBoard.getPixels().get(27).getMonth());
        assertEquals(28, testBoard.getPixels().get(27).getDay());
        assertEquals(" ", testBoard.getPixels().get(10).getMood().getEmotion());
        assertEquals(Color.BLACK, testBoard.getPixels().get(10).getMood().getColour());
    }

    @Test
    public void testCreateBoardMultipleMonths() {
        testBoard.createBoard(50, 1, 1);
        assertEquals(50, testBoard.getPixels().size());
        assertEquals(1, testBoard.getPixels().get(0).getMonth());
        assertEquals(1, testBoard.getPixels().get(0).getDay());
        assertEquals(2, testBoard.getPixels().get(49).getMonth());
        assertEquals(19, testBoard.getPixels().get(49).getDay());
        assertEquals(" ", testBoard.getPixels().get(10).getMood().getEmotion());
        assertEquals(Color.BLACK, testBoard.getPixels().get(10).getMood().getColour());
    }

    @Test
    public void testCreateBoardDiffMultipleMonths() {
        testBoard.createBoard(70, 6, 3);
        assertEquals(70, testBoard.getPixels().size());
        assertEquals(6, testBoard.getPixels().get(0).getMonth());
        assertEquals(3, testBoard.getPixels().get(0).getDay());
        assertEquals(8, testBoard.getPixels().get(69).getMonth());
        assertEquals(11, testBoard.getPixels().get(69).getDay());
        assertEquals(" ", testBoard.getPixels().get(10).getMood().getEmotion());
        assertEquals(Color.BLACK, testBoard.getPixels().get(10).getMood().getColour());
    }

    @Test
    public void testAddDayToBoardToMidofMonth() {
        testBoard.createBoard(15, 1, 1);
        int sizeBefore = testBoard.getSize();
        testBoard.addDayToBoard(10);
        assertEquals(sizeBefore + 10, testBoard.getSize());
        assertEquals(1, testBoard.getPixels().get(15).getMonth());
        assertEquals(16, testBoard.getPixels().get(15).getDay());
        assertEquals(1, testBoard.getPixels().get(24).getMonth());
        assertEquals(25, testBoard.getPixels().get(24).getDay());
    }

    @Test
    public void testAddDayToBoardToEndOf31Month() {
        testBoard.createBoard(31, 1, 1);
        int sizeBefore = testBoard.getSize();
        testBoard.addDayToBoard(10);
        assertEquals(sizeBefore + 10, testBoard.getSize());
        assertEquals(2, testBoard.getPixels().get(31).getMonth());
        assertEquals(1, testBoard.getPixels().get(31).getDay());
        assertEquals(2, testBoard.getPixels().get(40).getMonth());
        assertEquals(10, testBoard.getPixels().get(40).getDay());
    }

    @Test
    public void testAddDayToBoardToEndOf30Month() {
        testBoard.createBoard(30, 4, 1);
        int sizeBefore = testBoard.getSize();
        testBoard.addDayToBoard(10);
        assertEquals(sizeBefore + 10, testBoard.getSize());
        assertEquals(5, testBoard.getPixels().get(30).getMonth());
        assertEquals(1, testBoard.getPixels().get(30).getDay());
        assertEquals(5, testBoard.getPixels().get(39).getMonth());
        assertEquals(10, testBoard.getPixels().get(39).getDay());
    }

    @Test
    public void testAddDayToBoardToEndOf28Month() {
        testBoard.createBoard(28, 2, 1);
        int sizeBefore = testBoard.getSize();
        testBoard.addDayToBoard(10);
        assertEquals(sizeBefore + 10, testBoard.getSize());
        assertEquals(3, testBoard.getPixels().get(28).getMonth());
        assertEquals(1, testBoard.getPixels().get(28).getDay());
        assertEquals(3, testBoard.getPixels().get(37).getMonth());
        assertEquals(10, testBoard.getPixels().get(37).getDay());
    }

    @Test
    public void testAddDayToBoardDecToJan() {
        testBoard.createBoard(31, 12, 1);
        int sizeBefore = testBoard.getSize();
        testBoard.addDayToBoard(30);
        assertEquals(sizeBefore + 30, testBoard.getSize());
        assertEquals(1, testBoard.getPixels().get(31).getMonth());
        assertEquals(1, testBoard.getPixels().get(31).getDay());
        assertEquals(1, testBoard.getPixels().get(60).getMonth());
        assertEquals(30, testBoard.getPixels().get(60).getDay());
    }


    @Test
    public void testRecordMoodOnce() {
        testBoard.createBoard(31, 1, 1);
        testBoard.recordMood(1,0);
        assertEquals("angry", testBoard.getPixels().get(0).getMood().getEmotion());
    }

    @Test
    public void testRecordMoodChangeMood() {
        testBoard.createBoard(31, 1, 1);
        testBoard.recordMood(1,0);
        testBoard.recordMood(1,3);
        assertEquals("happy", testBoard.getPixels().get(0).getMood().getEmotion());
    }

    @Test
    public void testCountMoodOccurences() {
        testBoard.createBoard(31, 1, 1);
        testBoard.recordMood(1,0);
        MoodListDisplay ml = new MoodListDisplay();
        assertEquals(0, testBoard.countMoodOccurences(ml.getMoodList().get(2)));
        assertEquals(1, testBoard.countMoodOccurences(ml.getMoodList().get(0)));
    }

    @Test
    public void testGetSizeEmptyList() {
        assertEquals(0, testBoard.getSize());
    }

    @Test
    public void testGetSize() {
        testBoard.createBoard(31, 1, 1);
        assertEquals(31,testBoard.getSize());
    }

}