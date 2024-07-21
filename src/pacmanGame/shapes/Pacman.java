package pacmanGame.shapes;

import pacmanGame.models.BaseModel;
import pacmanGame.models.PacmanModel;
import pacmanGame.screens.gameScreen.Direction;

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

//    @Override
//    public void setImage() {
//        super.setImage();  // Call the parent's setImage to ensure proper functionality
//    }

//    public boolean isCanMove(Direction direction) {
//       return ((PacmanModel)model).canMove(direction);
//    }

    public void moveTo(int startX, int startY) {
        model.moveTo(startX, startY);
    }
}