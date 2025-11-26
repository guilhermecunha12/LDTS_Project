package getcooked.viewer.game;

import getcooked.gui.GUI;

public class Chef1Viewer implements ElementViewer<Chef>{
    @Override
    public void draw(Chef chef, GUI gui) {
        gui.drawChef1(chef.getPosition());
    }
}
