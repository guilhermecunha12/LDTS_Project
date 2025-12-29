package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.PlateDispenser;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class PlateDispenserViewer implements ElementViewer<PlateDispenser> {
    @Override
    public void draw(PlateDispenser element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/plate_dispenser.png");
        sprite.draw(gui, element.getX(), element.getY(), 0, 0);
    }
}

