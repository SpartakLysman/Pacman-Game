package pacmanGame.shapes;

import pacmanGame.models.BaseModel;
import pacmanGame.screens.gameScreen.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameShape { //configure with model. configurable interface.->baseModel

    protected Image image;
    //public GameModel gameModel;
    protected BaseModel model;

    public GameShape(BaseModel model) {
        this.model = model;
        image = scaleImage(new ImageIcon(model.getFileName().get()).getImage(), model.getImageSize());
        //System.out.println("Image= " + image);
    }

    protected Image scaleImage(Image image, int imageSize) {
        //System.out.println("Image= " + image);
        BufferedImage resizedImage = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_ARGB);
        // Малюємо масштабоване зображення у новий BufferedImage
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(image, 0, 0, imageSize, imageSize, null);
        g2d.dispose();

        return resizedImage;
    }

    public Image getImage() {
        return image;
    }

    public void setImage() {
        image = scaleImage(new ImageIcon(model.getFileName().get()).getImage(), model.getImageSize());
        System.out.println("Model file name: " + model.getFileName().get());
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
