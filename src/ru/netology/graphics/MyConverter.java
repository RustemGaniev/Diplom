package ru.netology.graphics;

import ru.netology.graphics.image.BadImageSizeException;
import ru.netology.graphics.image.TextColorSchema;
import ru.netology.graphics.image.TextGraphicsConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class MyConverter implements TextGraphicsConverter {

    double maxRatio;
    int maxWidth = 200;
    int maxHeight = 300;
    int newWidth;
    int newHeight;
    String s;

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        int width = img.getWidth();
        int height = img.getHeight();
        double actualRatio = width / height;
        if (actualRatio > maxRatio) {

            throw new BadImageSizeException(actualRatio, maxRatio);
        }
        if (width > maxWidth || height > maxHeight) {

            if ((width / maxWidth) > (height / maxHeight)) {
                newWidth = maxWidth;
                newHeight = (int) (height * maxWidth / width);

            } else {
                newHeight = maxHeight;
                newWidth = (int) (width * maxHeight / height);
            }


        } else {
            newHeight = height;
            newWidth = width;
        }
        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        WritableRaster bwRaster = bwImg.getRaster();
        TextColorSchema schema = new MyConvert();
        char[][] textImage = new char[newHeight][newWidth];
        for (int i = 0; i < newHeight; i++) {
            s = s + "\n";
            for (int j = 0; j < newWidth; j++) {
                int color = bwRaster.getPixel(i, j, new int[3])[0];
                textImage[i][j] = schema.convert(color);
                s = s + String.valueOf(textImage[i][j]);
            }
        }
        return s;
    }

    @Override
    public void setMaxWidth(int width) {
        int maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        int maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {

    }
}
