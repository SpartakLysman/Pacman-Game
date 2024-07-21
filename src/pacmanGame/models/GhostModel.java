package pacmanGame.models;

import pacmanGame.Constants;
import pacmanGame.screens.gameScreen.Direction;
import pacmanGame.screens.gameScreen.GameModel;

import java.util.Random;

public class GhostModel extends BaseModel {

    private String color = "orange";
    private int imageNumber = 0;
    private String firstPartImageName;
    private Direction direction = Direction.RIGHT;

    public GhostModel(GameModel model) {
        super(model);
        String imageName = resolveImageName();
        setFileName(imageName);
        initPosition();
    }

    public void initPosition() {
        Random random = new Random();
        int xCell;
        int yCell;
        do {
            xCell = random.nextInt(model.getMapWidth());
            yCell = random.nextInt(model.getMapHeight());
        } while (!model.isFreeSpace(xCell, yCell));

        moveTo(xCell * model.getCellSize(), yCell * model.getCellSize());
        System.out.println("Ghost: " + xCell + ", " + yCell);
    }

    private String resolveImageName() {
        switch (direction) {
            case UP:
                return Constants.GHOST_UP_FILE_NAME_MOUTH;
            case DOWN:
                return Constants.GHOST_DOWN_FILE_NAME_MOUTH;
            case LEFT:
                return Constants.GHOST_LEFT_FILE_NAME_MOUTH;
            case RIGHT:
                return Constants.GHOST_RIGHT_FILE_NAME_MOUTH;
        }
        return firstPartImageName;
    }


    @Override
    public int getImageSize() {
        return model.getCellSize();
    }

    @Override
    public synchronized boolean move(Direction direction) {
        //this.direction = direction;
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

    public void changeImageName() {
        setFileName(firstPartImageName + "/" + color + "/GHOST.png");
    }

    public void changeDirection(int num) {
        switch (num) {
            case 0 -> {direction = Direction.UP; }
            case 1 -> {direction = Direction.DOWN; }
            case 2 -> {direction = Direction.LEFT; }
            case 3 -> {direction = Direction.RIGHT; }
        }
    }
}
