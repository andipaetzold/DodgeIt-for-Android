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
    }
}
