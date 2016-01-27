package de.andipaetzold.dodgeit.objects.obstacles;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import de.andipaetzold.dodgeit.App;
import de.andipaetzold.dodgeit.activities.GameActivity;
import de.andipaetzold.dodgeit.util.RandomExtension;

public class ObstacleFactory {
    private final GameActivity gameActivity;
    private List<Class<? extends Obstacle>> obstacleClasses = new ArrayList<Class<? extends Obstacle>>();

    private List<Obstacle> obstacles = new ArrayList<Obstacle>();

    public ObstacleFactory(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
        
        obstacleClasses.add(Obstacle1x1.class);
        obstacleClasses.add(Obstacle2x1.class);
        obstacleClasses.add(Obstacle1x2.class);
    }

    private long timeUntilSpawn = 0;
    public void calcObstacles(long delta, float scrollSpeed) {
        // calc existing
        Iterator<Obstacle> iterator = obstacles.iterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            obstacle.calcNewPosition(delta, scrollSpeed);
            if (obstacle.isDisposable()) {
                gameActivity.addPoints(obstacle.getPoints() * 0.005f);
                iterator.remove();
            }
        }

        // spawn
        timeUntilSpawn -= delta;
        if (timeUntilSpawn < 0) {
            obstacles.add(createObstacle());
            timeUntilSpawn = 2000;
        }
    }

    public List<Obstacle> getObstacles() {
        return Collections.unmodifiableList(obstacles);
    }

    private Obstacle createObstacle() {
        int selectedObstacle = RandomExtension.nextInt(0, obstacleClasses.size());
        Class<? extends Obstacle> c = obstacleClasses.get(selectedObstacle);

        Obstacle o = null;
        try {
            o = c.newInstance();
        } catch (InstantiationException e) {
            Log.e(App.LOG_TAG, "Error instantiating an obstacle.", e);
        } catch (IllegalAccessException e) {
            Log.e(App.LOG_TAG, "Error instantiating an obstacle.", e);
        }

        return o;
    }
}
