package com.eric.four_player_pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.eric.four_player_pong.Panel.screenSize;

public class MusicScreen extends JPanel {
    int height = (int) screenSize.getHeight()-50;
    int width = (int) screenSize.getWidth();
    Dimension size = new Dimension(width, height);
    JButton musicButton, soundButton, backButton;
    static boolean musicON = true, soundON = true;
    int buttonHeight = 130;
    int buttonWidth = 315;
    Dimension buttonSize = new Dimension(buttonWidth, buttonHeight);
    public boolean isMenu;

    MusicScreen() {
//        setFocusable(true);
        setPreferredSize(size);
        setLayout(new GridBagLayout());

        ImageIcon musicONIcon = new ImageIcon("src/main/java/com/eric/four_player_pong/buttons/musicON.png");
        ImageIcon musicOFFIcon = new ImageIcon("src/main/java/com/eric/four_player_pong/buttons/musicOFF.png");
        musicButton = new JButton(musicONIcon);
        ImageIcon soundONIcon = new ImageIcon("src/main/java/com/eric/four_player_pong/buttons/soundON.png");
        ImageIcon soundOFFIcon = new ImageIcon("src/main/java/com/eric/four_player_pong/buttons/soundOFF.png");
        soundButton = new JButton(soundONIcon);
        ImageIcon backIcon = new ImageIcon("src/main/java/com/eric/four_player_pong/buttons/back.png");
        backButton = new JButton(backIcon);

        buttonInit(backButton);
        musicButton.setBorderPainted(false);
        musicButton.setFocusPainted(false);
        musicButton.setContentAreaFilled(false);
        musicButton.setPreferredSize(new Dimension(540, 700));
        soundButton.setBorderPainted(false);
        soundButton.setFocusPainted(false);
        soundButton.setContentAreaFilled(false);
        soundButton.setPreferredSize(new Dimension(540, 700));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(musicButton, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(soundButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.ipady = 50;

        add(backButton, constraints);

        musicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                musicButton.setIcon(musicON ? musicOFFIcon : musicONIcon);
                musicON = !musicON;
            }
        });
        soundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundButton.setIcon(soundON ? soundOFFIcon : soundONIcon);
                soundON = !soundON;
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isMenu = true;
            }
        });
    }
    private void buttonInit(JButton button) {
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setPreferredSize(buttonSize);
    }
}
