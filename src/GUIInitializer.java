import javax.swing.*;
import java.awt.*;

public class GUIInitializer {


    public static void run(){
        //Window frame of app:
        JFrame mainFrame = new JFrame("Minesweeper");
        //passing properties of if e.g dimensions ,close button=>close app e.t.c
        setMainFrameProperties(mainFrame);

        AppUnicodeIcon icons=new AppUnicodeIcon();//collection of Unicode icons to populate UI


        //Top panel: includes Number of flags, Timer and Restart button of the game
        JPanel menuPanel= new JPanel(new GridLayout(1, 3, 10, 10));
            menuPanel.setPreferredSize(new Dimension(410, 100));
                //Flag section
                JLabel flagsLeft=new JLabel();
                    setFlagsLeftProperties(flagsLeft,icons);
                //Restart section
                JButton resetGameFace=new JButton(icons.getHappyFace());
                    setResetGameFaceProperties(resetGameFace);
                //Timer section
                JLabel timer=new JLabel();
                    setTimerJLabelProperties(timer);

            menuPanel.add(flagsLeft);
            menuPanel.add(resetGameFace);
            menuPanel.add(timer);

        //Bottom panel: minefield

        JPanel minefieldPanel= new JPanel(new GridLayout(9,9));
        minefieldPanel.setPreferredSize(new Dimension(350,500));
            //Creating the minefield
            MinefieldCreator field=new MinefieldCreator(9,9);
            passMineFieldIntoPanel(minefieldPanel,field.getMinefieldJButtons());


        //Spacers
        JPanel spacer1Left=new JPanel();
        JPanel spacer2Right=new JPanel();
        JPanel spacer3Bottom=new JPanel();
        spacer1Left.setPreferredSize(new Dimension(20,500));
        spacer2Right.setPreferredSize(new Dimension(20,500));
        spacer3Bottom.setPreferredSize(new Dimension(350,20));

        mainFrame.getContentPane().add(menuPanel,BorderLayout.PAGE_START);
        mainFrame.getContentPane().add(minefieldPanel,BorderLayout.CENTER);

        mainFrame.getContentPane().add(spacer1Left,BorderLayout.WEST);
        mainFrame.getContentPane().add(spacer2Right,BorderLayout.EAST);
        mainFrame.getContentPane().add(spacer3Bottom,BorderLayout.SOUTH);
        mainFrame.setVisible(true);
    }

    private static void setMainFrameProperties(JFrame frame){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(500,600);
        frame.setResizable(false);
    }

    private static void setFlagsLeftProperties(JLabel flagsLeft , AppUnicodeIcon icons){
        flagsLeft.setText( "     " +   Integer.toString(FlagsCounter.getFlagsCounter()) + icons.getFlag());
        Font font = flagsLeft.getFont();
        flagsLeft.setFont(font.deriveFont(Font.BOLD, 50f));
      //  flagsLeft.setPreferredSize(new Dimension(100, 70));
    }

    private static void setResetGameFaceProperties(JButton resetGameFace){
        Font font = resetGameFace.getFont();
        resetGameFace.setFont(font.deriveFont(Font.BOLD, 50f));
        resetGameFace.setForeground(Color.GREEN);
        resetGameFace.setContentAreaFilled(false);
        resetGameFace.setBorderPainted(false);
        FontMetrics fontMetrics = resetGameFace.getFontMetrics(resetGameFace.getFont());
        int textWidth = fontMetrics.stringWidth(resetGameFace.getText());
        int textHeight = fontMetrics.getHeight();
        resetGameFace.setPreferredSize(new Dimension(textWidth+60,textHeight));
        resetGameFace.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private static void setTimerJLabelProperties(JLabel timerLabel){
       // timerLabel.setPreferredSize(new Dimension(100, 70));
        GameTimer gameTimer= new GameTimer(timerLabel);
        Font font = timerLabel.getFont();
        timerLabel.setFont(font.deriveFont(Font.BOLD, 50f));
    }


    private static void passMineFieldIntoPanel(JPanel minefieldPanel,JButton[][] minefield2DArray){
        for (int i = 0; i < minefield2DArray.length; i++) {
            for (int j = 0; j < minefield2DArray[0].length; j++) {
                minefieldPanel.add(minefield2DArray[i][j]);
            }
        }
    }


}
