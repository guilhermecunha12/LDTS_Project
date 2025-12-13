package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.PotatoDispenser;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class PotatoDispenserViewer implements ElementViewer<PotatoDispenser> {

    private final Sprite sprite;
    public PotatoDispenserViewer(String filepath) throws Exception {
        this.sprite = new Sprite(filepath);
    }

    @Override
    public void draw(PotatoDispenser model, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = model.getPosition().getX() * 5;
        int pixelY = model.getPosition().getY() * 5;
        sprite.draw(gui, pixelX, pixelY);
    }
}
