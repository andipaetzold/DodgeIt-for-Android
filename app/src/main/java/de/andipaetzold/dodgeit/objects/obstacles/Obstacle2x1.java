package de.andipaetzold.dodgeit.objects.obstacles;

import de.andipaetzold.dodgeit.R;

public class Obstacle2x1 extends StaticObstacle {
    @Override
    public int getWidth() {
        return 256;
    }

    @Override
    public int getHeight() {
        return 128;
    }

    @Override
    protected int getImg() {
        return R.drawable.obstacle_2x1;
    }
}
