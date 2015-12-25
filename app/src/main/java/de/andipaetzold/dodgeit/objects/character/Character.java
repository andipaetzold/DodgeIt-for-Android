package de.andipaetzold.dodgeit.objects.character;

import de.andipaetzold.dodgeit.R;
import de.andipaetzold.dodgeit.game.Display;
import de.andipaetzold.dodgeit.objects.GameObject;

public class Character extends GameObject {
    public Character() {
        position.x = (Display.getWidth() - getWidth()) / 2;
        position.y = Display.getHeight() - getHeight();
    }

    @Override
    public int getWidth() {
        return 128;
    }

    @Override
    public int getHeight() {
        return 128;
    }

    @Override
    public int getImg() {
        return R.drawable.character;
    }

    public void calcNewPosition(long delta, float[] orientation) {
        position.x += orientation[2] * delta;
        position.x = Math.max(position.x, 0);
        position.x = Math.min(position.x, Display.getWidth() - getWidth());

        position.y -= orientation[1] * delta;
        position.y = Math.max(position.y, 0);
        position.y = Math.min(position.y, Display.getHeight() - getHeight());
    }
}
