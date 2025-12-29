package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.viewer.game.CarrotDispenserViewer;
import getcooked.viewer.game.ElementViewer;

public class CarrotDispenser extends Dispenser {

    public CarrotDispenser(int x, int y) {
        super(x, y);
    }

    @Override
    public ElementViewer<CarrotDispenser> getViewer() {
        return new CarrotDispenserViewer();
    }

    @Override
    public Grabable createItem(int x, int y) {
        return new Carrot(x, y);
    }
}
