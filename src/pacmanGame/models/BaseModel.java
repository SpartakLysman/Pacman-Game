package pacmanGame.models;

import pacmanGame.screens.gameScreen.Direction;
import pacmanGame.screens.gameScreen.GameModel;

import java.util.concurrent.atomic.AtomicReference;

public class BaseModel { //coordinates, cells, rows, directions, get, set. Movable interface implemented by pacman and ghost

    protected GameModel model;
    protected AtomicReference<String> fileName = new AtomicReference<>("");
    protected int x = 0;
    protected int y = 0;
    protected int xSpeed = 0;
    protected int ySpeed = 0;

    public BaseModel(GameModel model) {
        this.model = model;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveDown() {

    }

    public void setSpeed(int xSpeed, int ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public int getImageSize() {
        return 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public AtomicReference<String> getFileName() {
        return fileName;
    }

    public synchronized String getFileNameString() {
        return fileName.get();
    }

    public synchronized void setFileName(String fileName) {
        this.fileName.set(fileName);
    }

    public boolean move(Direction direction) {
        return false;
    }
}
