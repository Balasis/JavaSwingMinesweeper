package minefield;

import appstart.JButtonListeners;
import gui.AppUnicodeIcon;

import javax.swing.*;
import java.awt.*;

public class MinefieldButton extends JButton {
    private int indicatorNumber;
    private boolean isAnIndicator;
    private boolean isEmpty = true;
    private boolean isAMine;
    private boolean isRevealed = false;
    private boolean isFlagged = false;
    private AppUnicodeIcon icons = new AppUnicodeIcon();
    private JButtonListeners listeners;

    public MinefieldButton() {
        //remove the square effect on focus
        this.setFocusable(false);
    }

    public MinefieldButton(boolean isAnIndicator, boolean isAMine) {
        this.isAnIndicator = isAnIndicator;
        this.isAMine = isAMine;
        //remove the square effect on focus
        this.setFocusable(false);
    }

    public void setIndicatorNumber(int indicatorNumber) {
        this.indicatorNumber = indicatorNumber;
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

        String buttonString = isAMine ? icons.getMine() : isAnIndicator() ? Integer.toString(indicatorNumber) : " ";
        if (this.isFlagged) {
            listeners.setFlagText(this);
        }
        setRevealedColor();
        this.setText(buttonString);
        isRevealed = revealed;

    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    private void setRevealedColor() {
        Color c;
        if (isAnIndicator) {
            if (indicatorNumber == 1) {
                c = Color.GREEN;
            } else if (indicatorNumber == 2) {
                c = Color.blue;
            } else if (indicatorNumber == 3) {
                c = Color.orange;
            } else {
                c = Color.RED;
            }

        } else if (isAMine) {
            c = Color.BLACK;
        } else {
            c = Color.GRAY;
        }
        this.setForeground(c);
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

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setListeners(JButtonListeners listeners) {
        this.listeners = listeners;
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
