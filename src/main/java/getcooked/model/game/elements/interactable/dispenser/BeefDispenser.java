package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.food.Beef;

public class BeefDispenser extends Dispenser {
    public BeefDispenser(int x, int y) {
        super(x, y);
    }

    @Override
    protected Grabable createItem(int x, int y) {
        return new Beef(x,y);
    }
}
