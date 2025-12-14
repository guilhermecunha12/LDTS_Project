package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.model.game.elements.interactable.Counter;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class CounterViewer implements ElementViewer<Counter>{
    /*
    @Override
    public void draw(Counter counter, GUI gui) {
        gui.drawCounter(counter.getPosition());
        Grabable item = counter.getContent();
        if(item == null) return;

        if (item instanceof Potato) gui.drawPotato(counter.getPosition());
        else if (item instanceof Carrot) gui.drawCarrot(counter.getPosition());

        else if(item instanceof Plate plate){
            gui.drawPlate(counter.getPosition());
            Dish dish = plate.getContent();

            if(dish != null){
                switch (dish.getType()) {
                    case CARROT_SOUP:
                        gui.drawCarrotSoup(counter.getPosition());
                        break;
                    case POTATO_SOUP:
                        gui.drawPotatoSoup(counter.getPosition());
                        break;
                    default:
                        break;
                }
            }
        }
    }
     */

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
        if(item == null) return;
        if (item instanceof Potato potato){
            PotatoViewer potatoViewer = new PotatoViewer(potato);
            potatoViewer.draw(potato, gui);
        } else if (item instanceof Carrot){
            //CarrotViewer carrotViewer = new CarrotViewer("sprites/carrot.png");
            //carrotViewer.draw((Carrot) item, gui);
        } else if(item instanceof Plate plate){
            PlateViewer plateViewer = new PlateViewer(plate);
            plateViewer.draw(plate, gui);
        }
    }
}
