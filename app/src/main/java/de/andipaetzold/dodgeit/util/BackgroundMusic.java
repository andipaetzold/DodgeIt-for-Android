package de.andipaetzold.dodgeit.util;

import android.media.MediaPlayer;

import de.andipaetzold.dodgeit.App;
import de.andipaetzold.dodgeit.R;

public class BackgroundMusic implements MediaPlayer.OnCompletionListener {
    private int current = 0;
    private int[] soundResources = new int[] { R.raw.music01, R.raw.music02, R.raw.music03};
    private MediaPlayer player;

    private static BackgroundMusic instance = new BackgroundMusic();
    public static BackgroundMusic getInstance() {
        return instance;
    }

    public BackgroundMusic() {
        playSong(soundResources[current]);
    }

    public void Play() {
        if (!player.isPlaying()) {
            player.start();
        }
    }

    public void Pause() {
        if (player.isPlaying()) {
            player.pause();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        current = (current++) % soundResources.length;
        playSong(soundResources[current]);
    }

    private void playSong(int res) {
        if (player != null) {
            player.stop();
            player = null;
        }

        player = MediaPlayer.create(App.getContext(), R.raw.music01);
        player.setOnCompletionListener(this);
        player.setLooping(false);
        player.setVolume(1.0f, 1.0f);
        player.start();
    }
}
