package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.food.Soda;
import getcooked.viewer.game.ElementViewer;
import getcooked.viewer.game.SodaDispenserViewer;

public class SodaDispenser extends Dispenser {

    public SodaDispenser(int x, int y) {
        super(x, y);
    }

    @Override
    public ElementViewer<SodaDispenser> getViewer() {
        return new SodaDispenserViewer();
    }

    @Override
    protected Grabable createItem(int x, int y) {
        return new Soda(x, y);
    }
}
