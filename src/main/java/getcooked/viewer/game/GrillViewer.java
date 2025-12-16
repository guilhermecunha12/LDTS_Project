package getcooked.viewer.game;

import getcooked.model.game.elements.interactable.kitchenware.cookware.Grill;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GrillViewer extends ElementViewer<Grill>{
    @Override
    public void draw(Grill grill, getcooked.gui.GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = null;
        if(grill.isEmpty()) sprite = new Sprite("sprites/empty_grill.png");
        else if (grill.isExecuting()) sprite = new Sprite("sprites/grill_cooking.png");
        else sprite = new Sprite("sprites/grill_finished.png");
        sprite.draw(gui, grill.getX(), grill.getY(), 0);
    }
}
