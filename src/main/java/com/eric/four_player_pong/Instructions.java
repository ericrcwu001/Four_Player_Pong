package com.eric.four_player_pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.eric.four_player_pong.Panel.screenSize;

public class Instructions extends JPanel {
    int height = (int) screenSize.getHeight()-50;
    int width = (int) screenSize.getWidth();
    Dimension size = new Dimension(width, height);
    JButton backButton;
    int buttonHeight = 130;
    int buttonWidth = 315;
    Dimension buttonSize = new Dimension(buttonWidth, buttonHeight);
    public boolean isMenu;
    Instructions() {
        this.setFocusable(true);
        this.setPreferredSize(size);
        this.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.NORTH;

        ImageIcon instructionsIcon = new ImageIcon(
                "src/main/java/com/eric/four_player_pong/misc_resources/allInstructions.png");
        JLabel titleLabel = new JLabel(instructionsIcon);
        ImageIcon backIcon = new ImageIcon("src/main/java/com/eric/four_player_pong/buttons/back.png");
        backButton = new JButton(backIcon);

        buttonInit(backButton);

        titleLabel.setPreferredSize(new Dimension(640, 480));
        this.add(titleLabel, constraints);
        this.add(backButton, constraints);

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
