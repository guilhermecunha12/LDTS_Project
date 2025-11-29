package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.Floor;

public class FloorViewer implements ElementViewer<Floor> {
    @Override
    public void draw(Floor element, GUI gui) {
        gui.drawFloor(element.getPosition());
    }
}
