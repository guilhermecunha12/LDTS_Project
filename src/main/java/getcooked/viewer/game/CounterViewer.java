package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.*;
import getcooked.model.game.elements.interactable.Counter;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class CounterViewer extends ElementViewer<Counter>{
    @Override
    public void draw(Counter counter, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = switch (counter.getType()) {
            case 'u' -> new Sprite("sprites/up_counter.png");
            case 'd' -> new Sprite("sprites/down_counter.png");
            case 'l' -> new Sprite("sprites/left_counter.png");
            case 'r' -> new Sprite("sprites/right_counter.png");
            case '1' -> new Sprite("sprites/top_left_counter.png");
            case '2' -> new Sprite("sprites/top_right_counter.png");
            case '3' -> new Sprite("sprites/bottom_right_counter.png");
            case '4' -> new Sprite("sprites/bottom_left_counter.png");
            default -> throw new IllegalStateException("Unexpected value: " + counter.getType());
        };
        sprite.draw(gui, counter.getX(), counter.getY(), 0);

        Grabable item = counter.getContent();
        if(item != null) {
            item.getViewer().draw(item, gui);
        }
    }
}
