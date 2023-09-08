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
    }

    public void step() {
        double v0 = speed;
        s = v0 * t + g * t * t/2;
        y = y - (int)s;
        double v = v0 - g * t;
        speed = v;
    }
}
