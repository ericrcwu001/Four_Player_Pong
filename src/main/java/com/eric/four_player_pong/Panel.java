package com.eric.four_player_pong;

import java.awt.*;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static com.eric.four_player_pong.MusicScreen.musicON;
import static com.eric.four_player_pong.MusicScreen.soundON;

public class Panel extends JPanel implements Runnable {
    //<editor-fold desc="GUI">
    static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static final int height = (int) screenSize.getHeight()-50;
    static final int width = height;
    static final Dimension size = new Dimension(width, height);
    static final int racketWidth = width/32;
    static final int racketHeight = height/8;
    Image image;
    Graphics graphics;
    //</editor-fold>
    //<editor-fold desc="BALL">
    static final int ballDiameter = 30; // make sure it's a multiple of 2
    Ball ball;
    //</editor-fold>
    //<editor-fold desc="RACKETS">
    Racket player1;
    Racket player2;
    Racket player3;
    Racket player4;
    //</editor-fold>
    //<editor-fold desc="MISC">
    Random random = new Random();
    Win win = new Win(width, height);
    ScoreBoard scoreB;
    Thread gameT;
    int first = 0, lastTouchId = -1; // handle the -1 case --> DELETE ONCE DONE
    boolean winner = false;
    //</editor-fold>
    boolean paused = false;
    int pBXS, pBYS;
    Clip paddleSfx, pointSfx, music, losePointSfx;
    double lastPaddleSfx = 10000000000000f, lastPointSfx = 10000000000000f, lastLosePointSfx = 10000000000000f;
    Panel() {
        // Instantiating Rackets + Balls + ScoreBoard
        newRacket();
        createBall();
        scoreB = new ScoreBoard(width, height);

        // JPanel methods
        setFocusable(true);
        addKeyListener(new Click());
        setPreferredSize(size);

        // Game Thread
        gameT = new Thread(this);
        gameT.start();
    }

    public class Click extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("HERE");
            player1.keyPressed(e);
            player2.keyPressed(e);
            player3.keyPressed(e);
            player4.keyPressed(e);
            if (e.getKeyCode() == KeyEvent.VK_P) {
                if (paused) {
                    ball.xS = pBXS; ball.yS = pBYS;
                    paused = false;
                } else {
                    pBXS = ball.xS; pBYS = ball.yS;
                    ball.xS = 0; ball.yS = 0;
                    paused = true;
                }
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
            player1.keyReleased(e);
            player2.keyReleased(e);
            player3.keyReleased(e);
            player4.keyReleased(e);
        }
    }
    // Click contains keyPressed and keyReleased methods that support the Racket Class

    public void createBall() {
        first = 0;
        random = new Random();
        ball = new Ball((width/2)-(ballDiameter/2), (height/2)-(ballDiameter/2), ballDiameter, ballDiameter);
    }
    // Creates a ball at the center of the JPanel that is ballDiameter by ballDiameter in size

    public void newRacket() {
        player1 = new Racket(0, (height / 2) - (racketHeight / 2), racketWidth, racketHeight, 1);
        player2 = new Racket(width - racketWidth, (height / 2) - (racketHeight / 2), racketWidth, racketHeight, 2);
        player3 = new Racket((width / 2) - (racketHeight / 2), 0, racketHeight, racketWidth, 3);
        player4 = new Racket((width / 2) - (racketHeight / 2), height - racketWidth, racketHeight, racketWidth, 4);
    }
    // Creates 4 Rackets

    public void paint(Graphics graphic) {
        if (winner) {
            music.stop();
            paddleSfx.close();
            pointSfx.close();
            music.close();
            losePointSfx.close();

            if (scoreB.players[0] == 11)
                Win.win(graphic, 1);
            else if (scoreB.players[1] == 11)
                Win.win(graphic, 2);
            else if (scoreB.players[2] == 11)
                Win.win(graphic, 3);
            else if (scoreB.players[3] == 11)
                Win.win(graphic, 4);

        } else {
            image = createImage(getWidth(), getHeight());
            graphics = image.getGraphics();
            draw(graphics);
            graphic.drawImage(image, 0, 0, this);
        }
    }
    // Drawing logic

    public void draw(Graphics graphic) {
        player1.colour(graphic);
        player2.colour(graphic);
        player3.colour(graphic);
        player4.colour(graphic);
        ball.colour(graphic);
        scoreB.colour(graphic);
        Toolkit.getDefaultToolkit().sync();
    }
    // Draws the 4 rackets, the ball, and the scoreboard

    public void go() {
        ball.go(first);

        player1.go();
        player2.go();
        player3.go();
        player4.go();

        first++;
    }
    // updating per tick

    public boolean checkWinner() {
        if (scoreB.players[0] == 11 || scoreB.players[1] == 11 || scoreB.players[2] == 11 ||
                scoreB.players[3] == 11) {
            repaint();
            return true;
        }
        return false;
    }
    // self-explanatory

    public void hit() {
        //<editor-fold desc="BALL INTERSECTIONS WITH RACKETS">
        if (ball.intersects(player1)) {
            lastTouchId = 1;
            ball.xS = Math.abs(ball.xS);
            ball.xS += 2;
            if (ball.yS > 0)
                ball.yS += 2;
            else
                ball.yS -= 2;
            ball.setXSpeed(ball.xS);
            ball.setYSpeed(ball.yS + player1.speed/2);
            paddleSfx.start();
            lastPaddleSfx = System.nanoTime();
        }
        if (ball.intersects(player2)) {
            lastTouchId = 2;
            ball.xS = Math.abs(ball.xS);
            ball.xS += 2;
            if (ball.yS > 0)
                ball.yS += 2;
            else
                ball.yS -= 2;
            ball.setXSpeed(-ball.xS);
            ball.setYSpeed(ball.yS + player2.speed/2);
            paddleSfx.start();
            lastPaddleSfx = System.nanoTime();
        }
        if (ball.intersects(player3)) {
            lastTouchId = 3;
            ball.yS = Math.abs(ball.yS);
            ball.yS += 2;
            if (ball.xS > 0)
                ball.xS += 2;
            else
                ball.xS -= 2;
            ball.setXSpeed(ball.xS + player3.speed/2);
            ball.setYSpeed(ball.yS);
            paddleSfx.start();
            lastPaddleSfx = System.nanoTime();
        }
        if (ball.intersects(player4)) {
            lastTouchId = 4;
            ball.yS = Math.abs(ball.yS);
            ball.yS += 2;
            if (ball.xS > 0)
                ball.xS += 2;
            else
                ball.xS -= 2;
            ball.setXSpeed(ball.xS + player4.speed/2);
            ball.setYSpeed(-ball.yS);
            paddleSfx.start();
            lastPaddleSfx = System.nanoTime();
        }
        //</editor-fold>
        //<editor-fold desc="prevents racket from going off-screen">
        if (player2.y >= (height - racketHeight - racketWidth))
            player2.y = height - racketHeight - racketWidth;
        if (player1.y >= (height - racketHeight - racketWidth))
            player1.y = height - racketHeight - racketWidth;
        if (player2.y <= racketWidth)
            player2.y = racketWidth;
        if (player1.y <= racketWidth)
            player1.y = racketWidth;

        if (player3.x >= (width - racketHeight - racketWidth))
            player3.x = width - racketHeight - racketWidth;
        if (player4.x >= (width - racketHeight - racketWidth))
            player4.x = width - racketHeight - racketWidth;
        if (player3.x <= racketHeight)
            player3.x = racketHeight;
        if (player4.x <= racketHeight)
            player4.x = racketHeight;
        //</editor-fold>
        //<editor-fold desc="SCORING POINTS">
        if (lastTouchId != -1) {
            if (ball.x >= width - ballDiameter/2) {
                scoreB.players[lastTouchId - 1]++;
                newRacket();
                createBall();
                pointSfx.start();
                lastPointSfx = System.nanoTime();
                lastTouchId = -1;
            }
            if (ball.x <= 0) {
                scoreB.players[lastTouchId - 1]++;
                newRacket();
                createBall();
                pointSfx.start();
                lastPointSfx = System.nanoTime();
                lastTouchId = -1;
            }
            if (ball.y >= height - ballDiameter/2) {
                scoreB.players[lastTouchId - 1]++;
                newRacket();
                createBall();
                pointSfx.start();
                lastPointSfx = System.nanoTime();
                lastTouchId = -1;
            }
            if (ball.y <= 0) {
                scoreB.players[lastTouchId - 1]++;
                newRacket();
                createBall();
                pointSfx.start();
                lastPointSfx = System.nanoTime();
                lastTouchId = -1;
            }
        } else {
            if (ball.x >= width - ballDiameter/2) {
                scoreB.players[2 - 1]--;
                newRacket();
                createBall();
                losePointSfx.start();
                lastLosePointSfx = System.nanoTime();
            }
            if (ball.x <= 0) {
                scoreB.players[1 - 1]--;
                newRacket();
                createBall();
                losePointSfx.start();
                lastLosePointSfx = System.nanoTime();
            }
            if (ball.y >= height - ballDiameter/2) {
                scoreB.players[4 - 1]--;
                newRacket();
                createBall();
                losePointSfx.start();
                lastLosePointSfx = System.nanoTime();
            }
            if (ball.y <= 0) {
                scoreB.players[3 - 1]--;
                newRacket();
                createBall();
                losePointSfx.start();
                lastLosePointSfx = System.nanoTime();
            }
        }
        //</editor-fold>

        for (int i = 0; i < 4; ++i) {
            if (scoreB.players[i] < 0) {
                scoreB.players[i] = 0;
            }
        }
    }
    public void run() {
        // https://stackoverflow.com/questions/26838286/delta-time-getting-60-updates-a-second-in-java
        long lastTime = System.nanoTime();

        final double ticks = 60D;
        double ns = 1000000000 / ticks;
        double paddleSfxLength = 27000000;
        double pointSfxLength = 331000000;
        double losePointSfxLength = 462000000;
        double delta = 0;

        try {
            String paddlePath = "src/main/java/com/eric/four_player_pong/sfx/paddle_sfx.wav";
            String pointPath = "src/main/java/com/eric/four_player_pong/sfx/point_sfx.wav";
            String musicPath = "src/main/java/com/eric/four_player_pong/sfx/pong_background_music.wav";
            String losePointPath = "src/main/java/com/eric/four_player_pong/sfx/lose_point.wav";
            AudioInputStream paddleStream = AudioSystem.getAudioInputStream(new File(paddlePath).getAbsoluteFile());
            AudioInputStream pointStream = AudioSystem.getAudioInputStream(new File(pointPath).getAbsoluteFile());
            AudioInputStream musicStream = AudioSystem.getAudioInputStream(new File(musicPath).getAbsoluteFile());
            AudioInputStream losePointStream = AudioSystem.getAudioInputStream(new File(losePointPath).getAbsoluteFile());
            paddleSfx = AudioSystem.getClip();
            pointSfx = AudioSystem.getClip();
            music = AudioSystem.getClip();
            losePointSfx = AudioSystem.getClip();
            paddleSfx.open(paddleStream);
            pointSfx.open(pointStream);
            music.open(musicStream);
            losePointSfx.open(losePointStream);
            FloatControl musicVolume = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
            musicVolume.setValue(musicON ? -38f : -200f);
            FloatControl paddleVolume = (FloatControl) paddleSfx.getControl(FloatControl.Type.MASTER_GAIN);
            paddleVolume.setValue(soundON ? -20f : -200f);
            FloatControl pointVolume = (FloatControl) pointSfx.getControl(FloatControl.Type.MASTER_GAIN);
            pointVolume.setValue(soundON ? -20f : -200f);
            FloatControl losePointVolume = (FloatControl) losePointSfx.getControl(FloatControl.Type.MASTER_GAIN);
            losePointVolume.setValue(soundON ? -20f : -200f);
            music.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        while (!winner) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                go();
                hit();
                repaint();
                winner = checkWinner();
                delta--;
                if (!paddleSfx.isRunning()) paddleSfx.setFramePosition(0);
            }
            if (now - lastPaddleSfx >= paddleSfxLength) {
                paddleSfx.stop();
                paddleSfx.setFramePosition(0);
            }
            if (now - lastPointSfx >= pointSfxLength) {
                pointSfx.stop();
                pointSfx.setFramePosition(0);
            }
            if (now - lastLosePointSfx >= losePointSfxLength) {
                losePointSfx.stop();
                losePointSfx.setFramePosition(0);
            }
        }
    }
}
