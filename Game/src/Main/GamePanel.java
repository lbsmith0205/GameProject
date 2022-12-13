package Main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Screen settings
    final int originalTileSize = 16;
    final int scale = 4;

    final int tileSize = originalTileSize * scale;
    final int maxScreenColumn = 15;
    final int maxScreenRow = 10;
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeight = tileSize * maxScreenRow;

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //set players default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

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

    public void update() {
        if(keyH.upPressed){
            playerY -= playerSpeed;
        } else if(keyH.leftPressed){
            playerX -= playerSpeed;
        }else if(keyH.downPressed){
            playerY += playerSpeed;
        }else if(keyH.rightPressed){
            playerX += playerSpeed;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.red);
        g2.fillRect(playerX,playerY,tileSize,tileSize);
        g2.dispose();
    }
}
