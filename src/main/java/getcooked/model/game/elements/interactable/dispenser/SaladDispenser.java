package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.food.Salad;
import getcooked.viewer.game.ElementViewer;
import getcooked.viewer.game.SaladDispenserViewer;

public class SaladDispenser extends Dispenser {

    public SaladDispenser(int x, int y) {
        super(x, y);
    }

    @Override
    public ElementViewer getViewer() {
        return new SaladDispenserViewer();
    }

    @Override
    protected Grabable createItem(int x, int y) {
        return new Salad(x, y);
    }
}
