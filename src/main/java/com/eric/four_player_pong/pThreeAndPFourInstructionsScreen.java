package com.eric.four_player_pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.eric.four_player_pong.Panel.screenSize;

public class pThreeAndPFourInstructionsScreen extends JPanel {
    //<editor-fold desc="GUI">
    static final int width = ((int) screenSize.getWidth() - Panel.width) / 2;
    static final int height = Panel.height;
    static final Dimension size = new Dimension(width, height);
    Image image;
    Graphics graphics;
    //</editor-fold>
    Keyboard right;
    pThreeAndPFourInstructionsScreen() {
        newKeyboard();

        // JPanel methods
        this.setFocusable(false);
        this.setPreferredSize(size);
    }

    public void newKeyboard() {
        right = new Keyboard(1);
    }
    public void paint(Graphics graphic) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        graphic.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics graphics) {
        right.colour(graphics);
    }
    // Creates 2 Keyboards
}
