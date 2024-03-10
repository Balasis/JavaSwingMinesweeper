package AppStart;

import GUI.AppUnicodeIcon;
import GUI.GUIInitializer;
import Minefield.MinefieldButton;
import PathOpener.MainPathOpener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JButtonListeners implements GameObserver {

    private final AppUnicodeIcon icons=new AppUnicodeIcon();
    private final GUIInitializer gui;
    private final MinefieldButton[][] theMinefield;

    public JButtonListeners(MinefieldButton[][] minefield2DArray,GUIInitializer gui){
        this.gui=gui;
        this.theMinefield=minefield2DArray;
        addListenerToFaceButton();
        addListenersToMinefieldBtns(minefield2DArray);
    }

    private void addListenerToFaceButton(){
        //getResetGameFace listener
        gui.getResetGameFace().addActionListener(e -> {
            restartGame();
        });
    }

    private void addListenersToMinefieldBtns(MinefieldButton[][] minefield2DArray){
        //Minefield buttons listeners:
        for (int i = 0; i < minefield2DArray.length; i++) {
            for (int j = 0; j < minefield2DArray[0].length; j++) {
                int finalI = i;
                int finalJ = j;
                //Left click listener :
                minefield2DArray[i][j].addActionListener(e -> {
                    //Reveal it no matter what
                    minefield2DArray[finalI][finalJ].setRevealed(true);

                    //Reset the flag in case there as one
                    setFlagText(minefield2DArray[finalI][finalJ]);
                    //Check if it is a mine
                    if (minefield2DArray[finalI][finalJ].isAMine()){
                        onGameOver();
                        //check if it is an indicator
                    }else if (minefield2DArray[finalI][finalJ].isAnIndicator()){
                        checkWinCondition();
                        //Any other cases means it is an empty cell
                        // therefore just expand(open the closed ones)
                    }else{
                        //Opener has combinations of expanding and open adjacent buttons/cells
                        MainPathOpener pathOpener=new MainPathOpener();
                        pathOpener.openPaths(minefield2DArray,finalI,finalJ);
                        checkWinCondition();
                    }

                });

                //RIGHT CLICK :sets a flag on not revealed buttons.
                minefield2DArray[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            setFlagText(minefield2DArray[finalI][finalJ]);
                        }
                    }
                });
            }
        }
    }

    private void setFlagText(MinefieldButton button){
        if (button.isRevealed()){
            if (button.isFlagged()){
                gui.getFlagsCounter().increaseFlagsCounter();
                button.setFlagged(false);
                button.setText("");
                button.setForeground(null);
                redisplayFlagNumber();
            }
            return;
        }
        if (!button.isFlagged()) {
            button.setFlagged(true);
            gui.getFlagsCounter().decreaseFlagsCounter();
            button.setText("\uD83D\uDEA9");
            button.setForeground(Color.RED);
            redisplayFlagNumber();
        }else{
            gui.getFlagsCounter().increaseFlagsCounter();
            button.setFlagged(false);
            button.setText("");
            button.setForeground(null);
            redisplayFlagNumber();
        }
    }

    @Override
    public void checkWinCondition() {
        boolean gameWon=true;
        for (int i = 0; i < theMinefield.length; i++) {
            for (int j = 0; j < theMinefield[0].length; j++) {
                    if (!theMinefield[i][j].isAMine() && !theMinefield[i][j].isRevealed()){
                        gameWon=false;
                        break;
                    }
                }
            }
        if (gameWon){
            gui.getGameTimer().stopTimer();
            JOptionPane.showMessageDialog(null, "Congratulations! You've won the game!");
        }
    }

    @Override
    public void onGameOver() {
        revealEverything();
        gui.getResetGameFace().setText(icons.getWoopsyFace());
        gui.getResetGameFace().setForeground(Color.RED);
        gui.getGameTimer().stopTimer();
    }

    private void revealEverything(){
        for (int i = 0; i < theMinefield.length; i++) {
            for (int j = 0; j < theMinefield[0].length; j++) {
                theMinefield[i][j].setRevealed(true);
            }
        }
    }

    @Override
    public void redisplayFlagNumber() {
        gui.getFlagsLeft().setText( "     " +   Integer.toString(gui.getFlagsCounter().getFlagsCounter()) + icons.getFlag());
    }

    private void restartGame() {
        // Create a new instance of GUIInitializer to start a fresh game
        SwingUtilities.invokeLater(() -> {
            if (gui.getMainFrame()!= null) {
                gui.getMainFrame().dispose();
            }
            GUIInitializer newGame = new GUIInitializer();
            newGame.run();
        });
    }

}
