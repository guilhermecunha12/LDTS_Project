package getcooked.model.game.kitchen.elements.grabable;

import getcooked.model.Position;
import getcooked.model.game.kitchen.elements.Chef;

// TODO: Grabable should extend Element...
public abstract class Grabable {
    //TODO: Check if this position method is the best for class grabable
    private Position placedPosition;
    private Chef holder;

    public Grabable(int x, int y) {
        this.placedPosition = new Position(x, y);
        this.holder = null;
    }

    public boolean isHeld() { return holder != null; }
    public boolean isPlaced() { return placedPosition != null && holder == null; }

    public Position getPosition() {
        if (isHeld()) return holder.getPosition();
        return placedPosition;
    }

    // mark placed; clears holder
    public void placeAt(Position pos) {
        this.placedPosition = pos;
        this.holder = null;
    }

    // mark held; clears placed position
    public void setHolder(Chef chef) {
        this.holder = chef;
        if(chef != null) {
            this.placedPosition = null;
        }
    }

    public Chef getHolder() { return holder; }
    public Position getPlacedPosition() { return placedPosition; }
}
