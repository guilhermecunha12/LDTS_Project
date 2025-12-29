package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class PotatoViewer extends ElementViewer<Potato>{
    @Override
    public void draw(Potato potato, GUI gui) throws IOException, URISyntaxException, FontFormatException {

        Sprite sprite = switch (potato.getState()) {
            case RAW -> new Sprite("sprites/raw_potato.png");
            case CHOPPED -> new Sprite("sprites/chopped_potato.png");
        };

        sprite.draw(gui, potato.getX(), potato.getY(), 0, 5);
    }
}
