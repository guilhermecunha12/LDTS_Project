package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.model.game.dish.DishType.*;
import static getcooked.viewer.Sprite.SCALE;

public class PlateViewer implements ElementViewer<Plate>{

    private final Sprite sprite;
    public PlateViewer(Plate plate) throws IOException {
        Dish dish = plate.getContent();
        if (dish == null){
            sprite = new Sprite("sprites/plate.png");
        }
        else{
            switch (dish.getType()) {
                case POTATO_SOUP -> sprite = new Sprite("sprites/potato_soup.png");
                default -> throw new IOException("Invalid plate state");
            }
        }
    }

    @Override
    public void draw(Plate element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = element.getPosition().getX() * SCALE;
        int pixelY = element.getPosition().getY() * SCALE;
        sprite.draw(gui, pixelX, pixelY);
    }
}
