package de.andipaetzold.dodgeit.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import de.andipaetzold.dodgeit.objects.obstacles.Obstacle;

public class GameEngine {
    ArrayList<Obstacle> obstacles = new ArrayList<>();
    Character character;
    private GameLoopThread gameLoopThread;
    private SurfaceView view;

    public GameEngine(SurfaceView surfaceView) {
        gameLoopThread = new GameLoopThread(this);

        view = surfaceView;
        view.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
    }

    public void draw(long delta) {
        // calc
        calcBackground(delta);
        calcObstacles(delta);
        calcCharacter(delta);

        // draw
        Canvas c = null;
        try {
            c = view.getHolder().lockCanvas();
            synchronized (view.getHolder()) {
                drawBackground(c);
                drawObstacles(c);
                drawCharacter(c);
            }
        } finally {
            if (c != null) {
                view.getHolder().unlockCanvasAndPost(c);
            }
        }
    }

    private void calcBackground(long delta) {

    }

    private void calcObstacles(long delta) {
        for (Obstacle obstacle : obstacles) {
            obstacle.calcNewPosition(delta);
        }
    }

    private void calcCharacter(long delta) {
    }

    private void drawBackground(Canvas c) {

    }

    private void drawCharacter(Canvas c) {

    }

    private void drawObstacles(Canvas c) {

    }

    public void resume() {

    }

    public void pause() {

    }
}
