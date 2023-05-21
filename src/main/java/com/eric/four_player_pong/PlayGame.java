package com.eric.four_player_pong;

import javax.swing.*;
import java.awt.*;

public class PlayGame extends JFrame {
    Panel panel;
    pOneAndPTwoInstructionsScreen pOneAndPTwo;
    pThreeAndPFourInstructionsScreen pThreeAndPFour;
    JPanel container;
    PlayGame() {
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

        add(container);

        pOneAndPTwo = new pOneAndPTwoInstructionsScreen();
        panel = new Panel();
        pThreeAndPFour = new pThreeAndPFourInstructionsScreen();

        container.add(pOneAndPTwo);
        container.add(panel);
        container.add(pThreeAndPFour);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.black);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }
}
