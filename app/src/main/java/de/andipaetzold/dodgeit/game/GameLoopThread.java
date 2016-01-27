package de.andipaetzold.dodgeit.game;

import de.andipaetzold.dodgeit.activities.GameActivity;

public class GameLoopThread extends Thread {
    private int TICKS_PER_SECOND = 30;
    private int SKIP_TICKS = 1000 / TICKS_PER_SECOND;

    private GameActivity gameActivity;
    private boolean running = false;

    public GameLoopThread(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
    }

    @Override
    public void run() {
        long sleepTime = 0;
        long nextTick  = System.currentTimeMillis();

        while (running) {
            if (sleepTime > 0) {
                gameActivity.update(SKIP_TICKS - sleepTime);
            }
            else {
                gameActivity.update(SKIP_TICKS);
            }

            nextTick += SKIP_TICKS;
            sleepTime = nextTick  - System.currentTimeMillis();
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
