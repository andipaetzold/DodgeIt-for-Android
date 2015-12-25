package de.andipaetzold.dodgeit.objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import de.andipaetzold.dodgeit.App;
import de.andipaetzold.dodgeit.game.Display;
import de.andipaetzold.dodgeit.util.Point;

public abstract class GameObject {
    protected Point position = new Point(0, 0);

    public Point getPosition() {
        return Display.scalePosition(position);
    }

    public abstract int getWidth();

    public abstract int getHeight();

    protected abstract int getImg();

    private Bitmap scaledImg = null;
    public Bitmap getBitmap() {
        if (scaledImg == null) {
            scaledImg = BitmapFactory.decodeResource(App.getContext().getResources(), getImg());
            scaledImg = Bitmap.createScaledBitmap(scaledImg, getWidth(), getHeight(), false);
            scaledImg = Display.scaleBitmap(scaledImg);
        }

        return scaledImg;
    }

    public boolean isDisposable() {
        return Display.getHeight() <= position.y;
    }
}
