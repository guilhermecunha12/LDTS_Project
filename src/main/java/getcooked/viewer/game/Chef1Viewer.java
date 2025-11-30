package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.Potato;

//TODO: review if ChefViewers could be unified
public class Chef1Viewer implements ElementViewer<Chef>{
    @Override
    public void draw(Chef chef, GUI gui) {
        gui.drawChef1(chef.getPosition());
        Grabable item = chef.getItem();
        if(item == null) return;

        if (item instanceof Potato) gui.drawPotato(chef.getPosition());
        else if (item instanceof Carrot) gui.drawCarrot(chef.getPosition());

        else if(item instanceof Plate){
            gui.drawPlate(chef.getPosition());
            Dish dish = ((Plate) item).getContent();
            if(dish != null){
                switch (dish.getType()) {
                    case CARROT_SOUP -> gui.drawCarrotSoup(chef.getPosition());
                    case POTATO_SOUP -> gui.drawPotatoSoup(chef.getPosition());
                }
            }
        }
    }
}
