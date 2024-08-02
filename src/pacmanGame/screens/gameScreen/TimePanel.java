package pacmanGame.screens.gameScreen;

import pacmanGame.models.PacmanModel;

import javax.swing.*;
import java.awt.*;

public class TimePanel extends JPanel {

    private DigitsBlock hours = new DigitsBlock();
    private DigitsBlock minutes = new DigitsBlock();
    private DigitsBlock seconds = new DigitsBlock();
    private Image hourImage;
    private Image minutesImage;
    private Image secondsImage;
    private Thread timerThread;

    public TimePanel() {
        seconds.setDelegate(minutes);
        minutes.setDelegate(hours);
        seconds.reset();
        startTimer();
    }

    private void startTimer() {
        timerThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                this.seconds.next();
                System.out.println(hours + ":" + minutes + ":" + seconds);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();  // Restore interrupted status
                }
            }
        });
        timerThread.start();
    }

    public void stop() {
        if (timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt();
        }
    }

    public int getHours() {
        return hours.getValue();
    }

    public int getMinutes() {
        return minutes.getValue();
    }

    public int getSeconds() {
        return seconds.getValue();
    }
}