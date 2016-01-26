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

    private static float getScale() {
       return metrics.widthPixels / (float) width;
    }

    public static float scale(float x) {
        return getScale() * x;
    }


    public static int scale(int x) {
        return (int) (getScale() * x);
    }

    public static Point scalePosition(Point p) {

        float x = p.x * getScale();
        float y = p.y * getScale();
        y += metrics.heightPixels - (height * getScale());

        return new Point(x, y);
    }

    public static Bitmap scaleBitmap(Bitmap bmp) {
        return Bitmap.createScaledBitmap(bmp, (int) (bmp.getWidth() * getScale()), (int) (bmp.getHeight() * getScale()), false);
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}
