package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class CarrotViewer implements ElementViewer<Carrot> {
    @Override
    public void draw(Carrot carrot, GUI gui) throws IOException, URISyntaxException, FontFormatException {

        Sprite sprite = switch (carrot.getState()) {
            case RAW -> new Sprite("sprites/raw_carrot.png");
            case CHOPPED -> new Sprite("sprites/chopped_carrot.png");
        };

        int pixelX = carrot.getPosition().getX() * SCALE;
        int pixelY = carrot.getPosition().getY() * SCALE;
        sprite.draw(gui, pixelX, pixelY);
    }
}
