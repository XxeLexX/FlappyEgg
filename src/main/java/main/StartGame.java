package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class StartGame extends JPanel{
    public Egg egg;
    public Column leftColumn;
    public Column rightColumn;
    public Ground ground;
    public BufferedImage background;
    public int score;
    public GameState gameState;
    public BufferedImage gameStartImage;

    public BufferedImage gameOverImage;

    public StartGame() throws Exception {
        egg = new Egg();
        leftColumn = new Column(1);
        rightColumn = new Column(2);
        ground = new Ground();
        background = ImageIO.read(Objects.requireNonNull(getClass().getResource("/background.png")));
        gameState = GameState.START;
        gameStartImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/start.png")));
        gameOverImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/gameover.png")));
    }

    /**
     * Draw the initial interface
     * P.s. when we talk about the values of x and y of two-dimensional coordinate in computer,
     * the value of y is reversed(or upside-down) compare to the standard Cartesian coordinate system of mathematics.
     */
    @Override
    public void paint(Graphics g) {
        // draw the background
        g.drawImage(background, 0, 0, null);
        // draw the columns
        g.drawImage(leftColumn.columnImage, leftColumn.x - leftColumn.width/2, leftColumn.y - leftColumn.height/2, null);
        g.drawImage(rightColumn.columnImage, rightColumn.x - rightColumn.width/2, rightColumn.y - rightColumn.height/2, null);
        // draw the ground
        g.drawImage(ground.groundImage, ground.x, ground.y, null);
        // draw the egg
        g.drawImage(egg.eggImage, egg.x - egg.width/2, egg.y - egg.height/2, null);
        // draw the score
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 40);
        g.setFont(f);
        g.drawString("" + score, 40, 60);
        g.setColor(Color.WHITE);
        g.drawString("" + score, 40-3, 60-3);
        // draw the frame while game is over
        if (gameState == GameState.GAME_OVER) {
            g.drawImage(gameOverImage, 0, 0, null);
        }
        if (gameState == GameState.START) {
            g.drawImage(gameStartImage, 0, 0, null);
        }
    }

    /**
     * Mouse click listener
     */
    private void mouseListener() {
        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //bird.flappy();
                try {
                    switch (gameState) {
                        case GAME_OVER:
                            egg = new Egg();
                            leftColumn = new Column(1);
                            rightColumn = new Column(2);
                            score=0;
                            gameState = GameState.START;
                            break;
                        case START:
                            gameState = GameState.RUNNING;
                            break;
                        case RUNNING:
                            egg.flappy();
                    }
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
        };
        addMouseListener(mouseListener);
    }

    /**
     * Better choice: key binding
     * Use space key to control the object
     */
    private void keyListener() {
        Action spaceAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                egg.flappy();
            }
        };
        getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"space");
        getActionMap().put("space", spaceAction);

    }

    /**
     * Record the score
     */
    private void score() {
        if (egg.x == leftColumn.x || egg.x == rightColumn.x) {
            score++;
        }
    }

    public void action() throws Exception {
        while (true) {
            // if game is over, all the elements will stop refreshing
            switch (gameState) {
                case START:
                    egg.fly();
                    ground.step();
                    break;
                case RUNNING:
                    leftColumn.step();
                    rightColumn.step();
                    egg.step();
                    egg.fly();
                    ground.step();
            }

            if (egg.ifHitGround(ground) || egg.ifHitColumns(leftColumn) || egg.ifHitColumns(rightColumn)) {
                gameState = GameState.GAME_OVER;
            }

            mouseListener();
            keyListener();

            score();

            repaint();
            // Set the rate of frame-refreshing as 30 times per second
            Thread.sleep(1000/60);
        }
    }

    public static void main(String[] args) throws Exception {
        // window of operation
        JFrame frame = new JFrame();
        StartGame game = new StartGame();
        frame.add(game);
        frame.setSize(432, 644);
        frame.setResizable(false);
        // for test to show all elements, width of frame needed to set as 864
        //frame.setSize(864, 644);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        game.action();
    }
}
