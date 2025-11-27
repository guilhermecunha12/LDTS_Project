package getcooked.model.game.kitchen.elements.interactable;

import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.grabable.Grabable;

public class Trash extends Interactable{

    public Trash(int x, int y){
        super(x,y);
    }

    @Override
    public void interact(Chef chef) {
        Grabable tmp = chef.drop();
        if(tmp != null) tmp.setHolder(null);
    }
}
