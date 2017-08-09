package com.example.ilyad.testvk;


import java.io.*;

class Core {

    static final int widthLine = 20;
    private Line line;
    private Stick[] sticks;
    private boolean startGame, gameOver = false;
    private int score;
    private int oldScore;
    private int maxScore;
    private int count;
    private long timeMS;
    private String text;
    static final int heightPanel = 1230;
    static final int widthPanel = 720;


    Core() {
        sticks = new Stick[3];
        for (int i = 0; i < 3; i++) {
            sticks[i] = new Stick(widthPanel, heightPanel);
        }
        line = new Line(widthPanel, heightPanel, widthLine);

        text = new TextForPlay().getText();

        score = oldScore = 0;
        maxScore = 30;
        ThreadCore threadCore = new ThreadCore();
        threadCore.start();

    }

    private void isGameOver() {
        for (Stick element : sticks) {
            if (element.getY() + 3 > line.getY1() && element.getY() - 3 < line.getY1()) {
                if (element.isDoubleSticks() && line.getX1() > element.getX3() - widthLine && line.getX1() < element.getX4() + widthLine)
                    gameOver = true;
                if (line.getX1() > element.getX1() - widthLine && line.getX1() < element.getX2() + widthLine)
                    gameOver = true;
                else score++;
            }
        }
        if (line.isCrashed()) gameOver = true;
    }

    boolean tapped() {
        long currentTime = System.currentTimeMillis();
        if (currentTime > timeMS + 50) {  // нельзя быстро нажать 2 тапа (защита)
            startGame = true;
            line.tap();
        }
        timeMS = currentTime;
        if (gameOver) {
            line = new Line(Core.widthPanel, Core.heightPanel, Core.widthLine);
            for (int i = 0; i < 3; i++) {
                sticks[i] = new Stick(Core.widthPanel, Core.heightPanel);
            }
            text = new TextForPlay().getText();
            gameOver = false;
            startGame = false;
            score = 0;
        }
        return true;
    }
    /*Thread core (timer)*/

    class ThreadCore extends Thread {

        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    sleep(15);
                    if (!gameOver) {
                        line.start();
                        if (startGame) {
                            int y0 = sticks[0].getY();
                            int y1 = sticks[1].getY();
                            int y2 = sticks[2].getY();
                            int distanceStick = heightPanel / 2 + Stick.delay;
                            if (y2 == Stick.delay || y1 > distanceStick || y2 > distanceStick) sticks[0].start();
                            if (y0 > distanceStick || y1 > distanceStick) sticks[1].start();
                            if (y1 > distanceStick || y2 > distanceStick) sticks[2].start();
                            isGameOver();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void setOldScore(int oldScore) {
        this.oldScore = oldScore;
    }

    void setCount(int count) {
        this.count = count;
    }

    void countWane() {
        count--;
    }

    Line getLine() {
        return line;
    }

    Stick[] getSticks() {
        return sticks;
    }

    boolean isStartGame() {
        return startGame;
    }

    boolean isGaveOver() {
        return gameOver;
    }

    int getScore() {
        return score;
    }

    int getOldScore() {
        return oldScore;
    }

    int getMaxScore() {
        return maxScore;
    }

    int getCount() {
        return count;
    }

    String getText() {
        return text;
    }

    void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
}
