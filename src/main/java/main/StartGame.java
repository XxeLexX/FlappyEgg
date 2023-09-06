package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class StartGame extends JPanel{
    public Egg egg;
    public Column leftColumn;
    public Column rightColumn;
    public Ground ground;
    public BufferedImage background;

    public StartGame() throws Exception {
        egg = new Egg();
        leftColumn = new Column(1);
        rightColumn = new Column(2);
        ground = new Ground();
        background = ImageIO.read(new File("/Users/lxx/Library/CloudStorage/Dropbox/DIYProjects/FlappyEgg/src/main/resources/background.png"));
    }

    // Draw the initial interface
    // P.s. when we talk about the values of x and y of 2-dimensional coordinate in computer, the value of y is reversed compare to the math.
    public void paint(Graphics g) {
        // draw the background
        g.drawImage(background, 0, 0, null);
        // draw the columns
        g.drawImage(leftColumn.columnImage, leftColumn.x - leftColumn.width/2, leftColumn.y - leftColumn.height/2, null);
        g.drawImage(rightColumn.columnImage, rightColumn.x - rightColumn.width/2, rightColumn.y - rightColumn.height/2, null);
        //System.out.println("x = " + leftColumn.x + ", y = " + leftColumn.y);
        //System.out.println("Position: " + (rightColumn.x - rightColumn.width/2) + ", " + (rightColumn.y - rightColumn.height/2));

        // draw the gound
        g.drawImage(ground.groundImage, ground.x, ground.y, null);
        // draw the egg
        g.drawImage(egg.eggImage, egg.x - egg.width/2, egg.y - egg.height/2, null);
    }

    public static void main(String[] args) throws Exception {
        // window of operation
        JFrame frame = new JFrame();
        StartGame game = new StartGame();
        frame.add(game);
        //frame.setSize(432, 644);
        // for test to show all elements, width of frame needed to set as 864
        frame.setSize(864, 644);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
