package com.eric.four_player_pong;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Keyboard extends Rectangle {
    int w = pOneAndPTwoInstructionsScreen.width;
    int h = pOneAndPTwoInstructionsScreen.height;
    int midY = h/2 - 95 - 10, midX = 10;
    int id;
    Keyboard(int id) {
        this.id = id;
    }

    public void colour(Graphics graphic) {
        graphic.setColor(Color.white);
        graphic.setFont(new Font("Monospaced", Font.PLAIN, 40));
        graphic.drawString("CONTROLS",w/2-80, midY);
        if (id == 0) {
            // ROW 1
            drawKey(midX + 20, midY + 20, "1", graphic);
            drawKey(midX + 60, midY + 20, "2", graphic);
            drawKey(midX + 100, midY + 20, "3", graphic);
            drawKey(midX + 140, midY + 20, "4", graphic);
            drawKey(midX + 180, midY + 20, "5", graphic);

            // ROW 2
            drawKey(midX + 33, midY + 60, "Q", graphic);
            drawKey(midX + 73, midY + 60, "W", graphic);
            drawKey(midX + 113, midY + 60, "E", graphic);
            drawKey(midX + 153, midY + 60, "R", graphic);
            drawKey(midX + 193, midY + 60, "T", graphic);

            // ROW 3
            drawKey(midX + 47, midY + 100, "A", graphic);
            drawKey(midX + 87, midY + 100, "S", graphic);
            drawKey(midX + 127, midY + 100, "D", graphic);
            drawKey(midX + 167, midY + 100, "F", graphic);
            drawKey(midX + 207, midY + 100, "G", graphic);

            // ROW 4
            drawKey(midX + 60, midY + 140, "Z", graphic);
            drawKey(midX + 100, midY + 140, "X", graphic);
            drawKey(midX + 140, midY + 140, "C", graphic);
            drawKey(midX + 180, midY + 140, "V", graphic);
//            drawKey(220, midY + 140, "B", graphic);

            // ROW 5
            drawKey(midX + 20, midY + 180, "^", graphic);
            drawKey(midX + 60, midY + 180, "⌥", graphic);
            drawKey(midX + 100, midY + 180, "⌘", graphic);
            graphic.drawRoundRect(midX + 140, midY + 180, 120, 30, 7, 7);
            graphic.drawString("SPACE",midX+155+8, midY+180+25);


//            drawKey(180, midY + 180, "V", graphic);

        } else {
            drawKey(midX + 20, midY + 20, "8", graphic);
            drawKey(midX + 60, midY + 20, "9", graphic);
            drawKey(midX + 100, midY + 20, "0", graphic);
            drawKey(midX + 140, midY + 20, "-", graphic);
            drawKey(midX + 180, midY + 20, "=", graphic);
//            drawKey(midX + 220, midY + 20, "\\", graphic);

            // ROW 2
            drawKey(midX + 33, midY + 60, "I", graphic);
            drawKey(midX + 73, midY + 60, "O", graphic);
            drawKey(midX + 113, midY + 60, "P", graphic);
            drawKey(midX + 153, midY + 60, "[", graphic);
            drawKey(midX + 193, midY + 60, "]", graphic);

            // ROW 3
            drawKey(midX + 47, midY + 100, "K", graphic);
            drawKey(midX + 87, midY + 100, "L", graphic);
            drawKey(midX + 127, midY + 100, ";", graphic);
            drawKey(midX + 167, midY + 100, "\'", graphic);
//            drawKey(midX + 207, midY + 100, "G", graphic);

            // ROW 4
            drawKey(midX + 20, midY + 140, "M", graphic);
            drawKey(midX + 60, midY + 140, ",", graphic);
            drawKey(midX + 100, midY + 140, ".", graphic);
            drawKey(midX + 140, midY + 140, "/", graphic);
//            drawKey(midX + 180, midY + 140, "V", graphic);

            // ROW 5
            drawKey(midX + 60, midY + 180, "⌘", graphic);
            drawKey(midX + 100, midY + 180, "⌥", graphic);
            drawKey(midX + 140, midY + 180, "←", graphic);
            drawKey(midX + 180, midY + 180, "UP", graphic);
            drawKey(midX + 180, midY + 180, "DOWN", graphic);
            drawKey(midX + 220, midY + 180, "→", graphic);
//            drawKey(midX + 100, midY + 180, "⌘", graphic);
        }
    }
    public void drawKey(int x, int y, String key, Graphics graphic) {
        graphic.setFont(new Font("Monospaced", Font.PLAIN, 25));
        if (key.equals("Q") || key.equals("A")) {
            graphic.setColor(Color.cyan);
            graphic.fillRoundRect(x, y, 30, 30, 7, 7);
            graphic.setColor(Color.black);
            graphic.drawString(key, x+8, y+25);

        } else if (key.equals("C") || key.equals("V")) {
            graphic.setColor(Color.orange);
            graphic.fillRoundRect(x, y, 30, 30, 7, 7);
            graphic.setColor(Color.black);
            graphic.drawString(key, x+8, y+25);

        } else if (key.equals("M") || key.equals(",")) {
            graphic.setColor(Color.magenta);
            graphic.fillRoundRect(x, y, 30, 30, 7, 7);
            graphic.setColor(Color.black);
            graphic.drawString(key, x+8, y+25);
        } else if (key.equals("UP") || key.equals("DOWN")) {
            graphic.setColor(Color.pink);
            graphic.setFont(new Font("Monospaced", Font.PLAIN, 18));

            if (key.equals("UP")) {
                graphic.fillRoundRect(x, y, 30, 14, 7, 7);
                graphic.setColor(Color.black);
                graphic.drawString("↑", x+10, y+13);
            } else {
                graphic.fillRoundRect(x, y+16, 30, 14, 7, 7);
                graphic.setColor(Color.black);
                graphic.drawString("↓", x+10, y+28);
            }

        } else {
            graphic.setColor(Color.WHITE);
            graphic.drawRoundRect(x, y, 30, 30, 7, 7);
            graphic.drawString(key, x+8, y+25);
        }
    }
}
