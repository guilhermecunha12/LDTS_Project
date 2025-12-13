package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class PotatoViewer implements ElementViewer<Potato>{

    private final Sprite sprite;
    public PotatoViewer(String filepath) throws IOException {
        this.sprite = new Sprite(filepath);
    }
    @Override
    public void draw(Potato element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = element.getPosition().getX() * 5;
        int pixelY = element.getPosition().getY() * 5;
        sprite.draw(gui, pixelX, pixelY);
    }
}
