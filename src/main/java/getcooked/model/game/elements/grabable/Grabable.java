package getcooked.model.game.elements.grabable;

import getcooked.model.Position;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Element;


public abstract class Grabable extends Element {
    private Chef holder;

    protected Grabable(int x, int y) {
        super(x,y);
        this.holder = null;
    }

    public boolean isHeld() { return holder != null; }
    public boolean isPlaced() { return holder == null; }

    // mark placed; clears holder
    public void placeAt(Position pos) {
        this.holder = null;
        this.setPosition(pos);
    }

    // mark held; clears placed position
    public void setHolder(Chef chef) {
        this.holder = chef;
        if(chef != null) this.setPosition(chef.getPosition());
    }

    public Chef getHolder() { return holder; }
}
