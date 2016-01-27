package de.andipaetzold.dodgeit.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import de.andipaetzold.dodgeit.R;
import de.andipaetzold.dodgeit.util.BackgroundMusic;

public class AboutActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        findViewById(R.id.about_button_github).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.about_button_github:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/andipaetzold/DodgeIt-for-Android")));
                break;
        }
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
}
