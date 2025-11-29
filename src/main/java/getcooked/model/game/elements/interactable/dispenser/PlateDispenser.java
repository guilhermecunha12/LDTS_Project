package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;

public class PlateDispenser extends Dispenser{
    public PlateDispenser(int x, int y) {
        super(x, y);
    }

    @Override
    protected Grabable createItem(int x, int y) {
        return new Plate(getX(), getY());
    }
}
