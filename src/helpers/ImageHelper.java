package helpers;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageHelper {

    public static Image scaleImage(Image image, int imageSize) {
        return scaleImage(image, imageSize, imageSize);
    }

    public static Image scaleImage(Image image, int imageHeight, int imageWidth) {
        //System.out.println("Image= " + image);
        BufferedImage resizedImage = new BufferedImage(imageHeight, imageWidth, BufferedImage.TYPE_INT_ARGB);
        // Малюємо масштабоване зображення у новий BufferedImage
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(image, 0, 0, imageHeight, imageWidth, null);
        g2d.dispose();

        return resizedImage;
    }
}
