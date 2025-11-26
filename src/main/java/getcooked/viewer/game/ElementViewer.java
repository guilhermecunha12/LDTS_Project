package getcooked.viewer.game;

import getcooked.gui.GUI;

public interface ElementViewer <T extends Element> {
    void draw(T element, GUI gui);
}
