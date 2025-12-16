package getcooked.model.game.elements;

import getcooked.gui.GUI;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class Floor extends Tile {
    public Floor(int x, int y) {
        super(x,y);
    }

    @Override
    public void draw(GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/floor.png");
        int pixelX = getX() * SCALE;
        int pixelY = getY() * SCALE;
        sprite.draw(gui, pixelX, pixelY);
    }
}
