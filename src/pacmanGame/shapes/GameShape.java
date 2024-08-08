package pacmanGame.shapes;

import pacmanGame.models.BaseModel;
import pacmanGame.screens.gameScreen.Direction;

import javax.swing.*;
import java.awt.*;


import static helpers.ImageHelper.scaleImage;

public class GameShape { //configure with model. configurable interface.->baseModel

    protected Image image;
    protected BaseModel model;

    public GameShape(BaseModel model) {
        this.model = model;
        System.out.println("Image Name: " + model.getFileName() + ", Image Size: " + model.getImageSize());
        image = scaleImage(new ImageIcon(model.getFileName().get()).getImage(), model.getImageSize());
        System.out.println("Image: " + image);
    }

    public Image getImage() {
        return image;
    }

    public void setImage() {
        image = scaleImage(new ImageIcon(model.getFileName().get()).getImage(), model.getImageSize());
    }

    public int getX() {
        return model.getX();
    }

    public int getY() {
        return model.getY();
    }

    public boolean move(Direction direction) {
        return model.move(direction);
    }
}