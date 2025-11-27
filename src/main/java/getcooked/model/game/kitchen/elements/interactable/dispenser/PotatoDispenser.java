package getcooked.model.game.kitchen.elements.interactable.dispenser;

import getcooked.model.game.kitchen.elements.grabable.Grabable;
import getcooked.model.game.kitchen.elements.grabable.food.Potato;

public class PotatoDispenser extends Dispenser {

    public PotatoDispenser(int x, int y) {
        super(x, y);
    }

    @Override
    public Grabable createItem(int x, int y) {
        return new Potato(getX(), getY());
    }
}
