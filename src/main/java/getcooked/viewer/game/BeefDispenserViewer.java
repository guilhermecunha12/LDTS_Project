package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.BeefDispenser;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class BeefDispenserViewer implements ElementViewer<BeefDispenser> {

    private Sprite sprite;

    public BeefDispenserViewer(String filepath) throws IOException {
        this.sprite = new Sprite(filepath);
    }

    @Override
    public void draw(BeefDispenser beefDispenser, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = beefDispenser.getX() * SCALE;
        int pixelY = beefDispenser.getY() * SCALE;
        sprite.draw(gui, pixelX, pixelY);
    }
}
