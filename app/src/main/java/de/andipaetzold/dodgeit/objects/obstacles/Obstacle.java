package de.andipaetzold.dodgeit.objects.obstacles;

import de.andipaetzold.dodgeit.objects.GameObject;

public abstract class Obstacle extends GameObject {
    public abstract void calcNewPosition(long delta, float scrollSpeed);
}
