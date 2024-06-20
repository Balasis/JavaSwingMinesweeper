package gui;

import appstart.JButtonListeners;
import menurelated.FlagsCounter;
import menurelated.GameTimer;
import minefield.MinefieldButton;
import minefield.MinefieldCreator;

import javax.swing.*;
import java.awt.*;

public class GUIInitializer {

    private final AppUnicodeIcon icons = new AppUnicodeIcon();
    private JFrame mainFrame;
    private final FlagsCounter flagsCounter = new FlagsCounter();
    private JLabel flagsLeft;//placeholder for flagsCounter
    private GameTimer gameTimer;
    private JButton resetGameFace;


    public void run() {
        mainFrame = new JFrame("Minesweeper by John Balasis - AthensTech 2024");
        setMainFrameProperties(mainFrame);//Size,WindowsClose e.t.c

        //Top panel
        JPanel menuPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        menuPanel.setPreferredSize(new Dimension(410, 100));
        //Top ui elements
        flagsLeft = new JLabel();
        resetGameFace = new JButton(icons.getHappyFace());
        //placeholderForGameTimer
        JLabel timer = new JLabel();
        //passing ui properties to them
        setFlagsLeftProperties(flagsLeft, icons);
        setResetGameFaceProperties(resetGameFace);
        setTimerJLabelProperties(timer);
        //add Them to Top ui panel
        menuPanel.add(flagsLeft);
        menuPanel.add(resetGameFace);
        menuPanel.add(timer);

        //Bottom panel: minefield
        JPanel minefieldPanel = new JPanel(new GridLayout(9, 9));
        minefieldPanel.setPreferredSize(new Dimension(350, 500));

        //Bottom ui elements
        MinefieldCreator field = new MinefieldCreator(9, 9);
        passMineFieldIntoPanel(minefieldPanel, field.getMinefieldJButtons());
        JButtonListeners listeners = new JButtonListeners(field.getMinefieldJButtons(), this);
        passListenerRefToFieldBtn(field.getMinefieldJButtons(), listeners);

        //Bottom ui Spacer elements
        JPanel spacer1Left = new JPanel();
        JPanel spacer2Right = new JPanel();
        JPanel spacer3Bottom = new JPanel();
        spacer1Left.setPreferredSize(new Dimension(20, 500));
        spacer2Right.setPreferredSize(new Dimension(20, 500));
        spacer3Bottom.setPreferredSize(new Dimension(350, 20));

        //Passing Panels to Main Frame
        mainFrame.getContentPane().add(menuPanel, BorderLayout.PAGE_START);
        mainFrame.getContentPane().add(minefieldPanel, BorderLayout.CENTER);
        mainFrame.getContentPane().add(spacer1Left, BorderLayout.WEST);
        mainFrame.getContentPane().add(spacer2Right, BorderLayout.EAST);
        mainFrame.getContentPane().add(spacer3Bottom, BorderLayout.SOUTH);

        mainFrame.setVisible(true);
    }

    private void setMainFrameProperties(JFrame frame) {
        ImageIcon i = new ImageIcon("ichigo.ico");
        frame.setIconImage(i.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 600);
        frame.setResizable(false);
    }

    private void setFlagsLeftProperties(JLabel flagsLeft, AppUnicodeIcon icons) {
        flagsLeft.setText("     " + Integer.toString(flagsCounter.getFlagsCounter()) + icons.getFlag());
        Font font = flagsLeft.getFont();
        flagsLeft.setFont(font.deriveFont(Font.BOLD, 50f));
    }

    private void setResetGameFaceProperties(JButton resetGameFace) {
        Font font = resetGameFace.getFont();
        resetGameFace.setFont(font.deriveFont(Font.BOLD, 50f));
        resetGameFace.setForeground(Color.GREEN);
        resetGameFace.setContentAreaFilled(false);
        resetGameFace.setBorderPainted(false);
        FontMetrics fontMetrics = resetGameFace.getFontMetrics(resetGameFace.getFont());
        int textWidth = fontMetrics.stringWidth(resetGameFace.getText());
        int textHeight = fontMetrics.getHeight();
        resetGameFace.setPreferredSize(new Dimension(textWidth + 60, textHeight));
        resetGameFace.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void setTimerJLabelProperties(JLabel timerLabel) {
        //Timer of Swing..per Second refreshes the Time label
        gameTimer = new GameTimer(timerLabel);
        Font font = timerLabel.getFont();
        timerLabel.setFont(font.deriveFont(Font.BOLD, 50f));
    }

    private void passMineFieldIntoPanel(JPanel minefieldPanel, JButton[][] minefield2DArray) {
        for (int i = 0; i < minefield2DArray.length; i++) {
            for (int j = 0; j < minefield2DArray[0].length; j++) {
                minefieldPanel.add(minefield2DArray[i][j]);
            }
        }
    }

    private void passListenerRefToFieldBtn(MinefieldButton[][] minefield2DArray, JButtonListeners listeners) {
        for (int i = 0; i < minefield2DArray.length; i++) {
            for (int j = 0; j < minefield2DArray[0].length; j++) {
                minefield2DArray[i][j].setListeners(listeners);
            }
        }
    }

    //Getters
    public JFrame getMainFrame() {
        return mainFrame;
    }

    public JLabel getFlagsLeft() {
        return flagsLeft;
    }

    public JButton getResetGameFace() {
        return resetGameFace;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    public FlagsCounter getFlagsCounter() {
        return flagsCounter;
    }
}
