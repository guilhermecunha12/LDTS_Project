package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.PotatoDispenser;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;


public class PotatoDispenserViewer implements ElementViewer<PotatoDispenser> {

    private final Sprite sprite;
    public PotatoDispenserViewer(String filepath) throws IOException {
        this.sprite = new Sprite(filepath);
    }

    @Override
    public void draw(PotatoDispenser model, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = model.getPosition().getX() * SCALE;
        int pixelY = model.getPosition().getY() * SCALE;
        sprite.draw(gui, pixelX, pixelY);
    }
}
