package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.model.game.elements.interactable.cookware.Pot;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
public class PotViewer implements ElementViewer<Pot>{

    private final Sprite sprite;
    public PotViewer(String filepath) throws Exception {
        this.sprite = new Sprite(filepath);
    }

    @Override
    public void draw(Pot pot, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = pot.getPosition().getX() * 5;
        int pixelY = pot.getPosition().getY() * 5;
        sprite.draw(gui, pixelX, pixelY);
        Food content = pot.getContent();
        if (pot.isFinished()){
            if (content instanceof Potato){
                
            }
        }
    }
}
