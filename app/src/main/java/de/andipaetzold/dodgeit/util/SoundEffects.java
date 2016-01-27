package de.andipaetzold.dodgeit.util;

import android.media.MediaPlayer;

import java.util.HashMap;
import java.util.Map;

import de.andipaetzold.dodgeit.App;
import de.andipaetzold.dodgeit.R;

public class SoundEffects {
    private static SoundEffects instance = new SoundEffects();
    private Map<Integer, MediaPlayer> mediaPlayers = new HashMap<Integer, MediaPlayer>();

    public SoundEffects() {
        int[] soundResources = new int[]{R.raw.crash};

        for (int res : soundResources) {
            mediaPlayers.put(res, MediaPlayer.create(App.getContext(), res));
        }
    }

    public static SoundEffects getInstance() {
        return instance;
    }

    public void Play(int res) {
        mediaPlayers.get(res).start();
    }
}
