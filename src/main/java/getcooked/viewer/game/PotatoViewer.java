package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class PotatoViewer implements ElementViewer<Potato>{
    @Override
    public void draw(Potato potato, GUI gui) throws IOException, URISyntaxException, FontFormatException {

        Sprite sprite = switch (potato.getState()) {
            case RAW -> new Sprite("sprites/raw_potato.png");
            case CHOPPED -> new Sprite("sprites/chopped_potato.png");
        };

        int pixelX = potato.getPosition().getX() * SCALE;
        int pixelY = potato.getPosition().getY() * SCALE + 5;
        sprite.draw(gui, pixelX, pixelY);
    }
}