package de.andipaetzold.dodgeit.objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;
import java.util.Map;

import de.andipaetzold.dodgeit.App;

public class GameObjectBitmapCache {
    private static Map<Integer, Bitmap> map = new HashMap<Integer, Bitmap>();

    public static boolean containsBitmap(int ressourceId) {
        return map.containsKey(ressourceId);
    }

    public static void put(int resourceId, int width, int height) {
        Bitmap bmp = BitmapFactory.decodeResource(App.getContext().getResources(), resourceId);
        map.put(resourceId, Bitmap.createScaledBitmap(bmp, width, height, false));
    }

    public static Bitmap get(int ressourceId) {
        if (map.containsKey(ressourceId)) {
            return map.get(ressourceId);
        }
        return null;
    }
}
