package getcooked.model.game.kitchen.elements.interactable.dispenser;

import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.grabable.Grabable;
import getcooked.model.game.kitchen.elements.interactable.Interactable;

public abstract class Dispenser extends Interactable {
    //Strategys are imutable of each dispenser
    protected final executeBehaviour strategy;

    public Dispenser(int x, int y) {
        super(x, y);
    }

    protected abstract Grabable createItem();

    @Override
    public void interact(Chef chef){
        if(!chef.isHoldingSomething()){
            //missing somehting for the execBehaviour
            chef.pickUp(createItem());
        }
    }

}
