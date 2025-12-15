package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Beef;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.model.game.elements.grabable.food.Salad;
import getcooked.model.game.elements.interactable.Counter;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class CounterViewer implements ElementViewer<Counter>{
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

        int pixelX = counter.getPosition().getX() * SCALE;
        int pixelY = counter.getPosition().getY() * SCALE;
        sprite.draw(gui, pixelX, pixelY);

        Grabable item = counter.getContent();
        if (item == null) return;
        if (item instanceof Potato potato){
            PotatoViewer potatoViewer = new PotatoViewer();
            potatoViewer.draw(potato, gui);
        } else if (item instanceof Carrot carrot){
            CarrotViewer carrotViewer = new CarrotViewer();
            carrotViewer.draw(carrot, gui);
        }
        else if (item instanceof Salad salad){
            SaladViewer saladViewer = new SaladViewer();
            saladViewer.draw(salad, gui);
        }
        else if (item instanceof Beef beef){
            BeefViewer beefViewer = new BeefViewer();
            beefViewer.draw(beef, gui);
        }
        else if(item instanceof Plate plate){
            PlateViewer plateViewer = new PlateViewer();
            plateViewer.draw(plate, gui);
        }
    }
}
