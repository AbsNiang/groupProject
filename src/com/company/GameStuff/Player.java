package com.company.GameStuff;

import java.awt.*;

public class Player {

    GamePanel panel;
    int x;
    int y;
    int width;
    int height;
    double xspeed; //horizontal velocity
    double yspeed; //vertical velocity
    Rectangle hitbox;//used for collision checking
    boolean keyLeft;
    boolean keyRight;
    boolean keyUp;
    boolean keyDown;

    public Player(int x, int y, GamePanel panel) { //x & y in this line are the players starting pos
        this.panel = panel;
        this.x = x;
        this.y = y;

        width = 50; //width of the player
        height = 100; //height of the player
        hitbox = new Rectangle(x, y, width, height); //sets the players hit-box to the players pos and size
    }

    public void set() { //runs every frame of the game
        if (keyLeft && keyRight || !keyLeft && !keyRight)
            xspeed *= 0.8; //stops the player moving if both directional keys or no keys are pressed, the deceleration speed
        else if (keyLeft) xspeed--;//moves the player depending on what keys are pressed
        else xspeed++;

        if (xspeed > 0 && xspeed < 0.75) xspeed = 0; //stops the player moving if the player is moving really slowly
        if (xspeed < 0 && xspeed > -0.75) xspeed = 0;

        if (xspeed > 7) xspeed = 7; //sets a maximum speed for the player
        if (xspeed < -7) xspeed = -7;

        if (keyUp) {
            //checking when the player can jump by checking if one pixel below the player is in a wall
            hitbox.y++; //1 pixel below the player
            for (Wall wall : panel.walls) { //for every wall within the walls arraylist
                if (wall.hitbox.intersects(hitbox))
                    yspeed = -6; //sets if the player is touching the floor then it's allowed to jump, the speed at which the player jumps
            }
            hitbox.y--;//sets the hit-box back to its correct alignment
        }
        yspeed += 0.3; //this is the speed at which gravity acts on the player
        //horizontal collisions
        hitbox.x += xspeed;
        for (Wall wall : panel.walls) { //checking for all walls
            if (hitbox.intersects(wall.hitbox)) { //if the hit-box hits a wall
                hitbox.x -= xspeed;
                while (!wall.hitbox.intersects(hitbox))
                    hitbox.x += Math.signum(xspeed); //signum takes the value of it being + or -, allows the player to get as close to the wall without going through it
                hitbox.x -= Math.signum(xspeed);
                panel.cameraX += x - hitbox.x; //corrects for player x value changing when hitting a wall
                xspeed = 0;
                hitbox.x = x;
            }
        }
        //vertical collisions
        hitbox.y += yspeed;
        for (Wall wall : panel.walls) { //checking for all walls
            if (hitbox.intersects(wall.hitbox)) { //if the hit-box hits a wall
                hitbox.y -= yspeed;
                while (!wall.hitbox.intersects(hitbox))
                    hitbox.y += Math.signum(yspeed); //'signum' takes the value of it being + or -, allows the player to get as close to the wall without going through it
                hitbox.y -= Math.signum(yspeed);
                yspeed = 0;
                y = hitbox.y;
            }
        }
        panel.cameraX -= xspeed; //allows for player movement
        y += yspeed;
        hitbox.x = x; //moves the hit-box with the player
        hitbox.y = y;

        //death code
        if (y > 800) panel.reset(); //if the player goes below this amount they respawn
    }

    public boolean isMoving() { //checks if the player is moving horizontally to add an animation (represented by a change in colours for now)
        if (keyLeft && keyRight || !keyLeft && !keyRight) {
            return false;
        }
        return true;
    }

    public void drawStill(Graphics2D gtd) {
        gtd.setColor(Color.BLACK); //sets the colour of the player for when the player is still
        gtd.fillRect(x, y, width, height); //fills in the player, so it is not an outline of a rectangle
    }

    public void drawMoving(Graphics2D gtd) {
        gtd.setColor(Color.RED); //sets the colour of the player differently to drawStill()
        gtd.fillRect(x, y, width, height); //fills in the rectangle
    }
}
