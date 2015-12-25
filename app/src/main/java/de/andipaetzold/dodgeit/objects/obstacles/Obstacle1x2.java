package de.andipaetzold.dodgeit.objects.obstacles;

import de.andipaetzold.dodgeit.R;

public class Obstacle1x2 extends Obstacle {
    @Override
    public void calcNewPosition(long delta) {

    }

    @Override
    public int getWidth() {
        return 50;
    }

    @Override
    public int getHeight() {
        return 100;
    }

    @Override
    public int getImg() {
        return R.drawable.obstacle_1x2;
    }
}
