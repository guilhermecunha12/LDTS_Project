package getcooked.model.game.elements.interactable;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Element;

public abstract class Interactable extends Element {
    protected Interactable(int x, int y) {
        super(x, y);
    }

    public boolean isBlocking(){
        return true;
    }

    public abstract void interact(Chef chef);
}
