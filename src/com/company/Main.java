package com.company;

import com.company.GameStuff.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setSize(900, 700); //sets the size of the window
        frame.setLocationRelativeTo(null);//Sets the window to the center of the screen
        frame.setResizable(false); //allows the user to resize the window
        frame.setTitle("2D Platformer Game");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//closes the whole program when  window is closed
    }
}
