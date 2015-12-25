package de.andipaetzold.dodgeit.objects;

public abstract class GameObject {
    protected int x;
    protected int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract int getImg();
}
