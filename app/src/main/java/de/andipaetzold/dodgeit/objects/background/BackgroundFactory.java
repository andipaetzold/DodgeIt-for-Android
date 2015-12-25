package de.andipaetzold.dodgeit.objects.background;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.andipaetzold.dodgeit.util.Logger;

public class BackgroundFactory {
    private ArrayList<Background> backgrounds = new ArrayList<Background>();

    public List<Background> getBackgrounds() {
        return backgrounds;
    }

    public void calcBackgrounds(long delta, float scrollSpeed) {
        boolean createBackground = true;

        Iterator<Background> iterator = backgrounds.iterator();

        while (iterator.hasNext()) {
            Background background = iterator.next();
            background.calcNewPosition(delta, scrollSpeed);
            if (background.isDisposable()) {
                iterator.remove();
            } else if (background.getPosition().y <= 0) {
                createBackground = false;
            }
        }

        if (createBackground) {
            backgrounds.add(new Background());
        }

        Log.i(Logger.LOG_TAG, String.valueOf(  backgrounds.size()));
    }
}