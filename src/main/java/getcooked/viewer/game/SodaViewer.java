package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Soda;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class SodaViewer implements ElementViewer<Soda> {
    @Override
    public void draw(Soda element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/soda.png");
        sprite.draw(gui, element.getX(), element.getY(), 0, 5);
    }
}
