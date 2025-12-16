package getcooked.model.game.elements.grabable.food;

import getcooked.viewer.game.BeefViewer;
import getcooked.viewer.game.ElementViewer;

public class Beef extends Food {

    public Beef(int x, int y) {
        super(x, y);
    }

    @Override
    public ElementViewer getViewer() {
        return new BeefViewer();
    }
}
