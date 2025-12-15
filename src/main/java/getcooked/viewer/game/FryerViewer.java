package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Fryer;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class FryerViewer implements ElementViewer<Fryer>{

    @Override
    public void draw(Fryer fryer, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = fryer.getX() * SCALE;
        int pixelY = fryer.getY() * SCALE;
        Sprite sprite = null;
        if(fryer.isEmpty()) sprite = new Sprite("sprites/empty_fryer.png");
        else if(fryer.isEmpty()) sprite = new Sprite("sprites/fryer_cooking.png");
        else sprite = new Sprite("sprites/fryer_finished.png");
        sprite.draw(gui,pixelX,pixelY);

    }
}
