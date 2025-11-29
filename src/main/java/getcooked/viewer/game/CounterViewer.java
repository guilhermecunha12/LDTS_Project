package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.model.game.elements.interactable.Counter;

public class CounterViewer implements ElementViewer<Counter>{

    @Override
    public void draw(Counter counter, GUI gui) {
        gui.drawCounter(counter.getPosition());
        Grabable item = counter.getItem();
        if(item == null){return;}
        if(item instanceof Potato){
            gui.drawPotato(counter.getPosition());
        }
        if(item instanceof Plate){
            gui.drawPlate(counter.getPosition());
            Dish dish = ((Plate) item).getContent();
            if(dish != null){
                if(dish.getType() == DishType.POTATO_SOUP){
                    gui.drawPotatoSoup(counter.getPosition());
                }
            }
        }
    }
}
