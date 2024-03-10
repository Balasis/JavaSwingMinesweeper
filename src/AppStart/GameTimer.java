package AppStart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTimer {
    private int minutes;
    private int seconds;
    private Timer swingTimer;
    private JLabel timerLabel;

    public GameTimer(JLabel timerLabel) {
        this.timerLabel=timerLabel;
        minutes = 0;
        seconds = 0;
        swingTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                if (seconds == 60) {
                    minutes++;
                    seconds = 0;
                }
                sendToUi();
            }
        });
        swingTimer.start();
    }

    public void stopTimer() {
        swingTimer.stop();
    }

    private String sendToUi(){
        timerLabel.setText(toString());
       return String.format("  %02d:%02d", minutes, seconds);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", minutes, seconds);
    }

}