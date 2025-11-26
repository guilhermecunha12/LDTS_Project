package getcooked.model.game.kitchen.elements.interactable.dispenser;

import getcooked.model.game.kitchen.elements.grabable.Grabable;
import getcooked.model.game.kitchen.elements.grabable.food.Carrot;

public class CarrotDispenser extends Dispenser {

    public CarrotDispenser(int x, int y) {
        super(x, y);
    }

    @Override
    public Grabable createItem(){
        return new Carrot(getX(),getY());
    }
}
