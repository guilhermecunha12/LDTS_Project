package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Fryer;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class FryerViewer extends ElementViewer<Fryer>{

    @Override
    public void draw(Fryer fryer, GUI gui) throws IOException, URISyntaxException, FontFormatException {
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
        sprite.draw(gui, fryer.getX(), fryer.getY(), 0, 0);
    }
}
