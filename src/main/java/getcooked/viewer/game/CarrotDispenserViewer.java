package getcooked.viewer.game;

import getcooked.model.game.elements.interactable.dispenser.CarrotDispenser;

public class CarrotDispenserViewer implements ElementViewer<CarrotDispenser> {

    @Override
    public void draw(CarrotDispenser element, getcooked.gui.GUI gui) {
        gui.drawCarrotDispenser(element.getPosition());
    }
}
