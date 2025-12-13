package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class PotatoViewer implements ElementViewer<Potato>{

    private final Sprite sprite;
    public PotatoViewer(Potato potato) throws IOException {
        switch (potato.getState()){
            case RAW -> sprite = new Sprite("sprites/raw_potato.png");
            case CHOPPED -> sprite = new Sprite("sprites/chopped_potato.png");
            default -> throw new IOException("Invalid potato state");
        }
    }
    @Override
    public void draw(Potato element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = element.getPosition().getX() * 5;
        int pixelY = element.getPosition().getY() * 5;
        sprite.draw(gui, pixelX, pixelY);
    }
}
