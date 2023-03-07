package persistence;

import model.Board;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {
    @Test
    void testReaderNoFileFound() {
        JsonReader reader = new JsonReader("./data/noFilejson");
        try {
            Board board = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBoard.json");
        try {
            Board board = reader.read();
            assertEquals(0, board.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBoard.json");
        try {
            Board board = reader.read();
            assertEquals(31, board.getSize());
            assertEquals(1, board.getPixels().get(0).getMonth());
            assertEquals(1, board.getPixels().get(0).getDay());
            assertEquals(" ", board.getPixels().get(0).getMood().getEmotion());
            assertEquals(1, board.getPixels().get(30).getMonth());
            assertEquals(31, board.getPixels().get(30).getDay());
            assertEquals(" ", board.getPixels().get(30).getMood().getEmotion());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
