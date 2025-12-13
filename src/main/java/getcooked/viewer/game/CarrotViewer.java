package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class CarrotViewer implements ElementViewer<Carrot> {
    private final Sprite sprite;
    public CarrotViewer(Carrot carrot) throws IOException {
        switch (carrot.getState()){
            case RAW: sprite = new Sprite("sprites/raw_carrot.png"); break;
            case CHOPPED: sprite = new Sprite("sprites/chopped_potato.png"); break;
            default: throw new IOException("Invalid potato state");
        }
    }
    @Override
    public void draw(Carrot element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = element.getPosition().getX() * SCALE;
        int pixelY = element.getPosition().getY() * SCALE;
        sprite.draw(gui, pixelX, pixelY);
    }
}
