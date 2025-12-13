package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.PlateDispenser;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class PlateDispenserViewer implements ElementViewer<PlateDispenser> {

    private final Sprite sprite;
    public PlateDispenserViewer(String filepath) throws Exception {
        this.sprite = new Sprite(filepath);
    }

    @Override
    public void draw(PlateDispenser element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = element.getPosition().getX() * 5;
        int pixelY = element.getPosition().getY() * 5;
        sprite.draw(gui, pixelX, pixelY);
    }
}

