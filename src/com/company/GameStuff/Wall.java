package com.company.GameStuff;

import java.awt.*;

public class Wall {

    int x;
    int y;
    int height;
    int width;
    int startX;
    Rectangle hitbox;

    public Wall(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        startX = x;

        hitbox = new Rectangle(x,y,width,height);
    }

    public void draw(Graphics2D gtd){
        gtd.setColor(Color.BLACK);
        gtd.drawRect(x,y,width,height);
        gtd.setColor(Color.WHITE);
        gtd.fillRect(x+1,y+1,width-2,height-2);
    }

    public int set(int cameraX){
        x = startX + cameraX;
        hitbox.x = x;
        return x;
    }
}
