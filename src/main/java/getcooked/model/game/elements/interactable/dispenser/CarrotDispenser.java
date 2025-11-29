package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.food.Carrot;

public class CarrotDispenser extends Dispenser {

    public CarrotDispenser(int x, int y) {
        super(x, y);
    }

    @Override
    public Grabable createItem(int x, int y) {
        return new Carrot(getX(),getY());
    }
}
