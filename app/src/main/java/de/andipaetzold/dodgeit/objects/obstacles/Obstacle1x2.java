package de.andipaetzold.dodgeit.objects.obstacles;

import de.andipaetzold.dodgeit.R;

public class Obstacle1x2 extends StaticObstacle {
    @Override
    public int getWidth() {
        return 128;
    }

    @Override
    public int getHeight() {
        return 256;
    }

    @Override
    protected int getImg() {
        return R.drawable.obstacle_1x2;
    }
}
