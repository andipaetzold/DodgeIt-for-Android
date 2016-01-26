package de.andipaetzold.dodgeit.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.andipaetzold.dodgeit.R;
import de.andipaetzold.dodgeit.util.BackgroundMusic;

public class MenuActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.menu_button_play).setOnClickListener(this);
        findViewById(R.id.menu_button_leaderboard).setOnClickListener(this);
        findViewById(R.id.menu_button_settings).setOnClickListener(this);
        findViewById(R.id.menu_button_about).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_button_play:
                startActivity(new Intent(this, GameActivity.class));
                break;
            case R.id.menu_button_leaderboard:
                startActivity(new Intent(this, LeaderboardActivity.class));
                break;
            case R.id.menu_button_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.menu_button_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        BackgroundMusic.getInstance().Play();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BackgroundMusic.getInstance().Pause();
    }
}
