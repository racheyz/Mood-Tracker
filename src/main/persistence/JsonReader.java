package persistence;

import model.Board;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Mood;
import model.Pixel;
import org.json.JSONArray;
import org.json.JSONObject;

// A reader that loads in a board from Json file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads board from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Board read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBoard(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses board from JSON object and returns it
    private Board parseBoard(JSONObject jsonObject) {
        Board board = new Board();
        addPixels(board, jsonObject);
        return board;
    }

    // MODIFIES: board
    // EFFECTS: parses pixels from JSON object and adds them to board
    private void addPixels(Board b, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("mood board");
        for (Object json : jsonArray) {
            JSONObject nextPixel = (JSONObject) json;
            addPixel(b, nextPixel);
        }
    }

    // MODIFIES: board
    // EFFECTS parse pixel from JSON object and adds it to board
    private void addPixel(Board b, JSONObject jsonObject) {
        int month = jsonObject.getInt("month");
        int day = jsonObject.getInt("day");

        JSONObject jsonMood = jsonObject.getJSONObject("mood");
        int rgb = jsonMood.getInt("colour");
        Color colour = new Color(rgb);
        String emotion = jsonMood.getString("emotion");

        Mood mood = new Mood(colour, emotion);
        Pixel pixel = new Pixel(month, day, mood);

        b.addPixel(pixel);
    }
}

