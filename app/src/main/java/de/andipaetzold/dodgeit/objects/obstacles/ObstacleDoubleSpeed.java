package de.andipaetzold.dodgeit.objects.obstacles;

import de.andipaetzold.dodgeit.R;

public class ObstacleDoubleSpeed extends StaticObstacle {
    @Override
    public int getWidth() {
        return 128;
    }

    @Override
    public int getHeight() {
        return 128;
    }

    @Override
    protected int getImg() {
        return R.drawable.obstacle_1x1;
    }

    @Override
    public void calcNewPosition(long delta, float scrollSpeed) {
        super.calcNewPosition(delta, scrollSpeed * 2);
    }
}
