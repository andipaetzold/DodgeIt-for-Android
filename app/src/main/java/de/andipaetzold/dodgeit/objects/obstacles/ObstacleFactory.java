package de.andipaetzold.dodgeit.objects.obstacles;

import android.util.Log;

import de.andipaetzold.dodgeit.util.Logger;
import de.andipaetzold.dodgeit.util.RandomExtension;

public class ObstacleFactory {
    private Class[] obstacles = new Class[]{Obstacle1x1.class, Obstacle2x1.class, Obstacle1x2.class};

    public Obstacle getObstacle() {
        int selectedObstacle = RandomExtension.nextInt(0, obstacles.length - 1);
        @SuppressWarnings("unchecked")
        Class<? extends Obstacle> c = obstacles[selectedObstacle];

        Obstacle o = null;
        try {
            o = c.newInstance();
        } catch (InstantiationException e) {
            Log.e(Logger.LOG_TAG, "Error instantiating an obstacle.", e);
        } catch (IllegalAccessException e) {
            Log.e(Logger.LOG_TAG, "Error instantiating an obstacle.", e);
        }

        return o;
    }
}
