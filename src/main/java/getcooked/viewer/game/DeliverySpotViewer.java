package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.DeliverySpot;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class DeliverySpotViewer extends ElementViewer<DeliverySpot>{

    @Override
    public void draw(DeliverySpot element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/delivery_spot.png");
        sprite.draw(gui, element.getX(), element.getY(), 0);
    }
}
