package getcooked.model.game.kitchen.elements.interactable;

import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.Tile;

public abstract class Interactable extends Tile {

    public Interactable(int x, int y){
        super(x,y);
    }

    // Each interactable defines what happens to the chef
    public abstract void interact(Chef chef);
}
