package getcooked.model.game.elements.interactable;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;

public class Trash extends Interactable{

    public Trash(int x, int y){
        super(x,y);
    }

    @Override
    public void interact(Chef chef) {
       chef.drop();
    }
}
