package pacmanGame.shapes;

import pacmanGame.models.BaseModel;
import pacmanGame.models.PacmanModel;

public class Pacman extends GameShape {

    private Thread animationThread;

    public Pacman(BaseModel model) {
        super(model);
        startAnimation();
    }

    private void startAnimation() {
        animationThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                ((PacmanModel) model).changeImageName();
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
    }

    public void moveTo(int startX, int startY) {
        model.moveTo(startX, startY);
    }

    public void ghostCollision() {
        ((PacmanModel) model).ghostCollision();
    }

    public void pointCollision() {
        ((PacmanModel) model).pointCollision();
    }
}