package main;

import javax.imageio.ImageIO;
import javax.swing.*;
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

    public static void main(String[] args) throws Exception {
        // window of operation
        JFrame frame = new JFrame();
        StartGame game = new StartGame();
        frame.add(game);
        frame.setSize(432, 644); // for test need to set width as 864
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
