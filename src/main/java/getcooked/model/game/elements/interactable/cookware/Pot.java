package getcooked.model.game.elements.interactable.cookware;

import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.grabable.food.Potato;

public class Pot extends Cookware{
    public Pot(int x, int y){
        super(x,y,1);
    }

    @Override
    protected boolean accepts(Food food){
        //only chopped potatos and chopped Carrot
        boolean isPotato = (food instanceof Potato) && (food.getState() == Food.FoodState.RAW);
        boolean isCarrot = (food instanceof Carrot) && (food.getState() == Food.FoodState.RAW);

        if (!isPotato && !isCarrot) return false;
        if (isFull()) return false;

        return true;
    }

    @Override
    protected void tryCook(){
        cooked = isFull();
    }

    @Override
    protected Dish createFinishedDish(){
        if(!cooked) return null;

        boolean hasPotato = false, hasCarrot = false;
        if (content instanceof Potato) hasPotato = true;
        if (content instanceof Carrot) hasCarrot = true;

        Dish result;
        if (hasPotato) {
            result = new Dish(DishType.POTATO_SOUP);
        } else if (hasCarrot) {
            result = new Dish(DishType.CARROT_SOUP);
        } else {
            return null; // should not happen
        }

        clear();
        cooked = false;
        return result;
    }
}
