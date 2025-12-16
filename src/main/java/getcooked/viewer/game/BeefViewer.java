package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.Element;
import getcooked.model.game.elements.grabable.food.Beef;
import getcooked.model.game.elements.grabable.food.FoodState;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class BeefViewer extends ElementViewer<Beef> {
    @Override
    public void draw(Beef element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/raw_beef.png");
        sprite.draw(gui, element.getX(), element.getY(), 5);
    }
}