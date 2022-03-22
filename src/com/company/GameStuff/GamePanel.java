package com.company.GameStuff;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends javax.swing.JPanel implements ActionListener { //this is the panel where the game is controlled

    Player player;
    ArrayList<Wall> walls = new ArrayList<>(); //stores all the walls
    ArrayList<Enemy01> enemy01s = new ArrayList<>();

    Timer gameTimer;

    int cameraX;
    int offset;

    public GamePanel(){

        player = new Player(400,300,this);

        reset(); //creates the level

        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {

            @Override
            public void run(){ //this is the main loop of the game and will run each frame
                if(walls.get(walls.size() - 1).x < 800){ //check to see if eligible to make more walls
                    offset += 700; //moves the offset so the walls don't overlap
                    makeWalls(offset);
                }
                player.set();
                for(Wall wall : walls) wall.set(cameraX);
                for(Enemy01 enemy01 : enemy01s)enemy01.set(cameraX);
                for (int i = 0; i < walls.size(); i++) { //remove this loop to keep the prior map
                    if(walls.get(i).x < -800) walls.remove(i); //Removes walls behind the player if it generates to many
                }
                repaint();
            }
        }, 0,17); //delay changes when the timer starts, period is how long it waits between each tick at 17 it is set to 60fps
    }

    public void reset(){ //this is to respawn the player when they fall or die
        player.x = 200; //this is the location where they respawn
        player.y = 150;
        cameraX = 150;
        player.xspeed = 0; //this is so they respawn with no speed
        player.yspeed = 0;
        walls.clear(); //so the walls do not continuously overlap
        enemy01s.clear();

        offset = -150; //moves the level spawn, so you don't spawn on the edge
        makeWalls(offset); //recreates the walls
    }

    public void makeWalls(int offset){
        int s = 50; //wall size
        Random random = new Random();
        int index = random.nextInt(2);

        //add different indexes to make more of the map
        if(index == 0){ //index determines the level which is to be played
            for (int i = 0; i < 14; i++) walls.add(new Wall(offset + i*s, 600 ,s ,s));
        }
        else if(index ==1){
            walls.add(new Wall(offset + 450,550,s,s));
            walls.add(new Wall(offset + 350,450,s,s));
            walls.add(new Wall(offset + 300,500,s,s));
            walls.add(new Wall(offset + 100,550,s,s));
            enemy01s.add(new Enemy01(offset + 450,500));
        }
    }

    public void paint(Graphics g){
        super.paint(g); //paints over the previous frame to prevent flickering

        Graphics2D gtd = (Graphics2D) g; //casts graphics to 2D graphics

        player.draw(gtd); //draws the player

        for (Wall wall : walls) wall.draw(gtd); //goes through and draws each wall in the walls arraylist
        for (Enemy01 enemy01 : enemy01s) enemy01.draw(gtd);
    }

    public void keyPressed(KeyEvent e) { //moves the player depending on what key is pressed
        if (e.getKeyChar() == 'a') player.keyLeft = true;
        if (e.getKeyChar() == 'w') player.keyUp = true;
        if (e.getKeyChar() == 'd') player.keyRight = true;
        if (e.getKeyChar() == 's') player.keyDown = true;
    }

    public void keyReleased(KeyEvent e) {//stops the player depending on what key is released
        if (e.getKeyChar() == 'a') player.keyLeft = false;
        if (e.getKeyChar() == 'w') player.keyUp = false;
        if (e.getKeyChar() == 'd') player.keyRight = false;
        if (e.getKeyChar() == 's') player.keyDown = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
