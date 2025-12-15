package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class PlateViewer implements ElementViewer<Plate>{
    @Override
    public void draw(Plate plate, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite;

        Dish dish = plate.getContent();
        if (dish == null){
            sprite = new Sprite("sprites/plate.png");
        }
        else{
            switch (dish.getType()) {
                case POTATO_SOUP -> sprite = new Sprite("sprites/potato_soup.png");
                case CARROT_SOUP ->  sprite = new Sprite("sprites/carrot_soup.png");
                case STEAK ->  sprite = new Sprite("sprites/steak.png");
                case FRIES ->  sprite = new Sprite("sprites/fries.png");
                default -> throw new IOException("Invalid plate state");
            }
        }

        int pixelX = plate.getPosition().getX() * SCALE ;
        int pixelY = plate.getPosition().getY() * SCALE + 5;
        sprite.draw(gui, pixelX, pixelY);
    }
}
