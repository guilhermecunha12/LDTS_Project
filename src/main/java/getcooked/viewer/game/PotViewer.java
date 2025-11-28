package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.kitchen.elements.interactable.cookware.Pot;

public class PotViewer implements ElementViewer<Pot>{

    @Override
    public void draw(Pot element, GUI gui) {
        gui.drawPot(element.getPosition());
        element.getContents();
        if(element.isCooked()){
            gui.drawPotatoSoup(element.getPosition());
        }
    }
}
