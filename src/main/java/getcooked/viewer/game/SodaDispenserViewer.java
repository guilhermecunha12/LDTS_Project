package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.SodaDispenser;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class SodaDispenserViewer implements ElementViewer<SodaDispenser> {

    private Sprite sprite;

    public SodaDispenserViewer(String filepath) throws IOException {
        this.sprite = new Sprite(filepath);
    }

    @Override
    public void draw(SodaDispenser sodaDispenser, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = sodaDispenser.getX() * SCALE;
        int pixelY = sodaDispenser.getY() * SCALE;
        sprite.draw(gui, pixelX, pixelY);
    }
}
