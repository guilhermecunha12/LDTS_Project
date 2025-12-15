package getcooked.model.game.elements.interactable;

import getcooked.model.game.elements.Chef;

public class Trash extends Interactable{

    public Trash(int x, int y){
        super(x,y);
    }

    @Override
    public void interact(Chef chef) {
        if(chef.isHoldingSomething())
            chef.drop();
    }
}
