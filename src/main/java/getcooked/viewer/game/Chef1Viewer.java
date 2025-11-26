package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.kitchen.elements.Chef;

public class Chef1Viewer implements ElementViewer<Chef>{
    @Override
    public void draw(Chef chef, GUI gui) {
        gui.drawChef1(chef.getPosition());
    }
}
