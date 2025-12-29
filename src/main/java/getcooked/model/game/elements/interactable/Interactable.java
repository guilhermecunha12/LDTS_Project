package getcooked.model.game.elements.interactable;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Tile;

public abstract class Interactable extends Tile {

    protected Interactable(int x, int y){
        super(x,y);
    }

    // Each interactable defines what happens to the chef
    @Override
    public abstract void interact(Chef chef);
}
