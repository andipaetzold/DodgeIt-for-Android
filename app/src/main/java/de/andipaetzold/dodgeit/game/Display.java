package de.andipaetzold.dodgeit.game;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import de.andipaetzold.dodgeit.App;

public class Display {
    private static int width = 720;
    private static int height = 1280;

    private static DisplayMetrics metrics = new DisplayMetrics();

    static {
        WindowManager wm = (WindowManager) App.getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}
