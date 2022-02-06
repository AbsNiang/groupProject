package com.company.GameStuff;

import java.awt.*;

public class Wall {

    int x;
    int y;
    int height;
    int width;

    Rectangle hitBox;

    public Wall(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;

        hitBox = new Rectangle(x,y,width,height);
    }

    public void draw(Graphics2D gtd){
        gtd.setColor(Color.BLACK);
        gtd.drawRect(x,y,width,height);
        gtd.setColor(Color.WHITE);
        gtd.fillRect(x+1,y+1,width-2,height-2);
    }
}
