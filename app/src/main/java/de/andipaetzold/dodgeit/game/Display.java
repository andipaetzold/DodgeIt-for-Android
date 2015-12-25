package de.andipaetzold.dodgeit.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import de.andipaetzold.dodgeit.App;
import de.andipaetzold.dodgeit.util.Point;

public class Display {
    private static int width = 720;
    private static int height = 1280;

    private static DisplayMetrics metrics = new DisplayMetrics();

    static {
        WindowManager wm = (WindowManager) App.getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
    }

    public static Point scalePosition(Point p) {
        float scale = metrics.widthPixels / (float) width;

        float x = p.x * scale;
        float y = p.y * scale;
        y += metrics.heightPixels - (height * scale);

        return new Point(x, y);
    }

    public static Bitmap scaleBitmap(Bitmap bmp) {
        float scale = metrics.widthPixels / (float) width;

        return Bitmap.createScaledBitmap(bmp, (int) (bmp.getWidth() * scale), (int) (bmp.getHeight() * scale), false);
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}
