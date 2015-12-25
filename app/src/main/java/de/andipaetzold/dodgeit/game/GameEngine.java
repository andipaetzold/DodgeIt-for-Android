package de.andipaetzold.dodgeit.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.List;

import de.andipaetzold.dodgeit.objects.GameObject;
import de.andipaetzold.dodgeit.objects.background.Background;
import de.andipaetzold.dodgeit.objects.background.BackgroundFactory;
import de.andipaetzold.dodgeit.objects.character.CharacterFactory;
import de.andipaetzold.dodgeit.objects.obstacles.Obstacle;
import de.andipaetzold.dodgeit.objects.obstacles.ObstacleFactory;
import de.andipaetzold.dodgeit.util.Point;

public class GameEngine {
    private GameLoopThread gameLoopThread;
    private SurfaceView view;

    private BackgroundFactory backgroundFactory = new BackgroundFactory();
    private ObstacleFactory obstacleFactory = new ObstacleFactory();
    private CharacterFactory characterFactory = new CharacterFactory();

    private float scrollSpeed = 0.3f;

    public GameEngine(SurfaceView surfaceView) {
        gameLoopThread = new GameLoopThread(this);
        view = surfaceView;
    }

    public void update(long delta) {
        // calc
        backgroundFactory.calcBackgrounds(delta, scrollSpeed);
        obstacleFactory.calcObstacles(delta, scrollSpeed);
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

    private void calcCharacter(long delta) {
        characterFactory.getCharacter().calcNewPosition(delta, InputEngine.getInstance().getOrientation());
    }

    private void drawBackground(Canvas c) {
        List<Background> backgrounds = backgroundFactory.getBackgrounds();
        for (Background background : backgrounds) {
            drawGameObject(c, background);
        }
    }

    private void drawCharacter(Canvas c) {
        drawGameObject(c, characterFactory.getCharacter());
    }

    private void drawObstacles(Canvas c) {
        List<Obstacle> obstacles = obstacleFactory.getObstacles();
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
        gameLoopThread.restart();
    }

    public void pause() {
        InputEngine.getInstance().pause();
        gameLoopThread.pause();
    }
}
