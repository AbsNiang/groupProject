package com.company.GameStuff;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends javax.swing.JPanel implements ActionListener {

    Player player;
    ArrayList<Wall> walls = new ArrayList<>(); //stores all the walls

    Timer gameTimer;

    public GamePanel(){

        player = new Player(400,300,this);

        makeWalls();

        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run(){ //this is the main loop of the game and will run each frame
                player.set();
                repaint();
            }
        }, 0,17); //delay changes when the timer starts, period is how long it waits between each tick at 17 it is set to 60fps
    }

    public void makeWalls(){
        for (int i = 50; i < 650; i+= 50) { //creates the floor
            walls.add(new Wall(i, 600, 50, 50));
        }
        for (int i = 450; i < 600; i+=50) { //creates the boarder walls
            walls.add(new Wall(50,i,50,50));
            walls.add(new Wall(600,i,50,50));
        }
        walls.add(new Wall(450,550,50,50)); //adds the wall in the middle

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
