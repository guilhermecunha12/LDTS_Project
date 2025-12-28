package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.kitchenware.cutter.KebabCutter;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class KebabCutterViewer extends ElementViewer<KebabCutter>{
    @Override
    public void draw(KebabCutter kebabCutter, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/kebab_cutter.png");
        if(kebabCutter.isFinished()){
            sprite = new Sprite("sprites/kebab_cutter_cutted.png");
        }
        sprite.draw(gui, kebabCutter.getX(), kebabCutter.getY(), 0, 0);

    }
}
