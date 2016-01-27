package de.andipaetzold.dodgeit.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import de.andipaetzold.dodgeit.game.Display;
import de.andipaetzold.dodgeit.util.Point;

public abstract class GameObject {
    protected Point position = new Point(0, 0);

    public Point getPosition() {
        return position;
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

    public void draw(Canvas c) {
        c.drawBitmap(getBitmap(), position.x, position.y, null);
    }

    public boolean isDisposable() {
        return Display.getHeight() <= position.y;
    }
}
