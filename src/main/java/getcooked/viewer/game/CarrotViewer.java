package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class CarrotViewer implements ElementViewer<Carrot> {

    @Override
    public void draw(Carrot element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = switch (element.getState()) {
            case RAW -> new Sprite("sprites/raw_carrot.png");
            case CHOPPED -> new Sprite("sprites/chopped_carrot.png");
        };
        sprite.draw(gui, element.getX(), element.getY(), 0, 5);
    }
}
