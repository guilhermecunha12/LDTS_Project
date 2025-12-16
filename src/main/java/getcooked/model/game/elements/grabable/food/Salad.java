package getcooked.model.game.elements.grabable.food;

import getcooked.viewer.game.ElementViewer;
import getcooked.viewer.game.SaladViewer;

public class Salad extends Food {

    public Salad(int x, int y) {
        super(x, y);
    }

    @Override
    public ElementViewer getViewer() {
        return new SaladViewer();
    }
}
