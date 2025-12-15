package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Salad;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class SaladViewer implements ElementViewer<Salad> {
    @Override
    public void draw(Salad salad, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/salad.png");
        int pixelX = salad.getPosition().getX() * SCALE;
        int pixelY = salad.getPosition().getY() * SCALE + 5;
        sprite.draw(gui, pixelX, pixelY);
    }
}
