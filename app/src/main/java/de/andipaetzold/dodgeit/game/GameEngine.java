package de.andipaetzold.dodgeit.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.ArrayList;

import de.andipaetzold.dodgeit.objects.character.Character;

import de.andipaetzold.dodgeit.App;
import de.andipaetzold.dodgeit.objects.GameObject;
import de.andipaetzold.dodgeit.objects.character.CharacterFactory;
import de.andipaetzold.dodgeit.objects.obstacles.Obstacle;

public class GameEngine {
    ArrayList<Obstacle> obstacles = new ArrayList<>();
    Character character;
    private GameLoopThread gameLoopThread;
    private SurfaceView view;

    public GameEngine(SurfaceView surfaceView) {
        gameLoopThread = new GameLoopThread(this);
        view = surfaceView;

        character = new CharacterFactory().getCharacter();
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

            if (c == null) {
                return;
            }

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
        drawGameObject(c, character);
    }

    private void drawObstacles(Canvas c) {
        for (Obstacle obstacle : obstacles) {
            drawGameObject(c, obstacle);
        }
    }

    private void drawGameObject(Canvas c, GameObject gameObject) {
        Bitmap bmp = BitmapFactory.decodeResource(App.getContext().getResources(), gameObject.getImg());
        c.drawBitmap(bmp, gameObject.getX(), gameObject.getY(), null);
    }

    public void resume() {
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
    }

    public void pause() {
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
}
