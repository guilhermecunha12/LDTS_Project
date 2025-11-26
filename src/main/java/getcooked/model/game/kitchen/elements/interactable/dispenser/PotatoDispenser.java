package getcooked.model.game.kitchen.elements.interactable.dispenser;

import getcooked.model.game.kitchen.elements.grabable.Grabable;

public class PotatoDispenser extends Dispenser {

    public PotatoDispenser(int x, int y) {
        super(x, y);
    }

    @Override
    public Grabable createItem(){
        return new Potato(getX(), getY());
    }
}
