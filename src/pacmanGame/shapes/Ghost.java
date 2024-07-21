package pacmanGame.shapes;

import pacmanGame.models.BaseModel;
import pacmanGame.models.GhostModel;
import pacmanGame.models.PacmanModel;

import java.awt.*;
import java.util.Random;

public class Ghost extends GameShape {

    private Image tmp;
    private Thread directionThread;
    private Thread animationThread;
    static private Random randomDirection = new Random();

    public Ghost(BaseModel model) {
        super(model);
        startAnimation();
        startMoving();
    }

    private void startMoving() {
        Random random = new Random();
        directionThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                ((GhostModel) model).changeDirection(randomDirection.nextInt(4) + 1);
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
