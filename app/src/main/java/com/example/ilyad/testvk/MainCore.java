package com.example.ilyad.testvk;

import java.io.*;
/**
public class MainCore extends JPanel {
    private static int widthLine = 10;
    private Line line;
    private Stick[] sticks;
    private boolean startGame, gameOver = false;
    private int score;
    private int oldScore;
    private int maxScore;
    private int count;
    private long timeMS;
    private int distanceStick;
    private String text;
    private File file;

    MainCore() {
        sticks = new Stick[3];
        for (int i = 0; i < 3; i++) {
            sticks[i] = new Stick(500, 600);
        }

        line = new Line(500,600, widthLine);

        text = new TextForPlay().getText();
        file = new File("data\\db\\score.txt");
        score = oldScore = 0;
        maxScore = readScore();

        /* Default paintComponent*/
/**
        font = new Font("Bebas Neue", Font.BOLD, 50);
        setBackground(Color.decode("#170c24"));
        setFont(font);
        setFocusable(true);
**/
        /*Listeners*/
/**
        KeyListener keyListener = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    long currentTime = System.currentTimeMillis();
                    if (currentTime > timeMS + 50) {
                        startGame = true;
                        line.tap();
                    }
                    timeMS = currentTime;
                }

                if (gameOver && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    line = new Line(widthPanel, heightPanel, widthLine);
                    for (int i = 0; i < 3; i++) {
                        sticks[i] = new Stick(widthPanel, heightPanel);
                    }
                    text = new TextForPlay().getText();
                    gameOver = false;
                    startGame = false;
                    score = oldScore = 0;
                }
            }
        };
        addKeyListener(keyListener);
    }
**//**
 //   @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(widthLine, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        if (!gameOver) {
            paintCurveLine(g2d);
            paintSticks(g2d);
            if (startGame) {
                g2d.setColor(Color.WHITE);
                if (score != oldScore) {
                    g2d.setFont(font.deriveFont(Font.BOLD, count));
                    count -= 1;
                } else count = 60;  // анимация для score
                if (count == 50) {
                    oldScore = score;
                }
                g2d.drawString(String.valueOf(score), getWidth() / 2, 100);   // после 100 очков счет пишется также по центру
            }

        } else {
            g2d.setColor(Color.decode("#e8dc00"));
            paintBang(g2d);
        }
        if (!startGame) {
            g2d.setColor(Color.WHITE);
            g2d.drawString(text, (getWidth() - text.length() * 20) / 2, 100);
        }
    }
/**
    private void paintCurveLine(Graphics2D g2d) {
        int widthCometa = widthLine;
        for (int i = 1; i < line.getCount(); i++) {
            g2d.setStroke(new BasicStroke(widthCometa, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            if (i % 2 != 0) g2d.setColor(Color.GREEN);
            else g2d.setColor(Color.BLACK);
            g2d.drawLine(line.getLineX()[i - 1], line.getLineY()[i - 1], line.getLineX()[i], line.getLineY()[i]);
        }
    }

    private void paintSticks(Graphics2D g2d) {
        g2d.setColor(Color.decode("#02eff7"));
        for (Stick element : sticks) {
            int y = element.getY();
            g2d.drawLine(element.getX1(), y, element.getX2(), y);
            if (element.isDoubleSticks()) {
                g2d.drawLine(element.getX3(), y, element.getX4(), y);
            }
        }
    }

    private void paintBang(Graphics2D g2d) {
        if (count < heightPanel * 2) {
            int radius = count / 2;
            g2d.fillOval(line.getX1() - radius, line.getY1() - radius, count, count);
            count += 40;
        } else paintResult(g2d);
    }

    private void paintResult(Graphics2D g2d) {
        g2d.drawString("Game over", 150, 150);
        g2d.setColor(Color.WHITE);
        if (score > maxScore) {
            maxScore = score;
            g2d.drawString("you new record: " + score, 120, 300);
            writeScore(maxScore);
        } else {
            g2d.drawString("Best scrore: " + maxScore, 100, 400);
            g2d.setFont(font.deriveFont(Font.BOLD, 35));
            g2d.drawString("You scrore: " + score, 150, 300);
        }
        g2d.setFont(font.deriveFont(Font.BOLD, 20));
        g2d.drawString("press enter to replay", 170, 500);
    }

}*/
