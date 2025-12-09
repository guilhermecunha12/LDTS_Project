package getcooked.model.game.elements.grabable;

import getcooked.model.Position;
import getcooked.model.game.elements.Element;


public abstract class Grabable extends Element {

    protected Grabable(int x, int y) {
        super(x,y);
    }

    // mark placed; clears holder
    public void placeAt(Position pos) {
        this.setPosition(pos);
    }
}
