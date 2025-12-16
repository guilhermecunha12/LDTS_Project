package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Soda;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class SodaViewer extends ElementViewer<Soda> {
    @Override
    public void draw(Soda element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/soda.png");
        sprite.draw(gui, element.getX(), element.getY(), 5);
    }
}
