package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.food.Beef;
import getcooked.viewer.game.BeefDispenserViewer;
import getcooked.viewer.game.BeefViewer;
import getcooked.viewer.game.ElementViewer;

public class BeefDispenser extends Dispenser {
    public BeefDispenser(int x, int y) {
        super(x, y);
    }

    @Override
    public ElementViewer getViewer() {
        return new BeefDispenserViewer();
    }

    @Override
    protected Grabable createItem(int x, int y) {
        return new Beef(x,y);
    }
}
