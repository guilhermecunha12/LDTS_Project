package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Beef;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class BeefViewer implements ElementViewer<Beef> {
    @Override
    public void draw(Beef element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/raw_beef.png");
        sprite.draw(gui, element.getX(), element.getY(), 0, 5);
    }
}
