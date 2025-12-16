package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.SaladDispenser;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class SaladDispenserViewer extends ElementViewer<SaladDispenser> {

    @Override
    public void draw(SaladDispenser saladDispenser, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/salad_dispenser.png");
        sprite.draw(gui, saladDispenser.getX(), saladDispenser.getY(), 0);
    }
}
