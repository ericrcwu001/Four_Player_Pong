package com.eric.four_player_pong;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {
    int xS, yS;
    int speed = 4;
    Random random;

    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        int randX = random.nextInt(-29, 29);
        int randY;
        if (randX < 0) {
            randY = -30 - randX;
            int rand = random.nextInt(0, 2);
            if (rand == 0) randY *= -1;
        } else if (randX == 0) {
            randX = 15; randY = 15;
        } else {
            randY = 30 - randX;
            int rand = random.nextInt(0, 2);
            if (rand == 0) randY *= -1;
        }

        setXSpeed(randX * speed);
        setYSpeed(randY * speed);
    }

    public void setXSpeed(int s) {
        xS = s;
    }

    public void setYSpeed(int s) {
        yS = s;
    }

    public void go(int first) {
        if (first <= 200) { // first acts like a buffer
            x = (Panel.width/2)-(width/2);
            y = (Panel.height/2)-(height/2);
        } else {
            x += xS / 15;
            y += yS / 15;
        }

    }
    public void colour(Graphics graphic) {
        graphic.setColor(Color.white);
        graphic.fillOval(x, y, height, width);
    }
}
