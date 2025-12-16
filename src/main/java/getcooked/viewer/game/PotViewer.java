package getcooked.viewer.game;

import getcooked.model.game.elements.interactable.kitchenware.cookware.Pot;
import getcooked.gui.GUI;
import getcooked.viewer.Sprite;
import static getcooked.viewer.Sprite.SCALE;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class PotViewer implements ElementViewer<Pot>{

    @Override
    public void draw(Pot pot, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = pot.getPosition().getX() * SCALE;
        int pixelY = pot.getPosition().getY() * SCALE;
        Sprite sprite = null;
        if (pot.isEmpty()) sprite = new Sprite("sprites/empty_pot.png");
        else if (pot.isExecuting()) sprite = new Sprite("sprites/pot_cooking.png");
        else sprite = new Sprite("sprites/pot_finished.png");
        sprite.draw(gui, pixelX, pixelY);
    }
}
