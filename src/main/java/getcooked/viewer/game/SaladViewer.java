package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Salad;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class SaladViewer implements ElementViewer<Salad> {
    @Override
    public void draw(Salad salad, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/salad.png");
        sprite.draw(gui, salad.getX(), salad.getY(), 0, 5);
    }
}
