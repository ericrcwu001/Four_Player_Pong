package com.eric.four_player_pong;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import static com.eric.four_player_pong.Panel.screenSize;

public class pOneAndPTwoInstructionsScreen extends JPanel {
    //<editor-fold desc="GUI">
    static final int width = ((int) screenSize.getWidth() - Panel.width) / 2;
    static final int height = Panel.height;
    static final Dimension size = new Dimension(width, height);
    Image image;
    Graphics graphics;
    //</editor-fold>
    Keyboard left;
    pOneAndPTwoInstructionsScreen() {
        newKeyboard();

        // JPanel methods
        this.setFocusable(false);
        this.setPreferredSize(size);
    }

    public void newKeyboard() {
        left = new Keyboard(0);
    }
    // Creates 2 Keyboards

    public void paint(Graphics graphic) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        graphic.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics graphics) {
        left.colour(graphics);
    }
}
