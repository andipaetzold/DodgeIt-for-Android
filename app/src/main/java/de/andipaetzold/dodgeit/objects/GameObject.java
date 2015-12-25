package de.andipaetzold.dodgeit.objects;

import android.graphics.Bitmap;

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

    public Bitmap getBitmap() {
        if (!GameObjectBitmapCache.containsBitmap(getImg())) {
            GameObjectBitmapCache.put(getImg(), getWidth(), getHeight());
        }

        return GameObjectBitmapCache.get(getImg());
    }

    public boolean isDisposable() {
        return Display.getHeight() <= position.y;
    }
}
