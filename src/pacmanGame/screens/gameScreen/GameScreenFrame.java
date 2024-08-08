package pacmanGame.screens.gameScreen;

import pacmanGame.Constants;
import pacmanGame.screens.menuScreen.MenuModel;
import pacmanGame.screens.menuScreen.MenuPanel;

import javax.swing.*;
import java.awt.*;
//


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreenFrame extends JFrame {
    private FieldPanel fieldPanel;
    private ScorePanel scorePanel;
    private HealthPanel healthPanel;
    private TimePanel timePanel;
    private MenuPanel menuScreen;
    private GameModel model;
    private int cellSize = Constants.DEFAULT_CELL_SIZE;
    private final int scorePanelSize = 40;
    private final int healthPanelSize = 40;
    private final int borderSize = 25;


    public GameScreenFrame() {
        initialize();
        showMenuScreen();
    }

    private void initialize() {
        setTitle("Pac-Man");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    void showMenuScreen() {
        String title = "Pac-Man Menu";
        String[] options = {"Start Game", "Options", "Exit"};
        MenuModel menuModel = new MenuModel(title, options);
        menuScreen = new MenuPanel(menuModel);
        menuScreen.setMenuListener(new MenuPanel.MenuListener() {
            @Override
            public void onStartGame() {
                chooseBoardSize();
            }

            @Override
            public void onOptions() {
                // Handle options
                System.out.println("Options clicked");
            }

            @Override
            public void onExit() {
                System.exit(0);
            }

            @Override
            public void onHighScore() {
                System.exit(0); //TODO: implement high scores counter and saver
            }
        });

        getContentPane().removeAll();
        getContentPane().add(menuScreen, BorderLayout.CENTER);
        revalidate();
        repaint();
        setVisible(true);
    }

    private void chooseBoardSize() {
        String[] options = {"20", "30", "40", "50", "60"};
        int choice = JOptionPane.showOptionDialog(this, "Choose Board Size", "Board Size", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice != JOptionPane.CLOSED_OPTION) {
            int mapWidth = Integer.parseInt(options[choice]) + 1;
            int mapHeight = mapWidth; // Adjust the height according to the width

            fieldPanel = new FieldPanel(mapWidth * cellSize, mapHeight * cellSize);
            model = new GameModel(fieldPanel, mapWidth, mapHeight, cellSize);
            fieldPanel.setModel(model);
            createScorePanel();
            createHealthPanel();

            startGame(mapWidth, mapHeight);
        }
    }

    private void startGame(int mapWidth, int mapHeight) {
        remove(menuScreen);
        int windowHeight = mapHeight * cellSize + scorePanelSize + healthPanelSize + borderSize;
        setSize(mapWidth * cellSize, windowHeight);

        getContentPane().removeAll();
        invalidate();
        revalidate();
        repaint();

        // Delay for 2 seconds before showing the game field
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanels();
                ((Timer) e.getSource()).stop(); // Stop the timer after one execution
            }
        });
        timer.setRepeats(false); // Execute the action only once
        timer.start();
    }

    private void showPanels() {
        getContentPane().removeAll();
        getContentPane().add(healthPanel, BorderLayout.SOUTH);
        getContentPane().add(fieldPanel, BorderLayout.CENTER);
        getContentPane().add(scorePanel, BorderLayout.NORTH);

        revalidate();
        repaint();
        fieldPanel.requestFocusInWindow();
        setVisible(true);
    }

    private void createHealthPanel() {
        healthPanel = new HealthPanel(fieldPanel.getWidth(), 40);
//        healthPanel.setBackground(Color.orange);
    }

    private void createScorePanel() {
        scorePanel = new ScorePanel(fieldPanel.getWidth(), 40);
        scorePanel.setBackground(Color.black);
    }
}