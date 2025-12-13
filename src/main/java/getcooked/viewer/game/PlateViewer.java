package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.model.game.dish.DishType.*;

public class PlateViewer implements ElementViewer<Plate>{

    private final Sprite sprite;
    public PlateViewer(Plate plate) throws IOException {
        Dish dish = plate.getContent();
        switch (dish.getType()) {
            case EMPTY -> sprite = new Sprite("sprites/plate.png");
            case POTATO_SOUP -> sprite = new Sprite("sprites/potato_soup.png");
            default -> throw new IOException("Invalid plate state");
        }
    }

    @Override
    public void draw(Plate element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = element.getPosition().getX() * 5;
        int pixelY = element.getPosition().getY() * 5;
        sprite.draw(gui, pixelX, pixelY);
    }
}
