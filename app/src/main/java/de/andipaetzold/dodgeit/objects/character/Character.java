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
}
