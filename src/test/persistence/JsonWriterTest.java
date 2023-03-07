package persistence;

import model.Board;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Board board = new Board();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyBoard() {
        try {
            Board board = new Board();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBoard.json");
            writer.open();
            writer.write(board);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBoard.json");
            board = reader.read();
            assertEquals(0, board.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBoard() {
        try {
            Board board = new Board();
            board.createBoard(31,1,1);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBoard.json");
            writer.open();
            writer.write(board);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBoard.json");
            board = reader.read();
            assertEquals(31, board.getSize());
            assertEquals(1, board.getPixels().get(0).getMonth());
            assertEquals(1, board.getPixels().get(0).getDay());
            assertEquals(" ", board.getPixels().get(0).getMood().getEmotion());
            assertEquals(1, board.getPixels().get(30).getMonth());
            assertEquals(31, board.getPixels().get(30).getDay());
            assertEquals(" ", board.getPixels().get(30).getMood().getEmotion());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
