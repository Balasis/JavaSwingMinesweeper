package AppStart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MinefieldButton extends JButton{
    private int indicatorNumber;
    private boolean isAnIndicator;
    private boolean isEmpty=true;
    private boolean isAMine;
    private boolean isRevealed=false;
    private boolean isFlagged=false;
    private AppUnicodeIcon icons;

    public MinefieldButton(){
    addListeners();
    }

    public MinefieldButton(boolean isAnIndicator, boolean isAMine ){
    this.isAnIndicator = isAnIndicator;
    this.isAMine=isAMine;
    addListeners();
    }

    private void addListeners(){
        //Left click listener (set to revealButton)
        this.addActionListener((ActionEvent e) -> {
            this.setRevealed(true);
        });

        //Right Click listener (set to flag)
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    setFlagText();
                }
            }
        });
    }

    private void setFlagText(){
        if (isRevealed){
            return;
        }
            if (!isFlagged) {
                isFlagged = true;
                FlagsCounter.decreaseFlagsCounter();
                this.setText("\uD83D\uDEA9");
                this.setForeground(Color.RED);
                System.out.println(FlagsCounter.getFlagsCounter());
            }else{
                FlagsCounter.increaseFlagsCounter();
                isFlagged=false;
                this.setText("");
                this.setForeground(null);
                System.out.println(FlagsCounter.getFlagsCounter());
            }
    }


    public void setAnIndicator(boolean anIndicator) {
        isAnIndicator = anIndicator;
        isEmpty = false;
    }

    public void setAMine(boolean AMine) {
        isAMine = AMine;
        isEmpty = false;
    }

    public void setRevealed(boolean revealed) {
        this.setText(" ");
        this.setBackground(Color.gray);
        String buttonString= isAMine ? icons.getMine() : isAnIndicator() ? Integer.toString(indicatorNumber) : " ";
        this.setText(buttonString);
        isRevealed = revealed;

    }

    private void setRevealedColor(){

        this.setForeground(Color.RED);
    }



    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isAnIndicator() {
        return isAnIndicator;
    }

    public boolean isAMine() {
        return isAMine;
    }

    public boolean isRevealed() {
        return isRevealed;
    }


    @Override
    public String toString() {
        return "MinefieldJButton{" +
                "isEmpty=" + isEmpty +
                ", isAnIndicatorNumber=" + isAnIndicator +
                ", isAMine=" + isAMine +
                ", isRevealed=" + isRevealed +
                '}';
    }

}
