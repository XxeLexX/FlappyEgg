package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Ground {
    public BufferedImage groundImage;
    public int x;
    public int y;
    public int width;
    public int height;

    public Ground() throws Exception {
        groundImage = ImageIO.read(new File("/Users/lxx/Library/CloudStorage/Dropbox/DIYProjects/FlappyEgg/src/main/resources/ground.png"));
        width = groundImage.getWidth();
        height = groundImage.getHeight();
        x = 0;
        y = 500;
    }

    // Ground movement
    public void step() {
        x--;
        // 432/4 = 108, in the next frame, which is 109, reset ground's position
        if (x == -109) {
            x = 0;
        }
    }
}
