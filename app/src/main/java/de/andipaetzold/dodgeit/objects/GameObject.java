package de.andipaetzold.dodgeit.objects;

public abstract class GameObject {
    protected float x;
    protected float y;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract int getImg();
}
