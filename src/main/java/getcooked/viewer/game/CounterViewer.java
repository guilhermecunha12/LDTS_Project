package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.model.game.elements.interactable.Counter;

public class CounterViewer implements ElementViewer<Counter>{

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
}
