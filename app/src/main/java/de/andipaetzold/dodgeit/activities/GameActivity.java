package de.andipaetzold.dodgeit.activities;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import de.andipaetzold.dodgeit.R;
import de.andipaetzold.dodgeit.game.GameLoopThread;
import de.andipaetzold.dodgeit.game.InputEngine;
import de.andipaetzold.dodgeit.objects.background.Background;
import de.andipaetzold.dodgeit.objects.background.BackgroundFactory;
import de.andipaetzold.dodgeit.objects.character.CharacterFactory;
import de.andipaetzold.dodgeit.objects.obstacles.Obstacle;
import de.andipaetzold.dodgeit.objects.obstacles.ObstacleFactory;

public class GameActivity extends Activity implements OnClickListener {
    private enum GameStatus {
        COUNTDOWN, RUNNING, PAUSE, GAMEOVER
    }

    private GameLoopThread gameLoopThread;

    private BackgroundFactory backgroundFactory = new BackgroundFactory();
    private ObstacleFactory obstacleFactory = new ObstacleFactory(this);
    private CharacterFactory characterFactory = new CharacterFactory();

    private float scrollSpeed = 0.3f;

    private GameStatus status = GameStatus.RUNNING;

    private float score = 0;
    private float time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        SurfaceView sv = (SurfaceView) findViewById(R.id.game_surfaceview_game);
        sv.setOnClickListener(this);

        obstacleFactory = new ObstacleFactory(this);
        gameLoopThread = new GameLoopThread(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        InputEngine.getInstance().resume();
        gameLoopThread.restart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        InputEngine.getInstance().pause();
        gameLoopThread.pause();
    }

    private GameStatus tmpGameStatus;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.game_surfaceview_game:
                switch (status) {
                    case COUNTDOWN:
                    case RUNNING:
                        tmpGameStatus = status;
                        status = GameStatus.PAUSE;
                        break;

                    case PAUSE:
                        status = tmpGameStatus;
                        break;

                    case GAMEOVER:
                        break;
                }
                break;
        }
    }

    public void update(long delta) {
        if (status == GameStatus.RUNNING) {
            // calc position
            backgroundFactory.calcBackgrounds(delta, scrollSpeed);
            obstacleFactory.calcObstacles(delta, scrollSpeed);
            calcCharacter(delta);

            // add time
            time += delta * 0.001f;

            // add points
            addPoints(delta * 0.01f);

            // check collision
            checkCollision();
        }

        // draw
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.game_surfaceview_game);
        Canvas c = null;
        try {
            c = surfaceView.getHolder().lockCanvas();

            if (c == null) {
                return;
            }

            synchronized (surfaceView.getHolder()) {
                drawBackground(c);
                drawObstacles(c);
                drawCharacter(c);
                drawText(c);
            }
        } finally {
            if (c != null) {
                surfaceView.getHolder().unlockCanvasAndPost(c);
            }
        }
    }

    private void checkCollision() {
        List<Obstacle> obstacles = obstacleFactory.getObstacles();
        de.andipaetzold.dodgeit.objects.character.Character character = characterFactory.getCharacter();
        for (Obstacle obstacle : obstacles) {
            if (character.getPosition().x < obstacle.getPosition().x + obstacle.getWidth() &&
                    character.getPosition().x + character.getWidth() > obstacle.getPosition().x &&
                    character.getPosition().y < obstacle.getPosition().y + obstacle.getHeight() &&
                    character.getHeight() + character.getPosition().y > obstacle.getPosition().y) {
                collision();
                return;
            }
        }
    }

    private void collision() {
        status = GameStatus.GAMEOVER;

        Fragment frag = getFragmentManager().findFragmentByTag("dialog_score");
        if (frag != null) {
            getFragmentManager().beginTransaction().remove(frag).commit();
        }

        ScoreDialog scoreDialog = new ScoreDialog();
        scoreDialog.setScore((int) score);
        scoreDialog.setActivity(this);
        scoreDialog.show(getFragmentManager(), "dialog_score");
    }

    public void addPoints(float points) {
        score += points * scrollSpeed;
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

    private void drawText(Canvas c) {
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setTextSize(16);

        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.DOWN);

        c.drawText(df.format(score), 20, 20, p);
        c.drawText(df.format(time), 20, 50, p);
    }
}
