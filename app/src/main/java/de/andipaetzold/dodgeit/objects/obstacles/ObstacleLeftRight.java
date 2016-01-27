package de.andipaetzold.dodgeit.objects.obstacles;

import de.andipaetzold.dodgeit.R;
import de.andipaetzold.dodgeit.game.Display;
import de.andipaetzold.dodgeit.util.RandomExtension;

public class ObstacleLeftRight extends StaticObstacle {
    private float range = RandomExtension.nextFloat(50, 100);
    private float initX;

    public ObstacleLeftRight() {
        initX = RandomExtension.nextFloat(range, Display.getWidth() - getWidth() - range);
        position.x = initX;
        position.y = -getHeight();
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
    protected int getImg() {
        return R.drawable.obstacle_1x1;
    }

    @Override
    public void calcNewPosition(long delta, float scrollSpeed) {
        super.calcNewPosition(delta, scrollSpeed);
        position.x = initX + (float) Math.sin(position.y / 100) * range;
    }
}
