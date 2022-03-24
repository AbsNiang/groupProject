package com.company.GameStuff;

import javax.swing.*;
import java.awt.*;

public class Enemy01 {

    GamePanel panel;

    int x;
    int y;
    int width;
    int height;
    int startX;

    double xspeed; //horizontal velocity
    double yspeed; //vertical velocity

    Rectangle hitBox;
    Rectangle invMenu;
    private Image image;

    public Image getImage() {
        return image;
    }

    public Enemy01(int x, int y, GamePanel panel) {
        this.panel = panel;
        this.x = x;
        this.y = y;

        startX = x;
        width = 50;
        height = 50;
        hitBox = new Rectangle(x,y,width,height);
        invMenu = new Rectangle(x,y,width,height);
    }

    public void set(){
        /*
        x = startX + cameraX;
        yspeed += 0.1; //this is the speed at which gravity acts on the player
        xspeed += 0.3;
        if(xspeed > 0 && xspeed < 0.75) xspeed = 0; //stops the player moving if the player is moving really slowly
        if(xspeed < 0 && xspeed > -0.75) xspeed = 0;

        if(xspeed > 3) xspeed = 3; //sets a maximum speed for the player
        if(xspeed < -3) xspeed = -3;

        //vertical collisions
        hitBox.y += yspeed;
        hitBox.x += xspeed;
        for(Wall wall:panel.walls){ //checking for all walls
            if(hitBox.intersects(wall.hitBox)){ //if the hitBox hits a wall
                hitBox.y -= yspeed;
                while(!wall.hitBox.intersects(hitBox)) hitBox.y += Math.signum(yspeed); //signum takes the value of it being + or -, allows the player to get as close to the wall without going through it
                hitBox.y -= Math.signum(yspeed);
                yspeed = 0;
                y = hitBox.y;
            }
        }
        x += xspeed;
        y += yspeed;

        hitBox.x = x; //moves the hitBox with the player
        hitBox.y = y;

         */
    }

    public void draw(Graphics2D gtd){
        //gtd.setColor(Color.BLACK); //sets the colour of the player
        //gtd.fillRect(x,y,width,height); //fills in the player, so it is not an outline of a rectangle
        if (xspeed >=0) {
            loadPlayerSprite("src/com/company/SpriteStorage/SlimeEnemy01.gif");
        }
        if (xspeed <0){
            loadPlayerSprite("src/com/company/SpriteStorage/SlimeEnemy01.gif");
        }
        gtd.drawImage(getImage(), x, y, null);
    }

    public void loadPlayerSprite(String imageLocation){
        ImageIcon ii = new ImageIcon(imageLocation);
        image = ii.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);
    }


    public int set(int cameraX){
        x = startX + cameraX;
        x -= xspeed;
        yspeed += 0.3;
        y += yspeed;
        for(Wall wall:panel.walls){ //checking for all walls
            if(hitBox.intersects(wall.hitBox)){ //if the hitBox hits a wall
                hitBox.y -= yspeed;
                while(!wall.hitBox.intersects(hitBox)) hitBox.y += Math.signum(yspeed); //signum takes the value of it being + or -, allows the player to get as close to the wall without going through it
                hitBox.y -= Math.signum(yspeed);
                yspeed = 0;
                y = hitBox.y;
            }
        }
        hitBox.x = x;
        hitBox.y = y;
        return x;
    }


}
