package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.Element;
import getcooked.model.game.elements.grabable.food.Beef;
import getcooked.model.game.elements.interactable.dispenser.BeefDispenser;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class BeefDispenserViewer extends ElementViewer<BeefDispenser> {


    @Override
    public void draw(BeefDispenser element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/beef_dispenser.png");
        sprite.draw(gui, element.getX(), element.getY(), 0);
    }
}
