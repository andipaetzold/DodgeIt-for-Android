package de.andipaetzold.dodgeit.objects.background;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BackgroundFactory {
    private List<Background> backgrounds = new ArrayList<Background>();

    public List<Background> getBackgrounds() {
        return Collections.unmodifiableList(backgrounds);
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
