package getcooked.model.game.kitchen.elements.interactable.cookware;

import getcooked.model.game.kitchen.elements.grabable.Dish;
import getcooked.model.game.kitchen.elements.grabable.DishType;
import getcooked.model.game.kitchen.elements.grabable.Grabable;
import getcooked.model.game.kitchen.elements.grabable.food.Carrot;
import getcooked.model.game.kitchen.elements.grabable.food.Food;
import getcooked.model.game.kitchen.elements.grabable.food.Potato;

public class Pot extends Cookware{
    //TODO: review this:
    // only 1 chopped potato and 1 chopped carrot
    public Pot(int x, int y){
        super(x,y,2);
    }

    @Override
    protected boolean accepts(Food food){
        //only chopped potatos and chopped Carrot
        boolean isPotato = (food instanceof Potato) && (food.getState() == Food.FoodState.RAW);
        boolean isCarrot = (food instanceof Carrot) && (food.getState() == Food.FoodState.RAW);

        if (!isPotato && !isCarrot) return false;

        int potatoes = 0, carrots = 0;
        for(Food f : contents){
            if(f instanceof Potato) potatoes++;
            if(f instanceof Carrot) carrots++;
        }

        if (potatoes + carrots >= 1) return false;

        return true;
    }

    @Override
    protected void tryCook(){
        boolean hasPotato = false;
        boolean hasCarrot = false;

        for (Food f : contents) {
            if (f instanceof Potato) hasPotato = true;
            if (f instanceof Carrot) hasCarrot = true;
        }
        // XOR
        cooked =  (hasPotato ^ hasCarrot);
    }


    @Override
    protected Dish createFinishedDish(){
        if(!cooked) return null;
        boolean hasPotato = false, hasCarrot = false;
        for (Food f : contents) {
            if (f instanceof Potato) hasPotato = true;
            if (f instanceof Carrot) hasCarrot = true;
        }

        Dish result;
        if (hasPotato) {
            result = new Dish(DishType.POTATO_SOUP);
        } else if (hasCarrot) {
            result = new Dish(DishType.CARROT_SOUP);
        } else {
            return null; // should not happen
        }
        contents.clear();
        cooked = false;
        return result;
    }
}
