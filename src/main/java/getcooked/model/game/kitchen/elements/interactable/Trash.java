package getcooked.model.game.kitchen.elements.interactable;

import getcooked.model.game.kitchen.elements.Chef;

public class Trash extends Interactable{

    public Trash(int x, int y){
        super(x,y);
    }

    @Override
    public void interact(Chef chef) {
        chef.drop();
    }
}
