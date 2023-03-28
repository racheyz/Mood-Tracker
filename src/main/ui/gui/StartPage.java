package ui.gui;

import model.Board;
import persistence.JsonReader;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class StartPage extends JFrame implements MouseListener {
    private JFrame frame;
    private JPanel panel;
    private JLabel welcomeText;
    private JLabel createBoard;
    private JLabel loadBoard;
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
        frame.setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: sets up all components in panel
    public void initializeStartPanel() {
        panel = new JPanel();
        panel.setBackground(new Color(47,47,47));
        panel.setBorder(BorderFactory.createLineBorder(Color.white,15,true));


        createBoard = new JLabel("Create New Mood Board");
        loadBoard = new JLabel("Load Mood Board From File");
        welcomeText = new JLabel("Welcome To Your Personalized Mood Tracker");

        setAlignmentAndColour();
        panel.add(Box.createRigidArea(new Dimension(0, 100)));
        panel.add(welcomeText);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(createBoard);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(loadBoard);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        frame.add(panel);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets alignment and colours for labels and buttons
    public void setAlignmentAndColour() {
        createBoard.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        loadBoard.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        welcomeText.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        welcomeText.setForeground(Color.WHITE);
        welcomeText.setFont(new Font("Ubuntu", Font.BOLD, 30));

        createBoard.setForeground(Color.WHITE);
        createBoard.setFont(new Font("Ubuntu", Font.BOLD, 30));

        loadBoard.setFont(new Font("Ubuntu", Font.BOLD, 30));
        loadBoard.setForeground(Color.WHITE);
    }

    // EFFECTS: runs page with mouselisteners for creating new board and loading board from file
    public void run() {
        createBoard.addMouseListener(this);
        loadBoard.addMouseListener(this);
    }

    // EFFECTS: mouse listeners for create new board and load board form file
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(createBoard)) {
            new CreateNewBoardPage(frame);
        } else if (e.getSource().equals(loadBoard)) {
            jsonReader = new JsonReader(JSON_STORE);
            try {
                board = jsonReader.read();
                new MainApp(board, frame);
            } catch (IOException exp) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(createBoard)) {
            e.getComponent().setForeground(Color.cyan);
        } else if (e.getSource().equals(loadBoard)) {
            e.getComponent().setForeground(new Color(107, 226, 159));
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.getComponent().setForeground(Color.WHITE);
    }
}
