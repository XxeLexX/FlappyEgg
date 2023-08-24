package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

public class Ground {
    public BufferedImage groundImage;
    public int x;
    public int y;
    public int width;
    public int height;

    public Ground() throws Exception {
        groundImage = ImageIO.read(new File("ground.png"));
        width = groundImage.getWidth();
        height = groundImage.getHeight();
        x = 0;
        y = 500;
    }
}
