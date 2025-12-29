package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.dispenser.PotatoDispenser;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class PotatoDispenserViewer implements ElementViewer<PotatoDispenser> {
    @Override
    public void draw(PotatoDispenser model, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/potato_dispenser.png");
        sprite.draw(gui, model.getX(), model.getY(), 0, 0);
    }
}
