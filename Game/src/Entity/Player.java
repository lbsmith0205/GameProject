package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
    }

    // Default position and speed for Player
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    // Update function for key press and interactions for player
    public void update(){
        if(keyH.upPressed){
            y -= speed;
        } else if(keyH.leftPressed){
            x -= speed;
        }else if(keyH.downPressed){
            y += speed;
        }else if(keyH.rightPressed){
            x += speed;
        }
    }

    // draw function to declare what is being drawn
    public void draw(Graphics2D g2){
        g2.setColor(Color.red);
        g2.fillRect(x,y,gp.tileSize,gp.tileSize);
    }
}
