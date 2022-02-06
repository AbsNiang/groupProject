package com.company;

import com.company.GameStuff.*;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();

        frame.setSize(700,700); //sets the size of the window

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Gets the screen size of the current computer
        frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2), (int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2)); //Sets the window to the center of the screen

        frame.setResizable(false); //allows the user to resize the window
        frame.setTitle("2D Platformer Game");
        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}