package getcooked.model.game.elements.interactable.cookware;

import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.grabable.food.FoodState;
import getcooked.model.game.elements.grabable.food.Potato;

import java.util.HashSet;
import java.util.Set;

public class Pot extends Cookware{
    public Pot(int x, int y){
        super(x,y);
        this.allowedFoods = new HashSet<>(Set.of(Potato.class, Carrot.class));
    }

    @Override
    protected boolean accepts(Food food){
        return allowedFoods.contains(food.getClass()) && food.getState() == FoodState.CHOPPED;
    }

    @Override
    protected void processFood(){
        finished = true;
    }

    @Override
    protected Dish createFinishedDish(){
        Dish result = null;
        if (content instanceof Potato) result = new Dish(DishType.POTATO_SOUP);
        else if (content instanceof Carrot) result = new Dish(DishType.CARROT_SOUP);
        return result;
    }
}
