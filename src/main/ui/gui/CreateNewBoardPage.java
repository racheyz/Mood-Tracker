package ui.gui;

import model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the page where you can create a new board
public class CreateNewBoardPage extends JFrame {
    private JPanel panel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JButton button;
    private JFrame frame;
    private Board board;

    // MODIFIES: this
    // EFFECT: sets up frame and runs page
    public CreateNewBoardPage(JFrame frame) {
        this.frame = frame;
        setUp();
        run();
    }

    // MODIFIES: this
    // EFFECTS: sets up frame and panels
    public void setUp() {
        resetFrame();
        initializePanel();
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    // MODIFIES: this
    //EFFECTS: clears current frame
    public void resetFrame() {
        Container oldPanel = frame.getContentPane();
        frame.remove(oldPanel);
    }

    // MODIFIES: this
    // EFFECTS: initializes components for page
    public void initializePanel() {
        label1 = new JLabel("Enter the number of days you want to track");
        label2 = new JLabel("Enter your starting month");
        label3 = new JLabel("Enter your starting day");
        text1 = new JTextField();
        text2 = new JTextField();
        text3 = new JTextField();
        button = new JButton("Create Tracker");
        setAppearance();
        panel = new JPanel();
        panel.setBackground(new Color(47,47,47));
        panel.add(Box.createRigidArea(new Dimension(0, 100)));
        panel.add(label1);
        panel.add(text1);
        panel.add(Box.createRigidArea(new Dimension(0, 70)));
        panel.add(label2);
        panel.add(text2);
        panel.add(Box.createRigidArea(new Dimension(0, 70)));
        panel.add(label3);
        panel.add(text3);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(button);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 300,10,300));
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    }

    // MODIFIES: this
    // EFFECTS: set alignment,size, font, and colour for the components
    public void setAppearance() {
        label1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        label2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        label3.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        text1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        text2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        text3.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        text1.setPreferredSize(new Dimension(20, 1));
        text2.setPreferredSize(new Dimension(20, 1));
        text3.setPreferredSize(new Dimension(20, 1));
        label1.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);
        label3.setForeground(Color.WHITE);
        label1.setFont(new Font("Ubuntu", Font.BOLD, 15));
        label2.setFont(new Font("Ubuntu", Font.BOLD, 15));
        label2.setFont(new Font("Ubuntu", Font.BOLD, 15));
    }

    // EFFECTS: runs page with action listeners
    public void run() {
        revalidate();
        repaint();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board = new Board();
                int numDays = Integer.parseInt(text1.getText());
                int month = Integer.parseInt(text2.getText());
                int days = Integer.parseInt(text3.getText());
                board.createBoard(numDays,month,days);
                new MainAppPage(board, frame);
            }
        });
    }
    
}
