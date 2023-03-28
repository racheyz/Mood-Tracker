package ui.gui;

import model.Board;
import model.Mood;
import model.Pixel;
import persistence.JsonWriter;
import ui.console.MoodListDisplay;
import ui.gui.piechart.MoodSummaryPieChart;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainApp extends JFrame implements ActionListener, MouseListener {
    private JFrame frame;
    private Board board;
    private JPanel mainPanel;
    private JPanel daysDisplay;
    private JPanel functionsPanel;
    private JPanel summaryPanel;
    private GridBagConstraints gbc;
    private JButton addButton;
    private JButton saveButton;
    private JButton dayButton;
    private JButton pieChartButton;
    private JLabel moodSummary;
    private JsonWriter jsonWriter;
    private JMenuItem mood;
    private JPopupMenu moodOptions;
    private Map<JButton,Pixel> daysMap;
    private MoodListDisplay moodListDisplay;
    private static final String JSON_STORE = "./data/board.json";


    public MainApp(Board board, JFrame frame) {
        this.frame = frame;
        this.board = board;
        this.mainPanel = new JPanel(new BorderLayout());
        this.summaryPanel = new JPanel();
        this.daysMap = new HashMap<>();
        this.daysDisplay = new JPanel(new GridBagLayout());
        this.functionsPanel = new JPanel();
        this.moodListDisplay = new MoodListDisplay();
        this.gbc = new GridBagConstraints();
        setUp();
        run();
    }

    // EFFECTS: sets up the frame and panels
    public void setUp() {
        resetFrame(frame);
        initializeDaysDisplay(board);
        initializeFunctionsPanel();
        mainPanel.setBackground(new Color(47,47,47));
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    // MODIFIES: this
    //EFFECTS: clears current frame
    public void resetFrame(JFrame frame) {
        Container oldPanel = frame.getContentPane();
        frame.remove(oldPanel);
    }

    // MODIFIES: this
    // EFFECTS: creates the mood board display panel with given board
    public void initializeDaysDisplay(Board board) {
        List<Pixel> pixels = board.getPixels();
        gbc.gridx = 25;
        gbc.gridy = 0;
        for (Pixel p: pixels) {
            dayButton = new JButton("" + p.getMonth() + " / " + p.getDay() + "");
            dayButton.setBackground(p.getMood().getColour());
            dayButton.setPreferredSize(new Dimension(35,35));
            dayButton.setMargin(new Insets(1, 1, 1, 1));
            dayButton.setFont(new Font("Arial", Font.BOLD, 8));
            dayButton.setBorder(BorderFactory.createLineBorder(new Color(47,47,47),1));
            daysMap.put(dayButton, p);
            daysDisplay.add(dayButton, gbc);
            dayButton.addMouseListener(this);
            gbc.gridx += 50;
            if (gbc.gridx >= 1320) {
                gbc.gridx = 25;
                gbc.gridy += 50;
            }
        }
        daysDisplay.setBackground(new Color(47,47,47));
        daysDisplay.setBorder(BorderFactory.createEmptyBorder(20, 20,10,20));
        mainPanel.add(daysDisplay, BorderLayout.WEST);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: creates the functionality panel
    public void initializeFunctionsPanel() {
        functionsPanel.setLayout(new BoxLayout(functionsPanel,BoxLayout.Y_AXIS));
        addButton = new JButton("   Add Day   ");
        saveButton = new JButton("Save Tracker");
        addButton.setBackground(Color.WHITE);
        saveButton.setBackground(Color.WHITE);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        functionsPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        functionsPanel.add(addButton);
        functionsPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        functionsPanel.add(saveButton);
        functionsPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        functionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 5,10,50));
        functionsPanel.setBackground(new Color(47,47,47));
        mainPanel.add(functionsPanel,BorderLayout.EAST);
        addSummaryLabels();
    }

    // MODIFIES: this
    // EFFECTS: creates labels to display the mood summary
    public void addSummaryLabels() {
        int totalDays = board.getSize();
        for (Mood m: moodListDisplay.getMoodList()) {
            moodSummary = new JLabel(m.getEmotion() + ": " + board.countMoodOccurences(m) + "/" + totalDays);
            moodSummary.setForeground(m.getColour());
            summaryPanel.add(moodSummary);
            summaryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        addSummaryChartFunction();
    }

    // MODIFIES: this
    // EFFECTS: add pie chart feature for representing mood summary to functions panel
    public void addSummaryChartFunction() {
        pieChartButton =  new JButton("Generate Pie Chart");
        pieChartButton.setBackground(Color.WHITE);
        pieChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Mood Summary");
                frame.getContentPane().add(new MoodSummaryPieChart(board));
                frame.setSize(300, 300);
                frame.setVisible(true);
            }
        });
        summaryPanel.add(pieChartButton);
        summaryPanel.setLayout(new BoxLayout(summaryPanel,BoxLayout.Y_AXIS));
        summaryPanel.setBackground(new Color(47,47,47));
        summaryPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        functionsPanel.add(summaryPanel);
    }

    // EFFECTS: running the page with action listeners for add and save buttons
    public void run() {
        addButton.addActionListener(this);
        saveButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: action listeners for adding days to mood board and saving board to file
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == addButton) {
            board.addDayToBoard(1);
            mainPanel.remove(daysDisplay);
            frame.revalidate();
            frame.repaint();
            initializeDaysDisplay(board);
            summaryPanel.removeAll();
            functionsPanel.revalidate();
            functionsPanel.repaint();
            addSummaryLabels();
        } else if (evt.getSource() == saveButton) {
            jsonWriter = new JsonWriter(JSON_STORE);
            try {
                jsonWriter.open();
                jsonWriter.write(board);
                jsonWriter.close();
            } catch (FileNotFoundException exp) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: mouse listener for when a user clicks a cell in mood board to record a mood
    @Override
    public void mouseClicked(MouseEvent me) {
        Pixel p = daysMap.get(me.getComponent());
        mood = new JMenuItem();
        List<Mood> moodList = moodListDisplay.getMoodList();
        moodOptions = new JPopupMenu();
        for (Mood m: moodList) {
            mood = new JMenuItem(m.getEmotion());
            mood.setActionCommand(m.getEmotion());
            mood.setBackground(m.getColour());
            mood.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    p.setMood(moodListDisplay.getMoodFromString(e.getActionCommand()));
                    me.getComponent().setBackground(
                            moodListDisplay.getMoodFromString(e.getActionCommand()).getColour());
                    summaryPanel.removeAll();
                    functionsPanel.revalidate();
                    functionsPanel.repaint();
                    addSummaryLabels();
                }

            });
            moodOptions.add(mood);
        }
        moodOptions.show(me.getComponent(),me.getX(),me.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}
