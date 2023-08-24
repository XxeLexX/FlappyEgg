package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

public class Egg {
    public BufferedImage eggImage;
    public int x;
    public int y;
    public int width;
    public int height;
    // Egg size will be used in terms of collision detection
    public int sizeOfEgg;

    public Egg() throws Exception {
        eggImage = ImageIO.read(new File("egg.png"));
        width = eggImage.getWidth();
        height = eggImage.getHeight();
        x = 132;
        y = 280;
        sizeOfEgg = 40;
    }
}
