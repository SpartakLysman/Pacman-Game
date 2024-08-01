package pacmanGame.models;

import pacmanGame.Constants;
import pacmanGame.screens.gameScreen.Direction;
import pacmanGame.screens.gameScreen.GameModel;

public class PacmanModel extends BaseModel {

    private int imageNumber = 0;
    private String firstPartImageName;
    private Direction direction = Direction.RIGHT;
    private int health = 100;
    private int lives = 5;

    public PacmanModel(GameModel model) {
        super(model);
        this.firstPartImageName = resolveImageName();
        setFileName(firstPartImageName + "PACMAN_MIDDLE_MOUTH.png");
        System.out.println(getFileNameString());
        System.out.println("Pac-man model created");
    }

    public void ghostCollision() {
        if (health == 1) {
            lives--;
            health = 100;
        } else {
            health--;
        }

        if (lives == 0) {
            model.gameOver();
        }

        System.out.println("Health: " + health);
        System.out.println("Lives: " + lives);
    }

    private String resolveImageName() {
        switch (direction) {
            case UP:
                return Constants.PACMAN_UP_FILE_NAME_MOUTH;
            case DOWN:
                return Constants.PACMAN_DOWN_FILE_NAME_MOUTH;
            case LEFT:
                return Constants.PACMAN_LEFT_FILE_NAME_MOUTH;
            case RIGHT:
                return Constants.PACMAN_RIGHT_FILE_NAME_MOUTH;
        }
        return firstPartImageName;
    }

    public synchronized void changeImageName() {
        switch (imageNumber) {
            case 0 -> {
                setFileName(firstPartImageName + "PACMAN_MIDDLE_MOUTH.png");
                imageNumber = 1;
            }
            case 1 -> {
                setFileName(firstPartImageName + "PACMAN_CLOSE_MOUTH.png");
                imageNumber = 0;
            }
        }
    }

    @Override
    public int getImageSize() {
        return model.getCellSize();
    }

    @Override
    public synchronized boolean move(Direction direction) {
        this.direction = direction;
        if (!canMove()) {
            return false;
        }
        switch (direction) {
            case UP:
                ySpeed = -1;
                xSpeed = 0;
                break;
            case DOWN:
                ySpeed = 1;
                xSpeed = 0;
                break;
            case LEFT:
                ySpeed = 0;
                xSpeed = -1;
                break;
            case RIGHT:
                ySpeed = 0;
                xSpeed = 1;
                break;
            case NONE:
                return false;
        }
        x += xSpeed;
        y += ySpeed;

        firstPartImageName = resolveImageName();  // Update the first part of the image name based on the new direction
        return true;
    }

    public boolean canMove(Direction direction) {
        var isCanMove = false;
        int halfImageSize = model.getCellSize() / 2;
        int centerX = x + halfImageSize;
        int centerY = y + halfImageSize;

        switch (direction) {

            case UP, DOWN -> {
                isCanMove = !model.isWallForward(centerX - (model.getCellSize() - 1), centerY, direction) && !model.isWallForward(centerX, centerY, direction);
            }
            case LEFT, RIGHT -> {
                isCanMove = !model.isWallForward(centerX, centerY - (model.getCellSize() - 1), direction) && !model.isWallForward(centerX, centerY, direction);
            }
        }
        return isCanMove;
    }

    private boolean canMove() {
        return canMove(this.direction);
    }

    public int getHealth() {
        return health;
    }

    public int getLives() {
        return lives;
    }

    public void pointCollision() {
        health += 5;
    }
}