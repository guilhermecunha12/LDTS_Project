package getcooked.model.game.kitchen.elements.grabable;

import getcooked.model.Position;
import getcooked.model.game.kitchen.elements.Chef;

public abstract class Grabable {
    //TODO: Check if this position method is the best for class grabable
    private Position placedPosition;
    private Chef holder;

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
        this.placedPosition = null;
    }

    public Chef getHolder() { return holder; }
    public Position getPlacedPosition() { return placedPosition; }
}
