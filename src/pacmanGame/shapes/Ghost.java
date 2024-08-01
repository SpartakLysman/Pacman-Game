package pacmanGame.shapes;

import pacmanGame.models.BaseModel;
import pacmanGame.models.GhostModel;
import pacmanGame.models.PacmanModel;
import pacmanGame.screens.gameScreen.Direction;

import java.awt.*;
import java.util.Random;

public class Ghost extends GameShape {

    private Image tmp;
    private Thread directionThread;
    private Thread animationThread;
    static private Random random = new Random();

    private Direction randomDirection() {
        int newDirection = random.nextInt(4) + 1;

        switch (newDirection) {
            case 1 -> {
                return Direction.UP;
            }
            case 2 -> {
                return Direction.DOWN;
            }
            case 3 -> {
                return Direction.LEFT;
            }
            case 4 -> {
                return Direction.RIGHT;
            }
        }
        return Direction.NONE;
    }

    public Ghost(BaseModel model) {
        super(model);
        startAnimation();
        startMoving();
    }

    private void startMoving() {
        Random random = new Random();
        directionThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                Direction direction;
                do {
                    direction = randomDirection();
                } while (!((GhostModel) model).canMove(direction));

                ((GhostModel) model).changeDirection(direction);
                try {
                    int delay = random.nextInt(2000) + 1000;
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();  // Restore interrupted status
                }
            }
        });
        directionThread.start();
    }

    private void startAnimation() {
        animationThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                ((GhostModel) model).changeImageName();
                setImage();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();  // Restore interrupted status
                }
            }
        });
        animationThread.start();
    }

    public void stop() {
        if (animationThread != null && animationThread.isAlive()) {
            animationThread.interrupt();
        }

        if (directionThread != null && directionThread.isAlive()) {
            directionThread.interrupt();
        }
    }
}
