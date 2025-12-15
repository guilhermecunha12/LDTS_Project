package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.kitchenware.cutter.KebabCutter;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class KebabCutterViewer implements ElementViewer<KebabCutter>{
    @Override
    public void draw(KebabCutter kebabCutter, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/kebab_cutter.png");
        int pixelX = kebabCutter.getPosition().getX() * SCALE;
        int pixelY = kebabCutter.getPosition().getY() * SCALE;
        if(kebabCutter.isFinished()){
            sprite = new Sprite("sprites/kebab_cutter_cutted.png");
        }
        sprite.draw(gui,pixelX,pixelY);

    }
}
