package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;
import java.util.Random;

public class Column {
    public BufferedImage columnImage;
    public int x;
    public int y;
    public int width;
    public int height;
    // gap of two columns
    public int gap;
    // distance of two columns
    public int distance;

    public Random random = new Random();

    public Column(int n) throws Exception {
        columnImage = ImageIO.read(new File("/Users/lxx/Library/CloudStorage/Dropbox/DIYProjects/FlappyEgg/src/main/resources/column.png"));
        width = columnImage.getWidth();
        height = columnImage.getHeight();
        // the values of gap and distance decide the diffculty of game
        gap = 144;
        distance = 245;
        // 432 * 644 is the size of window (user interface)
        x = 432 + 118 + (n-1) * distance;
        /* the highest position of column is 132 and the lowest position of column is 350,
        thus the value of y should be included in this range,
        that is [0, 350-132). */
        y = random.nextInt(218) + 132;
    }
}
