package getcooked.viewer.game;

import getcooked.gui.GUI;

public class Chef2Viewer implements ElementViewer<Chef> {
    @Override
    public void draw(Chef chef, GUI gui) {
        gui.drawChef2(chef.getPosition());
    }
}
