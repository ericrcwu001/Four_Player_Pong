package com.eric.four_player_pong;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Racket extends Rectangle {
    int id, yS = 0, xS = 0;
    int speed = 10;

    Racket(int x, int y, int width, int height, int id) {
        super(x, y, width, height);
        this.id = id;
    }

    public void keyPressed(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_Q) {
                    setSpeed(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    setSpeed(speed);
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setSpeed(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setSpeed(speed);
                }
                break;
            case 3:
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    setSpeed(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_V) {
                    setSpeed(speed);
                }
                break;
            case 4:
                if (e.getKeyCode() == KeyEvent.VK_M) {
                    setSpeed(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_COMMA) {
                    setSpeed(speed);
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_A) {
                    setSpeed(0);
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setSpeed(0);
                }
                break;
            case 3:
                if (e.getKeyCode() == KeyEvent.VK_C || e.getKeyCode() == KeyEvent.VK_V) {
                    setSpeed(0);
                }
                break;
            case 4:
                if (e.getKeyCode() == KeyEvent.VK_M || e.getKeyCode() == KeyEvent.VK_COMMA) {
                    setSpeed(0);
                }
                break;
        }
    }

    public void setSpeed(int Speed) {
        if (id == 1 || id == 2) {
            yS = Speed;
        } else {
            xS = Speed;
        }
    }

    public void go() {
        y += yS;
        x += xS;
    }

    public void colour(Graphics graphic) {
        if (id == 1)
            graphic.setColor(Color.cyan);
        else if (id == 2)
            graphic.setColor(Color.pink);
        else if (id == 3)
            graphic.setColor(Color.orange);
        else
            graphic.setColor(Color.magenta);

        graphic.fillRect(x, y, width, height);
//        graphic.setColor(Color.white);
        graphic.draw3DRect(x, y, width, height, false);
    }
}
