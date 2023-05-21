package com.eric.four_player_pong;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Game extends JFrame {
    static boolean onInstructions = false, onSettings = false;
    Panel panel;
    Instructions instructionsP;
    MusicScreen musicScreen;
    pOneAndPTwoInstructionsScreen pOneAndPTwo;
    pThreeAndPFourInstructionsScreen pThreeAndPFour;
    JPanel container;
    Menu menu;
    PlayGame playgame;

    Game() {
        setTitle("4-Player Pong");

        menu = new Menu();

        add(menu);

//        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setBackground(Color.black);
        setVisible(true);
        setLocationRelativeTo(null);

        run();
    }

    public void run() {
        long lastTime = System.nanoTime();

        final double ticks = 60D;
        double ns = 1000000000 / ticks;
        double delta = 0;

        while (!menu.getIsGame()) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                if (!onInstructions && menu.isInstructions) {
                    onInstructions = true;

                    remove(menu);

                    instructionsP = new Instructions();

                    add(instructionsP);

                    setResizable(false);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    pack();
                    setBackground(Color.black);
                    setVisible(true);
                    setLocationRelativeTo(null);
//                    repaint();
                }
                if (onInstructions && instructionsP.isMenu) {
                    onInstructions = false;

                    menu = new Menu();

                    remove(instructionsP);

                    add(menu);

                    setResizable(false);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    pack();
                    setBackground(Color.black);
                    setVisible(true);
                    setLocationRelativeTo(null);
//                    repaint();
                }
                if (!onSettings && menu.isSettings) {
                    onSettings = true;

                    remove(menu);

                    musicScreen = new MusicScreen();

                    add(musicScreen);

                    setResizable(false);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    pack();
                    setBackground(Color.black);
                    setVisible(true);
                    setLocationRelativeTo(null);
//                    repaint();
                }
                if (onSettings && musicScreen.isMenu) {
                    onSettings = false;

                    menu = new Menu();

                    remove(musicScreen);

                    add(menu);

                    setResizable(false);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    pack();
                    setBackground(Color.black);
                    setVisible(true);
                    setLocationRelativeTo(null);
//                    repaint();
                }
                delta--;
            }
        }

        if (menu.getIsGame()) {
            remove(menu);
            setVisible(false);

            playgame = new PlayGame();
        }
    }
}

