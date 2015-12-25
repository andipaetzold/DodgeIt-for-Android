package de.andipaetzold.dodgeit.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceView;

import java.util.ArrayList;

import de.andipaetzold.dodgeit.objects.GameObject;
import de.andipaetzold.dodgeit.objects.character.Character;
import de.andipaetzold.dodgeit.objects.character.CharacterFactory;
import de.andipaetzold.dodgeit.objects.obstacles.Obstacle;
import de.andipaetzold.dodgeit.objects.obstacles.ObstacleFactory;
import de.andipaetzold.dodgeit.util.Point;

public class GameEngine {
    ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    Character character;
    private GameLoopThread gameLoopThread;
    private SurfaceView view;

    private float scrollSpeed = 0.3f;

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
            obstacle.calcNewPosition(delta, scrollSpeed);
        }
    }

    private void calcCharacter(long delta) {
        character.calcNewPosition(delta, InputEngine.getInstance().getOrientation());
    }

    private void drawBackground(Canvas c) {
        c.drawColor(Color.BLACK);
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
        Bitmap bmp = gameObject.getBitmap();
        Point position = gameObject.getPosition();
        c.drawBitmap(bmp, position.x, position.y, null);
    }

    public void resume() {
        InputEngine.getInstance().resume();
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
    }

    public void pause() {
        InputEngine.getInstance().pause();

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
