package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.viewer.game.ElementViewer;
import getcooked.viewer.game.PlateDispenserViewer;

public class PlateDispenser extends Dispenser{
    public PlateDispenser(int x, int y) {
        super(x, y);
    }

    @Override
    public ElementViewer<PlateDispenser> getViewer() {
        return new PlateDispenserViewer();
    }

    @Override
    protected Grabable createItem(int x, int y) {
        return new Plate(x, y);
    }
}
