package getcooked.model.game.kitchen.elements.interactable;

import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.grabable.Grabable;

public class DeliverySpot extends Interactable {

    public DeliverySpot(int x, int y) {
        super(x,y);
    }

    @Override
    public void interact(Chef chef) {
        if(chef.isHoldingSomething()){
            //TODO
            Grabable item = chef.drop();
        }
    }
}
