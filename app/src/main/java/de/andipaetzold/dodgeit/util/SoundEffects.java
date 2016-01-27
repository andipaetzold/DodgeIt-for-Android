package de.andipaetzold.dodgeit.util;

import android.media.MediaPlayer;

import java.util.HashMap;
import java.util.Map;

import de.andipaetzold.dodgeit.App;
import de.andipaetzold.dodgeit.R;

public class SoundEffects {
    private static SoundEffects instance = new SoundEffects();
    private static boolean mute = false;
    private static Map<Integer, MediaPlayer> mediaPlayers = new HashMap<Integer, MediaPlayer>();

    static {
        int[] soundResources = new int[]{R.raw.crash};

        for (int res : soundResources) {
            mediaPlayers.put(res, MediaPlayer.create(App.getContext(), res));
        }
    }

    public static void Play(int res) {
        if (!Preferences.getSFXMuted()) {
            mediaPlayers.get(res).start();
        }
    }
}
