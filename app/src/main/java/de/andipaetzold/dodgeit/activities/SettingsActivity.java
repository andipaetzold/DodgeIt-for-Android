package de.andipaetzold.dodgeit.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import de.andipaetzold.dodgeit.R;
import de.andipaetzold.dodgeit.util.BackgroundMusic;
import de.andipaetzold.dodgeit.util.Preferences;

public class SettingsActivity extends Activity implements CompoundButton.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        CheckBox musicCheckBox = (CheckBox) findViewById(R.id.settings_checkbox_music);
        musicCheckBox.setOnCheckedChangeListener(this);
        musicCheckBox.setChecked(!Preferences.getMusicMuted());

        CheckBox sfxCheckBox = (CheckBox) findViewById(R.id.settings_checkbox_sfx);
        sfxCheckBox.setOnCheckedChangeListener(this);
        sfxCheckBox.setChecked(!Preferences.getSFXMuted());
    }

    @Override
    protected void onResume() {
        super.onResume();
        BackgroundMusic.Play();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BackgroundMusic.Pause();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.settings_checkbox_music:
                Preferences.setMusicMuted(!isChecked);
                break;

            case R.id.settings_checkbox_sfx:
                Preferences.setSFXMuted(!isChecked);
                break;
        }
    }
}
