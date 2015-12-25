package de.andipaetzold.dodgeit.game;

import android.graphics.Canvas;

public class GameLoopThread extends Thread {
    static final long FPS = 10;
    private GameEngine gameEngine;
    private boolean running = false;

    public GameLoopThread(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        long ticksPS = 1000 / FPS;

        while (running) {
            Canvas c = null;
            long startTime = System.currentTimeMillis();

            gameEngine.draw(ticksPS);

            long sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0) {
                    sleep(sleepTime);
                } else {
                    sleep(10);
                }
            } catch (Exception e) {
            }
        }
    }
}
