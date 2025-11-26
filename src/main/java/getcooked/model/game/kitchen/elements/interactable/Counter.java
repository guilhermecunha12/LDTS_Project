package getcooked.model.game.kitchen.elements.interactable;

import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.grabable.Grabable;

public class Counter extends Interactable{

    private Grabable item;

    public Counter(int x,int y){
        super(x,y);
    }

    @Override
    public void interact(Chef chef) {
        //pegar da counter
        if(!chef.isHoldingSomething() && item != null){
            chef.pickUp(item);
            item = null;
        }
        else if(chef.isHoldingSomething() && item == null){
            Grabable tmp = chef.drop();
            //Passar a posição deste counter
            tmp.placeAt(getPosition());
            item = tmp;
        }
    }
}
