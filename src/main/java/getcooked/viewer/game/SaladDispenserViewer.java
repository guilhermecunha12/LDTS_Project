package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.SaladDispenser;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class SaladDispenserViewer implements ElementViewer<SaladDispenser> {

    private Sprite sprite;

    public SaladDispenserViewer(String filepath) throws IOException {
        this.sprite = new Sprite(filepath);
    }

    @Override
    public void draw(SaladDispenser saladDispenser, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = saladDispenser.getPosition().getX() * SCALE;
        int pixelY = saladDispenser.getPosition().getY() * SCALE;
        sprite.draw(gui, pixelX, pixelY);
    }
}
