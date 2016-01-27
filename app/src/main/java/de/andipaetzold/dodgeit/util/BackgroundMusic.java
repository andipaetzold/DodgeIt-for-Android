package de.andipaetzold.dodgeit.util;

import android.media.MediaPlayer;

import de.andipaetzold.dodgeit.App;
import de.andipaetzold.dodgeit.R;

public class BackgroundMusic implements MediaPlayer.OnCompletionListener {
    private int current = 0;
    private MediaPlayer[] mediaPlayers;

    private static BackgroundMusic instance = new BackgroundMusic();
    public static BackgroundMusic getInstance() {
        return instance;
    }

    public BackgroundMusic() {
        int[] soundResources = new int[] { R.raw.music01, R.raw.music02, R.raw.music03};

        mediaPlayers = new MediaPlayer[soundResources.length];
        for(int i = 0; i <= soundResources.length - 1; i++) {
            mediaPlayers[i] = MediaPlayer.create(App.getContext(), soundResources[i]);
            mediaPlayers[i].setOnCompletionListener(this);
        }

        Play();
    }

    public void Play() {
        if (!mediaPlayers[current].isPlaying()) {
            mediaPlayers[current].start();
        }
    }

    public void Pause() {
        if (mediaPlayers[current].isPlaying()) {
            mediaPlayers[current].pause();
        }
    }

    public void Mute() {
        mediaPlayers[current].setVolume(0, 0);
    }

    public void Unmute() {
        mediaPlayers[current].setVolume(1, 1);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        current = (current + 1) % mediaPlayers.length;
        Play();
    }
}
