package de.andipaetzold.dodgeit.objects.background;

import de.andipaetzold.dodgeit.R;
import de.andipaetzold.dodgeit.game.Display;
import de.andipaetzold.dodgeit.objects.GameObject;

public class Background extends GameObject {
    public Background() {
        position.y = -getHeight();
    }

    @Override
    public int getWidth() {
        return Display.getWidth();
    }

    @Override
    public int getHeight() {
        float scale = Display.getWidth() / (float) 1366;
        return (int) (468 * scale);
    }

    @Override
    protected int getImg() {
        return R.drawable.background;
    }
    
    public void calcNewPosition(long delta, float scrollSpeed) {
        position.y += scrollSpeed * delta;
    }
}
