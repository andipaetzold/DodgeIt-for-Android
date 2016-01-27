package de.andipaetzold.dodgeit.util;


import android.content.Context;
import android.content.SharedPreferences;

import de.andipaetzold.dodgeit.App;

public class Preferences {
    private static final String PREF_KEY = "DodgeIt";
    private static final String KEY_MUSIC_MUTED = "MusicMuted";
    private static final String KEY_SFX_MUTED = "SFXMuted";

    private Preferences() {

    }

    private static SharedPreferences getSharedPreferences() {
        return App.getContext().getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
    }

    public static void setMusicMuted(boolean muted) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(KEY_MUSIC_MUTED, muted);
        editor.commit();

        if (muted) {
            BackgroundMusic.Mute();
        }else {
            BackgroundMusic.Unmute();
        }
    }

    public static boolean getMusicMuted() {
        return getSharedPreferences().getBoolean(KEY_MUSIC_MUTED, false);
    }

    public static void setSFXMuted(boolean muted) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(KEY_SFX_MUTED, muted);
        editor.commit();
    }

    public static boolean getSFXMuted() {
        return getSharedPreferences().getBoolean(KEY_SFX_MUTED, false);
    }
}
