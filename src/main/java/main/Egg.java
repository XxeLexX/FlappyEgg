package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

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
        eggImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/egg.png")));
        width = eggImage.getWidth();
        height = eggImage.getHeight();
        x = 132;
        y = 280;
        sizeOfEgg = 40;

        g = 5; // 重力加速度
        v0 = 20; // 两次位置的时间间隔
        t = 0.25; // 初始上抛速度
        speed = v0; // 当前上抛速度
        s = 0; // 经过t以后的位移

        eggImages = new BufferedImage[8];
        for (int i = 0; i < 8; i++) {
            eggImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/EggMovement/" + i + ".png")));
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
        eggImage = eggImages[(animationIndex/12) % 8]; // Set frequency of animation
    }

    // Reset flying speed
    public void flappy() {
        speed = v0;
    }

    public boolean ifHitGround(Ground ground) {
        if (y + sizeOfEgg/2 >= ground.y) {
            // if hit, put the egg on the ground, lmao
            y = ground.y - sizeOfEgg/2;
            return true;
        }
        return false;
    }

    public boolean ifHitColumns(Column column) {
        /* 先检测是否在柱子的范围以内(柱子外侧) */
        if (x > column.x - column.width / 2 - sizeOfEgg / 2 && x < column.x + column.width / 2 + sizeOfEgg / 2) {
            /* 检测是否在柱子缝隙中 */
            return y <= column.y - column.gap / 2 + sizeOfEgg / 2 || y >= column.y + column.gap / 2 - sizeOfEgg / 2;
        }
        return false;
    }
}
