package de.andipaetzold.dodgeit.objects.character;

import de.andipaetzold.dodgeit.R;
import de.andipaetzold.dodgeit.objects.GameObject;

public class Character extends GameObject {
    @Override
    public int getWidth() {
        return 50;
    }

    @Override
    public int getHeight() {
        return 50;
    }

    @Override
    public int getImg() {
        return R.drawable.character;
    }

    public void calcNewPosition(long delta, float[] orientation) {
        x += orientation[2] * 10;
        x = Math.max(x, 0);
        x = Math.min(x, 300);

        y -= orientation[1] * 10;
        y = Math.max(y, 0);
        y = Math.min(y, 300);
    }
}
