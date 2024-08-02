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

public class HealthPanel extends JPanel {

    private int width;
    private int height;
    private JButton backButton;

    public HealthPanel(int width, int height) {
        this.width = width;
        this.height = height;
        initialize();
        backButton = new JButton("menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToMenu();
            }
        });
        add(backButton);
    }

    private void initialize() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.BLACK);

        // Create and add JLabel for the image
        JLabel imageLabel = new JLabel();
        try {
            BufferedImage originalImage = ImageIO.read(new File(Constants.FOUR_HEALTH_FILE_NAME)); // Use your image path
            int newWidth = width / 2;
            int newHeight = height;
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g2d.dispose();
            imageLabel.setIcon(new ImageIcon(resizedImage));
        } catch (IOException e) {
            imageLabel.setText("Image not found");
            imageLabel.setForeground(Color.RED);
            e.printStackTrace();
        }
        imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(imageLabel);
        add(Box.createRigidArea(new Dimension(130, height))); // Add spacing
    }

    private void returnToMenu() {
        // Notify the frame to return to the menu
        Container parent = this.getParent();
        while (!(parent instanceof GameScreenFrame)) {
            parent = parent.getParent();
        }
        ((GameScreenFrame) parent).showMenuScreen();
    }

    @Override
    public Dimension getPreferredSize() {
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
