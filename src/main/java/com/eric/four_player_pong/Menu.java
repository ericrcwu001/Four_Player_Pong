package com.eric.four_player_pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import static com.eric.four_player_pong.Panel.screenSize;

public class Menu extends JPanel {
    static final int height = (int) screenSize.getHeight()-50;
    static final int width = (int) screenSize.getWidth();
    static final Dimension size = new Dimension(width, height);
    JButton startButton, instructionsButton, settingsButton;
    int buttonHeight = 130;
    int buttonWidth = 315;
    Dimension buttonSize = new Dimension(buttonWidth, buttonHeight);
    public boolean isGame = false, isInstructions = false, isSettings = false;
    Menu() {
        this.setFocusable(true);
        this.setPreferredSize(size);
        this.setLayout(new GridBagLayout());

        ImageIcon title = new ImageIcon("src/main/java/com/eric/four_player_pong/misc_resources/title.png");
        JLabel titleLabel = new JLabel(title);
        titleLabel.setPreferredSize(new Dimension(480, 300));

        ImageIcon startIcon = new ImageIcon("src/main/java/com/eric/four_player_pong/buttons/startButton.png");
        startButton = new JButton(startIcon);
        ImageIcon settingIcon = new ImageIcon("src/main/java/com/eric/four_player_pong/buttons/settingButton.png");
        settingsButton = new JButton(settingIcon);
        ImageIcon instructionsIcon = new ImageIcon("src/main/java/com/eric/four_player_pong/buttons/instructionsButton.png");
        instructionsButton = new JButton(instructionsIcon);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.NORTH;

        buttonInit(startButton);
        buttonInit(settingsButton);
        buttonInit(instructionsButton);

        this.add(titleLabel, constraints);
        this.add(startButton, constraints);
        this.add(settingsButton, constraints);
        this.add(instructionsButton,constraints);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setIsGame(true);
//                System.out.println("clicked start");
            }
        });
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSettings = true;
//                System.out.println("clicked settings");
            }
        });
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isInstructions = true;
//                System.out.println("clicked instructions");
            }
        });
    }

    private void buttonInit(JButton button) {
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setPreferredSize(buttonSize);
    }

    public boolean getIsGame() {
        return isGame;
    }
    public void setIsGame(boolean b) {
        this.isGame = b;
    }
    public boolean getIsSettings() {
        return isSettings;
    }
    public void setIsSettings(boolean b) {
        this.isSettings = b;
    }
    public boolean getIsInstructions() {
        return isInstructions;
    }
    public void setIsInstructions(boolean b) {
        this.isInstructions = b;
    }
}
