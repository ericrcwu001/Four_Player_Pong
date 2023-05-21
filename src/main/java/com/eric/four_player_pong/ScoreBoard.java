package com.eric.four_player_pong;

import java.awt.*;

public class ScoreBoard extends Rectangle {
    static int width, height;
    int[] players = {0, 0, 0, 0};

    ScoreBoard(int width, int height) {
        ScoreBoard.width = width;
        ScoreBoard.height = height;
    }

    public void colour(Graphics graphic) {
        graphic.setColor(Color.white);
        graphic.setFont(new Font("Monospaced", Font.PLAIN, 25));

        graphic.drawLine(width / 2, 50, width / 2, (height/2) - 75);
        graphic.drawLine(width / 2, (height/2) + 75, width / 2, height-50);
        graphic.drawLine(50, height / 2, (width/2) - 75, height / 2);
        graphic.drawLine((width/2) + 75, height / 2, width-50, height / 2);

        graphic.drawString(String.valueOf(players[0] / 10) + String.valueOf(players[0] % 10), (width / 2)-65, (height / 2)+10);
        graphic.drawString(String.valueOf(players[1] / 10) + String.valueOf(players[1] % 10), (width / 2)+35, (height / 2)+10);
        graphic.drawString(String.valueOf(players[2] / 10) + String.valueOf(players[2] % 10), (width / 2)-15, (height / 2)-40);
        graphic.drawString(String.valueOf(players[3] / 10) + String.valueOf(players[3] % 10), (width / 2)-15, (height / 2)+60);


    }
}
