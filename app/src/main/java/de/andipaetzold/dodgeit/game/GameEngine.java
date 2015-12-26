package de.andipaetzold.dodgeit.game;

import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.List;

import de.andipaetzold.dodgeit.objects.background.Background;
import de.andipaetzold.dodgeit.objects.background.BackgroundFactory;
import de.andipaetzold.dodgeit.objects.character.Character;
import de.andipaetzold.dodgeit.objects.character.CharacterFactory;
import de.andipaetzold.dodgeit.objects.obstacles.Obstacle;
import de.andipaetzold.dodgeit.objects.obstacles.ObstacleFactory;

public class GameEngine {
    private enum GameStatus {
        COUNTDOWN, RUNNING, PAUSE, GAMEOVER
    }

    private GameLoopThread gameLoopThread;
    private SurfaceView view;

    private BackgroundFactory backgroundFactory = new BackgroundFactory();
    private ObstacleFactory obstacleFactory = new ObstacleFactory();
    private CharacterFactory characterFactory = new CharacterFactory();

    private float scrollSpeed = 0.3f;
    private GameStatus status = GameStatus.RUNNING;

    public GameEngine(SurfaceView surfaceView) {
        gameLoopThread = new GameLoopThread(this);
        view = surfaceView;
    }

    public void update(long delta) {
        switch (status) {
            case RUNNING:
                // calc position
                backgroundFactory.calcBackgrounds(delta, scrollSpeed);
                obstacleFactory.calcObstacles(delta, scrollSpeed);
                calcCharacter(delta);

                // check collision
                checkCollision();
                break;
        }

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

    private void checkCollision() {
        List<Obstacle> obstacles = obstacleFactory.getObstacles();
        Character character = characterFactory.getCharacter();
        for (Obstacle obstacle : obstacles) {
            if (character.getPosition().x < obstacle.getPosition().x + obstacle.getWidth() &&
                    character.getPosition().x + character.getWidth() > obstacle.getPosition().x &&
                    character.getPosition().y < obstacle.getPosition().y + obstacle.getHeight() &&
                    character.getHeight() + character.getPosition().y > obstacle.getPosition().y) {
                collision();
            }
        }
    }

    private void collision() {
        status = GameStatus.GAMEOVER;
    }

    private void calcCharacter(long delta) {
        characterFactory.getCharacter().calcNewPosition(delta, InputEngine.getInstance().getOrientation());
    }

    private void drawBackground(Canvas c) {
        List<Background> backgrounds = backgroundFactory.getBackgrounds();
        for (Background background : backgrounds) {
            background.draw(c);
        }
    }

    private void drawCharacter(Canvas c) {
        characterFactory.getCharacter().draw(c);
    }

    private void drawObstacles(Canvas c) {
        List<Obstacle> obstacles = obstacleFactory.getObstacles();
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(c);
        }
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
