package pacmanGame.screens.gameScreen;

import pacmanGame.shapes.GameShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class FieldPanel extends JPanel implements KeyListener, Runnable {

    private int width;
    private int height;
    private GameModel model;
    private Direction direction;
    private Direction newDirection;
    private JButton backButton;

    public FieldPanel(int width, int height) {
        this.width = width;
        this.height = height;
        direction = Direction.NONE;
        newDirection= Direction.NONE;
        setFocusable(true); // Робимо компонент спрямованим на отримання фокусу, щоб слухати події клавіатури
        addKeyListener(this);
        backButton = new JButton("menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToMenu();
            }
        });
        setLayout(null);
        backButton.setBounds(width / 2 - 60, height - 25, 120, 30);
        add(backButton);
    }

    public void checkCollision() {
       model.checkCollision();
    }

    private void returnToMenu() {
        // Notify the frame to return to the menu
        Container parent = this.getParent();
        while (!(parent instanceof GameScreenFrame)) {
            parent = parent.getParent();
        }
        ((GameScreenFrame) parent).showMenuScreen();
    }

    public void setModel(GameModel model) {
        this.model = model;
        Thread animationThread = new Thread(this);
        animationThread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (GameShape shape : model.getShapes()) {
            drawImage(g, shape);
        }
    }

    private void drawImage(Graphics g, GameShape shape) {
        g.drawImage(shape.getImage(), shape.getX(), shape.getY(), this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
      //  var oldDirection = direction;
        int keyCode = e.getKeyCode();
        System.out.println(keyCode);
        if (keyCode == KeyEvent.VK_UP) {
            newDirection = Direction.UP;
            System.out.println("UP");
        } else if (keyCode == KeyEvent.VK_DOWN) {
            newDirection = Direction.DOWN;
            System.out.println("DOWN");
        } else if (keyCode == KeyEvent.VK_LEFT) {
            newDirection = Direction.LEFT;
            System.out.println("LEFT");
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            newDirection = Direction.RIGHT;
            System.out.println("RIGHT");
        }
//        if (!model.isPacmanCanMove(direction)) {
//            direction = oldDirection;
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        newDirection = direction;
    }

    @Override
    public void run() {
        while (true) {
//            System.out.println("x=" + x + " y=" + y);
            for (var shape : model.getShapes()) {
               if (shape.move(newDirection)) {
                   direction = newDirection;
               } else {
                   shape.move(direction);
               };
            }
            checkCollision();
            repaint();
            try {
                Thread.sleep(20); // Додамо трошки затримки для зменшення швидкості руху
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getHeight() {
        return height;
    }
}
