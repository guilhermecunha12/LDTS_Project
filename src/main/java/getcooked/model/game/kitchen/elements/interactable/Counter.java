package getcooked.model.game.kitchen.elements.interactable;

import getcooked.model.game.kitchen.elements.Chef;

public class Counter extends Interactable{

    private Grabable item;

    public Counter(int x,int y){
        super(x,y);
    }

    @Override
    public void interact(Chef chef) {
        if(!chef.isHoldingSomething() && item != null){
            chef.pickUp(item);
            item = null;
        }else if(chef.isHoldingSomething() && item == null){
            item = chef.drop();
        }
    }
}
