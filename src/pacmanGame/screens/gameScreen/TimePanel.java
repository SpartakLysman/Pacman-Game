package pacmanGame.screens.gameScreen;

import pacmanGame.Constants;
import pacmanGame.models.PacmanModel;
import pacmanGame.shapes.GameShape;

import javax.swing.*;
import java.awt.*;

import static helpers.ImageHelper.scaleImage;
import static pacmanGame.Constants.TIME_IMAGES_PASS;

public class TimePanel extends JPanel {

    private DigitsBlock hours = new DigitsBlock();
    private DigitsBlock minutes = new DigitsBlock();
    private DigitsBlock seconds = new DigitsBlock();
    private Image[] time = new Image[8];
    private Thread timerThread;

    public TimePanel() {
        seconds.setDelegate(minutes);
        minutes.setDelegate(hours);
        seconds.reset();
        setupImage();
        startTimer();
    }

    private void startTimer() {
        timerThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                resolveImage();
                repaint();
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

    public void resolveImage() {
        time[0] = scaleImage(new ImageIcon(TIME_IMAGES_PASS + hours.getFirstDigit() + ".png").getImage(), 20, 10);
        time[1] = scaleImage(new ImageIcon(TIME_IMAGES_PASS + hours.getSecondDigit() + ".png").getImage(), 20, 10);

        time[3] = scaleImage(new ImageIcon(TIME_IMAGES_PASS + minutes.getFirstDigit() + ".png").getImage(), 20, 10);
        time[4] = scaleImage(new ImageIcon(TIME_IMAGES_PASS + minutes.getSecondDigit() + ".png").getImage(), 20, 10);

        time[6] = scaleImage(new ImageIcon(TIME_IMAGES_PASS + seconds.getFirstDigit() + ".png").getImage(), 20, 10);
        time[7] = scaleImage(new ImageIcon(TIME_IMAGES_PASS + seconds.getSecondDigit() + ".png").getImage(), 20, 10);
    }

    public void setupImage() {
        time[0] = scaleImage(new ImageIcon(Constants.ZERO_TIME_FILE_NAME).getImage(), 20, 10);
        time[1] = scaleImage(new ImageIcon(Constants.ZERO_TIME_FILE_NAME).getImage(), 20, 10);
        time[2] = scaleImage(new ImageIcon(Constants.DOTS_TIME_FILE_NAME).getImage(), 20, 10);
        time[3] = scaleImage(new ImageIcon(Constants.ZERO_TIME_FILE_NAME).getImage(), 20, 10);
        time[4] = scaleImage(new ImageIcon(Constants.ZERO_TIME_FILE_NAME).getImage(), 20, 10);
        time[5] = scaleImage(new ImageIcon(Constants.DOTS_TIME_FILE_NAME).getImage(), 20, 10);
        time[6] = scaleImage(new ImageIcon(Constants.ZERO_TIME_FILE_NAME).getImage(), 20, 10);
        time[7] = scaleImage(new ImageIcon(Constants.ZERO_TIME_FILE_NAME).getImage(), 20, 10);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < time.length; i++) {
            drawImage(g, i);
        }
    }

    private void drawImage(Graphics g, int number) {
        g.drawImage(time[number], 5 + number * 12,5, this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(104, 30);
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