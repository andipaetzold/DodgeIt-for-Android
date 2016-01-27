package de.andipaetzold.dodgeit.util;

import android.media.MediaPlayer;

import de.andipaetzold.dodgeit.App;
import de.andipaetzold.dodgeit.R;

public class BackgroundMusic {
    private static int current = 0;
    private static MediaPlayer[] mediaPlayers;

    static {
        int[] soundResources = new int[]{R.raw.music01, R.raw.music02, R.raw.music03};

        mediaPlayers = new MediaPlayer[soundResources.length];
        for (int i = 0; i <= soundResources.length - 1; i++) {
            mediaPlayers[i] = MediaPlayer.create(App.getContext(), soundResources[i]);
            mediaPlayers[i].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    current = (current + 1) % mediaPlayers.length;
                    Play();
                }
            });
        }

        if (Preferences.getMusicMuted()) {
            Mute();
        }

        Play();
    }

    public static void Play() {
        if (!mediaPlayers[current].isPlaying()) {
            mediaPlayers[current].start();
        }
    }

    public static void Pause() {
        if (mediaPlayers[current].isPlaying()) {
            mediaPlayers[current].pause();
        }
    }

    public static void Unmute() {
        for (MediaPlayer mp : mediaPlayers) {
            mp.setVolume(1, 1);
        }
    }

    public static void Mute() {
        for (MediaPlayer mp : mediaPlayers) {
            mp.setVolume(0, 0);
        }
    }
}
