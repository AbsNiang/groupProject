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

    public GamePanel() {

        player = new Player(400, 300, this);

        reset(); //creates the level

        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {

            @Override
            public void run() { //this is the main loop of the game and will run each frame
                if (walls.get(walls.size() - 1).x < 800) { //check to see if eligible to make more walls
                    offset += 700; //moves the offset so the walls don't overlap
                    makeWalls(offset);
                }
                player.set();
                for (Wall wall : walls) wall.set(cameraX);
                for (int i = 0; i < walls.size(); i++) { //remove this loop to keep the prior map
                    if (walls.get(i).x < -800)
                        walls.remove(i); //Removes the walls behind the player if it generates to many
                }
                repaint();
            }
        }, 0, 17); //delay changes when the timer starts, period is how long it waits between each tick at 17 it's set to 60fps
    }

    public void reset() { //this is to respawn the player when they fall or die
        player.x = 200; //this is the location where they respawn
        player.y = 150;
        cameraX = 150;
        player.xspeed = 0; //this is so they respawn with no speed
        player.yspeed = 0;
        walls.clear(); //clears the ArrayList so the walls do not continuously overlap

        offset = -150; //moves the level spawn, so you don't spawn on the edge
        makeWalls(offset); //recreates the walls
    }

    public void makeWalls(int offset) {
        int s = 50; //wall size
        Random random = new Random();
        int index = random.nextInt(2);

        //add different indexes to make more of the map
        if (index == 0) { //index determines the level which is to be played
            for (int i = 0; i < 14; i++) walls.add(new Wall(offset + i * s, 600, s, s));
        } else { //index is always 1 if not 0 with the current random.nextInt parameters
            walls.add(new Wall(offset + 450, 550, s, s));
            walls.add(new Wall(offset + 350, 450, s, s));
            walls.add(new Wall(offset + 300, 500, s, s));
            walls.add(new Wall(offset + 100, 550, s, s));
        }
    }

    public void paint(Graphics g) {
        super.paint(g); //paints over the previous frame to prevent flickering

        Graphics2D gtd = (Graphics2D) g; //casts graphics to 2D graphics
        if (player.isMoving()) { //changes the colour of the player, depending on whether they are moving or not
            player.drawMoving(gtd);
        } else {
            player.drawStill(gtd); //draws the player
        }

        for (Wall wall : walls) wall.draw(gtd); //goes through and draws each wall in the walls arraylist
    }

    public void keyPressed(KeyEvent e) { //moves the player depending on what key is pressed
        if ((e.getKeyChar() == 'a') || (e.getKeyCode() == KeyEvent.VK_LEFT)) player.keyLeft = true;
        if ((e.getKeyChar() == 'w') || (e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_SPACE)) {
            player.keyUp = true;
        }
        if ((e.getKeyChar() == 'd') || (e.getKeyCode() == KeyEvent.VK_RIGHT)) player.keyRight = true;
        if ((e.getKeyChar() == 's') || (e.getKeyCode() == KeyEvent.VK_DOWN)) player.keyDown = true;
    }

    public void keyReleased(KeyEvent e) {//stops the player depending on what key is released
        if ((e.getKeyChar() == 'a') || (e.getKeyCode() == KeyEvent.VK_LEFT)) player.keyLeft = false;
        if ((e.getKeyChar() == 'w') || (e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_SPACE)) {
            player.keyUp = false;
        }
        if ((e.getKeyChar() == 'd') || (e.getKeyCode() == KeyEvent.VK_RIGHT)) player.keyRight = false;
        if ((e.getKeyChar() == 's') || (e.getKeyCode() == KeyEvent.VK_DOWN)) player.keyDown = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
