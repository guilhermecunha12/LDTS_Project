package getcooked.model.game.elements.grabable.food;

import getcooked.viewer.game.ElementViewer;
import getcooked.viewer.game.PotatoViewer;

public class Potato extends Food {

    public Potato(int x, int y){
        super(x, y);
    }

    @Override
    public ElementViewer getViewer() {
        return new PotatoViewer();
    }
}
