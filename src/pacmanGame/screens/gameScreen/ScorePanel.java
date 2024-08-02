package pacmanGame.screens.gameScreen;

import pacmanGame.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScorePanel extends JPanel {

    private int width;
    private int height;
    private GameModel model;
    private JButton backButton;

    public ScorePanel(int width, int height) {
        this.width = width;
        this.height = height;

        TimePanel timePanel = new TimePanel();
        add(timePanel);
    }



    @Override
    public Dimension getPreferredSize() {
        System.out.println("Score panel Width: " + width + ",  Height: " + height);
        return new Dimension(width, height);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
