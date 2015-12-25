package de.andipaetzold.dodgeit.game;

public class GameLoopThread extends Thread {
    static final long FPS = 60;
    private GameEngine gameEngine;
    private boolean running = false;

    public GameLoopThread(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long sleepTime = 0;
        while (running) {
            long startTime = System.currentTimeMillis();

            gameEngine.update(ticksPS + sleepTime);

            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
            try {
                sleepTime = Math.max(sleepTime, 10);
                sleep(sleepTime);
            } catch (Exception e) {
            }
        }
    }

    public void restart() {
        running = true;
        start();
    }

    public void pause() {
        boolean retry = true;
        running = false;
        while (retry) {
            try {
                join();
                retry = false;
            } catch (InterruptedException ignored) {
            }
        }
    }
}
