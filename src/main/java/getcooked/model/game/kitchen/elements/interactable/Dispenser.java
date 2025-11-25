package getcooked.model.game.kitchen.elements.interactable;

import getcooked.model.game.kitchen.elements.Chef;

public abstract class Dispenser extends Interactable {
    executeBehaviour strategy;

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

    public void setStrategy(ExecuteBehaviour strategy) {
        this.strategy = strategy;
    }
}
