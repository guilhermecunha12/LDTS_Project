package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.DeliverySpot;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class DeliverySpotViewer implements ElementViewer<DeliverySpot>{

    private final Sprite sprite;
    public DeliverySpotViewer(String filepath) throws IOException {
        this.sprite = new Sprite(filepath);
    }

    @Override
    public void draw(DeliverySpot element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = element.getPosition().getX() * SCALE;
        int pixelY = element.getPosition().getY() * SCALE;
        sprite.draw(gui, pixelX, pixelY);
    }
}
