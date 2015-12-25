package de.andipaetzold.dodgeit.util;

import java.util.Random;

public class RandomExtension {
    private static Random random = new Random();

    public static float nextFloat(float least, float bound) {
        return random.nextFloat() * (bound - least) + least;
    }

    public static int nextInt(int least, int bound) {
        return random.nextInt(bound - least) + least;
    }
}
