package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.DeliverySpot;

public class DeliverySpotViewer implements ElementViewer<DeliverySpot>{

    @Override
    public void draw(DeliverySpot element, GUI gui) {
        gui.drawDelivery(element.getPosition());
    }
}