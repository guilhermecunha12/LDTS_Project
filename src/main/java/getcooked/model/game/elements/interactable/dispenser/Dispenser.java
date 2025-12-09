package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.Position;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.interactable.Interactable;

public abstract class Dispenser extends Interactable {

    public Dispenser(int x, int y) {
        super(x, y);
    }

    protected abstract Grabable createItem(int x, int y);

    @Override
    public void interact(Chef chef){
        if(!chef.isHoldingSomething()){
            Grabable newItem = this.createItem(chef.getX(), chef.getY());
            chef.pickUp(newItem);
        }
    }

}
