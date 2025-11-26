package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.kitchen.elements.Element;

public interface ElementViewer <T extends Element> {
    void draw(T element, GUI gui);
}
