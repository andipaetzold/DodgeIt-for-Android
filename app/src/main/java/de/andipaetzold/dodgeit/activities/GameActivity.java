package de.andipaetzold.dodgeit.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceView;

import de.andipaetzold.dodgeit.R;
import de.andipaetzold.dodgeit.game.GameEngine;

public class GameActivity extends Activity {
    private GameEngine gameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        SurfaceView view = (SurfaceView) findViewById(R.id.game_surfaceview_game);
        gameEngine = new GameEngine(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameEngine.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameEngine.pause();
    }
}
