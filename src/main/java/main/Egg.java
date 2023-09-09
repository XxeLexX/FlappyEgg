package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Egg {
    public BufferedImage eggImage;
    public int x;
    public int y;
    public int width;
    public int height;
    // Egg size will be used in terms of collision detection
    public int sizeOfEgg;
    public double g;
    public double t;
    public double v0;
    public double speed;
    public double s;
    public BufferedImage[] eggImages;
    public int animationIndex;

    public Egg() throws Exception {
        eggImage = ImageIO.read(new File("/Users/lxx/Library/CloudStorage/Dropbox/DIYProjects/FlappyEgg/src/main/resources/egg.png"));
        width = eggImage.getWidth();
        height = eggImage.getHeight();
        x = 132;
        y = 280;
        sizeOfEgg = 40;

        g = 4; // 重力加速度
        v0 = 20; // 两次位置的时间间隔
        t = 0.25; // 初始上抛速度
        speed = v0; // 当前上抛速度
        s = 0; // 经过t以后的位移

        eggImages = new BufferedImage[8];
        for (int i = 0; i < 8; i++) {
            eggImages[i] = ImageIO.read(new File("/Users/lxx/Library/CloudStorage/Dropbox/DIYProjects/FlappyEgg/src/main/resources/EggMovement/" + i + ".png"));
        }
        animationIndex = 0;
    }

    public void step() {
        double v0 = speed;
        s = v0 * t + g * t * t/2;
        y = y - (int)s;
        speed = v0 - g * t;
    }

    public void fly() {
        animationIndex++;
        eggImage = eggImages[(animationIndex/6) % 8]; // Set frequency of animation
    }

    // Reset flying speed
    public void flappy() {
        speed = v0;
    }
}
