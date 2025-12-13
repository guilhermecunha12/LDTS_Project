package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.PlateDispenser;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class PlateDispenserViewer implements ElementViewer<PlateDispenser> {

    private final Sprite sprite;
    public PlateDispenserViewer(String filepath) throws IOException{
        this.sprite = new Sprite(filepath);
    }

    @Override
    public void draw(PlateDispenser element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = element.getPosition().getX() * SCALE;
        int pixelY = element.getPosition().getY() * SCALE;
        sprite.draw(gui, pixelX, pixelY);
    }
}

