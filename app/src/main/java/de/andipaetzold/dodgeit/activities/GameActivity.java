package de.andipaetzold.dodgeit.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;

import de.andipaetzold.dodgeit.R;
import de.andipaetzold.dodgeit.game.GameEngine;

public class GameActivity extends Activity implements OnClickListener {
    private GameEngine gameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        SurfaceView sv = (SurfaceView) findViewById(R.id.game_surfaceview_game);
        sv.setOnClickListener(this);

        gameEngine = new GameEngine(sv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameEngine.engineResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameEngine.enginePause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.game_surfaceview_game:
                gameEngine.onClick();
                break;
        }
    }
}
