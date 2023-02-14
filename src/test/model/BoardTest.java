package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.MoodList;

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
        MoodList ml = new MoodList();
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