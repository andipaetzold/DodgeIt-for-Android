package de.andipaetzold.dodgeit.activities;

import android.app.Activity;
import android.os.Bundle;

import de.andipaetzold.dodgeit.R;
import de.andipaetzold.dodgeit.util.BackgroundMusic;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
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
