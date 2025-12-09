package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.food.Potato;

public class PotatoDispenser extends Dispenser {

    public PotatoDispenser(int x, int y) {
        super(x, y);
    }

    @Override
    public Grabable createItem(int x, int y) {
        return new Potato(x, y);
    }
}
