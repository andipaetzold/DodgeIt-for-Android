package de.andipaetzold.dodgeit.objects;

public abstract class GameObject {
    protected int x = 0;
    protected int y = 0;

    protected int width;
    protected int height;

    protected int img;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getImg() {
        return img;
    }
}
