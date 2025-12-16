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

        if(fryer.isEmpty()){
            if(fryer.getType() == 'F') sprite = new Sprite("sprites/left_empty_fryer.png");
            else sprite = new Sprite("sprites/right_empty_fryer.png");
        }
        else if(fryer.isExecuting()){
            if(fryer.getType() == 'F') sprite = new Sprite("sprites/left_fryer_cooking.png");
            else sprite = new Sprite("sprites/right_fryer_cooking.png");
        }
        else {
            if(fryer.getType() == 'F') sprite = new Sprite("sprites/left_fryer_finished.png");
            else sprite = new Sprite("sprites/right_fryer_finished.png");
        }
        sprite.draw(gui,pixelX,pixelY);
    }
}
