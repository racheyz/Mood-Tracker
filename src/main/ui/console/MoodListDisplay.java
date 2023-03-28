package ui.console;

import model.Mood;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// A list of moods
public class MoodListDisplay {

    private static final Color ANGRY_RED = new Color(250,   121,112);
    private static final Color ANXIOUS_ORANGE = new Color(250,163,86);
    private static final Color SICK_YELLOW = new Color(255,255,102);
    private static final Color HAPPY_LIGHTGREEN = new Color(127,227,139);
    private static final Color CALM_LIGHTBLUE = new Color(162,210,251);
    private static final Color SAD_DARKBLUE = new Color(68,116,158);
    private static final Color TIRED_LIGHTPURPLE = new Color(206,165,251);
    private static final Color EXCITED_PINK = new Color(242,161,188);
    private static final Color NEUTRAL_GRAY = new Color(198,205,213);


    private List<Mood> moodList;

    // EFFECTS: make an empty mood list and calls the method that makes a standard mood list
    public MoodListDisplay() {
        moodList = new ArrayList<>();
        standardMoodList();
    }

    // MODIFIES: this
    // EFFECTS: creates the standard moods and adds it in moodList
    public void standardMoodList() {
        Mood angry = new Mood(ANGRY_RED,"angry");
        Mood anxious = new Mood(ANXIOUS_ORANGE,"anxious");
        Mood sick = new Mood(SICK_YELLOW,"sick");
        Mood happy = new Mood(HAPPY_LIGHTGREEN,"happy");
        Mood calm = new Mood(CALM_LIGHTBLUE,"calm");
        Mood sad = new Mood(SAD_DARKBLUE,"sad");
        Mood tired = new Mood(TIRED_LIGHTPURPLE,"tired");
        Mood excited = new Mood(EXCITED_PINK,"excited");
        Mood neutral = new Mood(NEUTRAL_GRAY,"neutral");
        moodList.add(angry);
        moodList.add(anxious);
        moodList.add(sick);
        moodList.add(happy);
        moodList.add(calm);
        moodList.add(sad);
        moodList.add(tired);
        moodList.add(excited);
        moodList.add(neutral);
    }

    // EFFECTS: prints out every mood in moodList in order from first to last
    public void printMoodList() {
        int count = 0;
        for (Mood m: this.moodList) {
            System.out.println(count + " -> " + m.getEmotion());
            count++;
        }
    }

    // REQUIRES: the emotion must be in the standardMoodList
    // EFFECTS: returns the mood associated with the emotion
    public Mood getMoodFromString(String emotion) {
        for (Mood m: moodList) {
            if (m.getEmotion().equals(emotion)) {
                return m;
            }
        }
        return null;
    }

    // getters
    public List<Mood> getMoodList() {
        return moodList;
    }


}
