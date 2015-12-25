package de.andipaetzold.dodgeit.objects.obstacles;

import android.util.Log;

import java.util.concurrent.ThreadLocalRandom;

import de.andipaetzold.dodgeit.util.Logger;

public class ObstacleFactory {
    private Class[] obstacles = new Class[]{DefaultObstacle.class};

    public Obstacle getObstacle() {
        int selectedObstacle = ThreadLocalRandom.current().nextInt(0, obstacles.length - 1);
        @SuppressWarnings("unchecked")
        Class<? extends Obstacle> c = obstacles[selectedObstacle];

        Obstacle o = null;
        try {
            o = c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            Log.e(Logger.LOG_TAG, "Error instantiating an obstacle.", e);
        }

        return o;
    }
}
