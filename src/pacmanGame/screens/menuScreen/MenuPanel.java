package pacmanGame.screens.menuScreen;

import pacmanGame.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class MenuPanel extends JPanel {
    private MenuModel menuModel;
    private MenuListener menuListener;

    public MenuPanel(MenuModel menuModel) {
        this.menuModel = menuModel;
        initialize();
    }

    private void initialize() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);

        // Create and add JLabel for the image
        JLabel imageLabel = new JLabel();
        try {
            BufferedImage originalImage = ImageIO.read(new File(Constants.PAC_MAN_ICON_FILE_NAME)); // Use your image path
            int newWidth = originalImage.getWidth() * 3; // Triple the width
            int newHeight = originalImage.getHeight() * 3; // Triple the height
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
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(imageLabel);
        add(Box.createRigidArea(new Dimension(0, 100))); // Add spacing

        // Create image buttons for "Start Game", "Options", and "Exit"
        JButton startGameButton = createImageButton(Constants.START_GAME_FILE_NAME, "Start Game"); // Path to your Start Game image
        JButton optionsButton = createImageButton(Constants.OPTIONS_FILE_NAME, "Options"); // Path to your Options image
        JButton exitButton = createImageButton(Constants.EXIT_GAME_FILE_NAME, "Exit"); // Path to your Exit image
        JButton highScoreButton = createImageButtonWithSize(Constants.HIGH_SCORE_FILE_NAME, "High Score", 161,20); // Path to your Exit image

        // Add buttons to the panel
        addButtonToPanel(startGameButton);
        addButtonToPanel(optionsButton);
        addButtonToPanel(exitButton);
        addButtonToPanel(highScoreButton);
    }

    private JButton createImageButtonWithSize(String imagePath, String actionCommand, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        JButton button = new JButton(scaledIcon);
        button.setActionCommand(actionCommand);
        button.setContentAreaFilled(false); // Remove the button's background
        button.setBorderPainted(false);     // Remove the button's border
        button.setFocusPainted(false);      // Remove the focus border
        button.addActionListener(new MenuButtonListener());
        return button;
    }

    private JButton createImageButton(String imagePath, String actionCommand) {
        ImageIcon icon = new ImageIcon(imagePath);
        JButton button = new JButton(icon);
        button.setActionCommand(actionCommand);
        button.setContentAreaFilled(false); // Remove the button's background
        button.setBorderPainted(false);     // Remove the button's border
        button.setFocusPainted(false);      // Remove the focus border
        button.addActionListener(new MenuButtonListener());
        return button;
    }

    private void addButtonToPanel(JButton button) {
        button.setAlignmentX(Component.RIGHT_ALIGNMENT);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(Box.createRigidArea(new Dimension(50, 0))); // Add spacing from the right edge
        buttonPanel.add(button);
        add(buttonPanel);
        add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between buttons
    }

    public void setMenuListener(MenuListener listener) {
        this.menuListener = listener;
    }

    private class MenuButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String command = source.getActionCommand();
            System.out.println("Button clicked: " + command);

            if (menuListener != null) {
                switch (command) {
                    case "Start Game":
                        menuListener.onStartGame();
                        break;
                    case "Options":
                        menuListener.onOptions();
                        break;
                    case "Exit":
                        menuListener.onExit();
                        break;
                    case "High Score":
                        menuListener.onHighScore();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + command);
                }
            }
        }
    }

    public interface MenuListener {
        void onStartGame();
        void onOptions();
        void onExit();
        void onHighScore();
    }
}