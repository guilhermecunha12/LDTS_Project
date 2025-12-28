package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.SodaDispenser;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class SodaDispenserViewer extends ElementViewer<SodaDispenser> {


    @Override
    public void draw(SodaDispenser sodaDispenser, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/soda_dispenser.png");
        sprite.draw(gui, sodaDispenser.getX(), sodaDispenser.getY(), 0, 0);
    }
}
