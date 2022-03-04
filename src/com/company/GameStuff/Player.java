package com.company.GameStuff;

import com.company.GameStuff.GamePanel;

import java.awt.*;

import static java.lang.Math.signum;

public class Player {

    GamePanel panel;

    int x;
    int y;
    int width;
    int height;

    double xspeed; //horizontal velocity
    double yspeed; //vertical velocity

    Rectangle hitBox;

    boolean keyLeft;
    boolean keyRight;
    boolean keyUp;
    boolean keyDown;
    boolean keyInv;

    public Player(int x, int y, GamePanel panel){ //x & y in this line are the players starting pos
        this.panel = panel;
        this.x = x;
        this.y = y;

        width = 50; //width of the player
        height = 100; //height of the player
        hitBox = new Rectangle(x,y,width,height); //sets the players hitBox to the players pos and size
    }

    public void set(){ //runs every frame of the game
        if (keyLeft && keyRight || !keyLeft && !keyRight) xspeed *= 0.8; //stops the player moving if both directional keys or no keys are pressed, the deceleration speed
        else if(keyLeft && !keyRight) xspeed --;//moves the player depending on what keys are pressed
        else if(keyRight && !keyLeft) xspeed ++;

        if(xspeed > 0 && xspeed < 0.75) xspeed = 0; //stops the player moving if the player is moving really slowly
        if(xspeed < 0 && xspeed > -0.75) xspeed = 0;

        if(xspeed > 7) xspeed = 7; //sets a maximum speed for the player
        if(xspeed < -7) xspeed = -7;

        if(keyUp){
            //checking when the player can jump by checking if one pixel below the player is in a wall
            hitBox.y ++; //1 pixel below the player
            for(Wall wall:panel.walls){ //for every wall within the walls arraylist
                if(wall.hitBox.intersects(hitBox)) yspeed = -6; //sets if the player is touching the floor then it's allowed to jump, the speed at which the player jumps
            }
            hitBox.y --;//sets the hitBox back to its correct alignment
        }

        yspeed += 0.3; //this is the speed at which gravity acts on the player

        //horizontal collisions
        hitBox.x += xspeed;
        for(Wall wall:panel.walls){ //checking for all walls
            if(hitBox.intersects(wall.hitBox)){ //if the hitBox hits a wall
                hitBox.x -= xspeed;
                while(!wall.hitBox.intersects(hitBox)) hitBox.x += Math.signum(xspeed); //signum takes the value of it being + or -, allows the player to get as close to the wall without going through it
                hitBox.x -= Math.signum(xspeed);
                panel.cameraX += x - hitBox.x; //corrects for player x value changing when hitting a wall
                xspeed = 0;
                hitBox.x = x;
            }
        }

        //vertical collisions
        hitBox.y += yspeed;
        for(Wall wall:panel.walls){ //checking for all walls
            if(hitBox.intersects(wall.hitBox)){ //if the hitBox hits a wall
                hitBox.y -= yspeed;
                while(!wall.hitBox.intersects(hitBox)) hitBox.y += Math.signum(yspeed); //signum takes the value of it being + or -, allows the player to get as close to the wall without going through it
                hitBox.y -= Math.signum(yspeed);
                yspeed = 0;
                y = hitBox.y;
            }
        }

        panel.cameraX -= xspeed; //allows for player movement
        y += yspeed;

        hitBox.x = x; //moves the hitBox with the player
        hitBox.y = y;

        //death code
        if (y> 800) panel.reset(); //if the player goes below this amount they respawn

    }

    public void draw(Graphics2D gtd){
        gtd.setColor(Color.BLACK); //sets the colour of the player
        gtd.fillRect(x,y,width,height); //fills in the player, so it is not an outline of a rectangle
    }
}
