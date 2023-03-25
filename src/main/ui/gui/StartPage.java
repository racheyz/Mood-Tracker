package ui.gui;

import model.Board;
import persistence.JsonReader;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartPage extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private JLabel welcomeText;
    private JButton createB;
    private JButton loadB;
    private Board board;
    private static final String JSON_STORE = "./data/board.json";
    private JsonReader jsonReader;
    private Graphics2D shape;


    public StartPage() {
        this.frame = new JFrame();
        setUp();
        run();
    }

    // EFFECTS: sets up the frame and panels
    public void setUp() {
        initializeFrame();
        initializeStartPanel();
    }

    // MODIFIES: this
    // EFFECTS: sets up new JFrame
    public void initializeFrame() {
        frame.setTitle("My Mood Tracker");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
    }

    // MODIFIES: this
    // EFFECTS: sets up all components in panel
    public void initializeStartPanel() {
        panel = new JPanel();

        panel.setBackground(Color.DARK_GRAY);
        createB = new JButton("Create New Mood Tracker");
        loadB = new JButton("Load Mood Tracker From File");
        welcomeText = new JLabel("Welcome To Your Personalized Mood Tracker");

        setAlignmentAndColour();

        panel.add(Box.createRigidArea(new Dimension(0, 100)));
        panel.add(welcomeText);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(createB);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(loadB);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        frame.add(panel);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets alignment and colours for labels and buttons
    public void setAlignmentAndColour() {
        createB.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        loadB.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        welcomeText.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        createB.setBackground(Color.WHITE);
        loadB.setBackground(Color.WHITE);
        welcomeText.setForeground(Color.WHITE);
        welcomeText.setFont(new Font("Ubuntu", Font.BOLD, 30));
    }

    // EFFECTS: run start page with action listeners
    public void run() {
        createB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateNewBoardPage(frame);
            }
        });
        loadB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jsonReader = new JsonReader(JSON_STORE);
                try {
                    board = jsonReader.read();
                    new MainApp(board, frame);
                } catch (IOException exp) {
                    System.out.println("Unable to read from file: " + JSON_STORE);
                }
            }
        });
    }
}
