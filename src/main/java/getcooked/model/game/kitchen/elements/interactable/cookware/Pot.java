package getcooked.model.game.kitchen.elements.interactable.cookware;

import getcooked.model.game.kitchen.elements.grabable.Grabable;
import getcooked.model.game.kitchen.elements.grabable.food.Carrot;
import getcooked.model.game.kitchen.elements.grabable.food.Food;
import getcooked.model.game.kitchen.elements.grabable.food.Potato;

public class Pot extends Cookware{
    // only 1 chopped potato and 1 chopped carrot
    public Pot(int x, int y){
        super(x,y,2);
    }

    @Override
    protected boolean accepts(Food food){
        //only chopped potatos and chopped Carrot
        boolean isChoppedPotato = (food instanceof Potato) && (food.getState() == Food.FoodState.CHOPPED);
        boolean isChoppedCarrot = (food instanceof Carrot) && (food.getState() == Food.FoodState.CHOPPED);

        int potatoes = 0, carrots = 0;
        for(Food f : contents){
            if(f instanceof Potato) potatoes++;
            if(f instanceof Carrot) carrots++;
        }

        if (isChoppedPotato && potatoes >= 1) return false;
        if (isChoppedCarrot && carrots >= 1) return false;

        return true;
    }

    @Override
    protected void tryCook(){
        boolean hasChoppedPotato = false;
        boolean hasChoppedCarrot = false;

        for (Food f : contents) {
            if (f instanceof Potato && f.getState() == Food.FoodState.CHOPPED) hasChoppedPotato = true;
            if (f instanceof Carrot && f.getState() == Food.FoodState.CHOPPED) hasChoppedCarrot = true;
        }

        cooked =  hasChoppedPotato && hasChoppedCarrot;
    }


    @Override
    protected Grabable createFinishedDish(){
        if(!cooked) return null;
        //TODO: Generate a soup and
        contents.clear();
        return null;
    }
}
