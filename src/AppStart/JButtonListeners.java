package AppStart;

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
        //getResetGameFace listener
        gui.getResetGameFace().addActionListener(e -> {
           restartGame();
        });



        //SET ON CLICK ACTION...CASES OF BOMB INDICATOR OR EMPTY CELL/BUTTON
        for (int i = 0; i < minefield2DArray.length; i++) {
            for (int j = 0; j < minefield2DArray[0].length; j++) {
                int finalI = i;
                int finalJ = j;

                //left CLICK cases
                minefield2DArray[i][j].addActionListener(e -> {

                    minefield2DArray[finalI][finalJ].setRevealed(true);
                    //reset the flag if you open button that was flagged
                    setFlagText(minefield2DArray[finalI][finalJ]);

                    if (minefield2DArray[finalI][finalJ].isAMine()){
                        onGameOver();
                    }else if (minefield2DArray[finalI][finalJ].isAnIndicator()){

                    }else{
                        MainPathOpener pathOpener=new MainPathOpener();
                        pathOpener.openPaths(minefield2DArray,finalI,finalJ);
                    }
                });
                //RIGHT CLICK
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
    public void onGameOver() {
        revealEverything();
        gui.getResetGameFace().setText(icons.getWoopsyFace());
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

    // Method to restart the game
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
