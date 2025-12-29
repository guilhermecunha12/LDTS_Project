package getcooked.model.game.elements.grabable;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Element;
import getcooked.model.game.elements.interactable.kitchenware.Kitchenware;

// Class to aggregate Plate and Food classes
public abstract class Grabable extends Element {

    protected Grabable(int x, int y) {
        super(x, y);
    }

    // Part of visitor pattern
    public abstract void interactWithKitchenware(Kitchenware kitchenware, Chef chef);
}
