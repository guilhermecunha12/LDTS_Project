package getcooked.viewer.game;

import getcooked.model.game.elements.interactable.kitchenware.cookware.Pot;
import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.viewer.Sprite;
import static getcooked.viewer.Sprite.SCALE;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class PotViewer implements ElementViewer<Pot>{

    private final Sprite sprite;
    public PotViewer(String filepath) throws Exception {
        this.sprite = new Sprite(filepath);
    }

    @Override
    public void draw(Pot pot, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = pot.getPosition().getX() * SCALE;
        int pixelY = pot.getPosition().getY() * SCALE;
        sprite.draw(gui, pixelX, pixelY);
    }
}
