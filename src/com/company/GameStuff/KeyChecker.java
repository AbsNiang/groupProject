package com.company.GameStuff;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyChecker extends KeyAdapter { //KeyAdapter means it is listening for key inputs

    GamePanel panel;

    public KeyChecker(GamePanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e) { //sends when a key is pressed to the panel
        panel.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) { //sends when a key is released to the panel
        panel.keyReleased(e);
    }
}