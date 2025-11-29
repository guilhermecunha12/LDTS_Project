package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Dish;
import getcooked.model.game.elements.grabable.DishType;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Potato;

public class Chef2Viewer implements ElementViewer<Chef> {
    @Override
    public void draw(Chef chef, GUI gui) {
        gui.drawChef2(chef.getPosition());
        Grabable item = chef.getItem();
        if(item == null) return;
        if(item instanceof Potato){
            gui.drawPotato(chef.getPosition());
        }
        if(item instanceof Plate){
            gui.drawPlate(chef.getPosition());
            Dish dish = ((Plate) item).getContent();
            if(dish != null){
                if(dish.getType() == DishType.POTATO_SOUP){
                    gui.drawPotatoSoup(chef.getPosition());
                }
            }
        }
    }
}
