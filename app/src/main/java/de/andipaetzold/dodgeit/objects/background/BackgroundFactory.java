package de.andipaetzold.dodgeit.objects.background;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import de.andipaetzold.dodgeit.game.Display;

public class BackgroundFactory {
    private List<Background> backgrounds = new ArrayList<Background>();

    public List<Background> getBackgrounds() {
        return Collections.unmodifiableList(backgrounds);
    }

    public BackgroundFactory() {
        int y = 0;
        while (y < Display.getHeight()) {
            Background background = new Background(y);
            backgrounds.add(background);
            y += background.getHeight();
        }
    }

    public void calcBackgrounds(long delta, float scrollSpeed) {
        float y = Float.MAX_VALUE;

        Iterator<Background> iterator = backgrounds.iterator();

        while (iterator.hasNext()) {
            Background background = iterator.next();
            background.calcNewPosition(delta, scrollSpeed);
            y = Math.min(background.getPosition().y, y);

            if (background.isDisposable()) {
                iterator.remove();
            }
        }

        if (y > 0) {
            backgrounds.add(new Background(y));
        }
    }
}
