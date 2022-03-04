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

    Timer gameTimer;

    int cameraX;
    int offset;
    boolean floor = true;
    boolean left_wall = true;

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

        offset = -150; //moves the level spawn, so you don't spawn on the edge
        makeWalls(offset); //recreates the walls
    }

    public void makeWalls(int offset){
        int s = 50; //wall size
        Random random = new Random();


        if (floor == true){
            for (int i = 0; i < 20; i++) {//creates 20 walls wide to make a floor
                walls.add(new Wall(offset + 100 + i * 50, 600, s, s));
            }
            floor = false;
        }
        if (left_wall == true){
            for (int i = 0; i < 20; i++) {//creates 20 walls wide to make a floor
                walls.add(new Wall(offset + 100, 600 - i*50, s, s));
            }
            left_wall = false;
        }
    }

    public void paint(Graphics g){
        super.paint(g); //paints over the previous frame to prevent flickering

        Graphics2D gtd = (Graphics2D) g; //casts graphics to 2D graphics

        player.draw(gtd); //draws the player

        for (Wall wall : walls) wall.draw(gtd); //goes through and draws each wall in the walls arraylist
    }

    public void keyPressed(KeyEvent e) { //moves the player depending on what key is pressed
        if (e.getKeyChar() == 'a') player.keyLeft = true;
        if (e.getKeyChar() == 'w') player.keyUp = true;
        if (e.getKeyChar() == 'd') player.keyRight = true;
        if (e.getKeyChar() == 's') player.keyDown = true;
        if (e.getKeyChar() == 'i') player.keyInv = true;
    }

    public void keyReleased(KeyEvent e) {//stops the player depending on what key is released
        if (e.getKeyChar() == 'a') player.keyLeft = false;
        if (e.getKeyChar() == 'w') player.keyUp = false;
        if (e.getKeyChar() == 'd') player.keyRight = false;
        if (e.getKeyChar() == 's') player.keyDown = false;
        if (e.getKeyChar() == 'i') player.keyInv = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
