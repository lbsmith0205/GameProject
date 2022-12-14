package Main;

import Entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Screen settings
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    final int maxScreenColumn = 15;
    final int maxScreenRow = 10;
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeight = tileSize * maxScreenRow;

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyH);

    //Creates panel for the game: sits on window in main
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000d/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {
            update();
            // Built-in function to repaint for paintComponent
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //Function to update game
    public void update() {
        player.update();
    }

    //Function to draw for that "Frame"
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        g2.dispose();
    }
}
