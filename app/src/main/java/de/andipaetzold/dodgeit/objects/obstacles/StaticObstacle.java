package de.andipaetzold.dodgeit.objects.obstacles;

import de.andipaetzold.dodgeit.game.Display;
import de.andipaetzold.dodgeit.util.RandomExtension;

public abstract class StaticObstacle extends Obstacle {
    public StaticObstacle() {
        position.x = RandomExtension.nextInt(0, Display.getWidth() - getWidth());
        position.y = -getHeight();
    }

    @Override
    public void calcNewPosition(long delta, float scrollSpeed) {
        position.y += scrollSpeed * delta;
    }
}
