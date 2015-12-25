package de.andipaetzold.dodgeit.objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;
import java.util.Map;

import de.andipaetzold.dodgeit.App;
import de.andipaetzold.dodgeit.game.Display;

public class GameObjectBitmapCache {
    private static Map<Integer, Bitmap> map = new HashMap<Integer, Bitmap>();

    public static boolean containsBitmap(int ressourceId) {
        return map.containsKey(ressourceId);
    }

    public static void put(int ressourceId, int width, int height) {
        Bitmap bmp = BitmapFactory.decodeResource(App.getContext().getResources(), ressourceId);
        bmp = Bitmap.createScaledBitmap(bmp, width, height, false);
        map.put(ressourceId, Display.scaleBitmap(bmp));
    }

    public static Bitmap get(int ressourceId) {
        if (map.containsKey(ressourceId)) {
            return map.get(ressourceId);
        }
        return null;
    }
}
