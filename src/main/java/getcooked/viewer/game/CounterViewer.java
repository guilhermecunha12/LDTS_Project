package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.model.game.elements.interactable.Counter;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

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
    private final Sprite sprite;
    public CounterViewer(String filepath) throws Exception {
        this.sprite = new Sprite(filepath);
        //TODO: fazer switch case para diferentes sprites de counter, é preicso mudaar o map
    }
    @Override
    public void draw(Counter model, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = model.getPosition().getX() * 8;
        int pixelY = model.getPosition().getY() * 8;
        sprite.draw(gui, pixelX, pixelY);
    }
}
