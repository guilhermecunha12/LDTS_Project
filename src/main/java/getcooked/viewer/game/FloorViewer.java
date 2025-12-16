package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.Element;
import getcooked.model.game.elements.Floor;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class FloorViewer extends ElementViewer<Floor>{

    @Override
    public void draw(Floor element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/floor.png");
        sprite.draw(gui, element.getX(), element.getY(), 0);
    }
}
