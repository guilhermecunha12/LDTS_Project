package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.kitchen.elements.interactable.dispenser.PotatoDispenser;

public class PotatoDispenserViewer implements ElementViewer<PotatoDispenser> {


    @Override
    public void draw(PotatoDispenser element, GUI gui) {
        gui.drawPotatoDispenser(element.getPosition());
    }
}
