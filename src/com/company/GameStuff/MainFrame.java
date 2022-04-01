package com.company.GameStuff;

import java.awt.*;

public class MainFrame extends javax.swing.JFrame{

    public MainFrame(){

        GamePanel panel = new GamePanel();
        panel.setLocation(0,0);
        panel.setSize(this.getSize());
        panel.setBackground(Color.CYAN);
        panel.setVisible(true);
        this.add(panel);
        addKeyListener(new KeyChecker(panel)); //allows the panel to 'listen' for key inputs
    }

}
