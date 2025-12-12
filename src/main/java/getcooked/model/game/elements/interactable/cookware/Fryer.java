package getcooked.model.game.elements.interactable.cookware;

import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.grabable.food.FoodState;
import getcooked.model.game.elements.grabable.food.Potato;

import java.util.HashSet;
import java.util.Set;

public class Fryer extends Cookware {

    public Fryer(int x, int y) {
        super(x, y);
        this.allowedFoods = new HashSet<>(Set.of(Potato.class));
    }

    @Override
    protected boolean accepts(Food food) {
        return allowedFoods.contains(food.getClass()) && food.getState() == FoodState.CHOPPED;
    }

    @Override
    protected void processFood() {
        finish();
    }

    @Override
    protected Dish createFinishedDish() {
        return new Dish(DishType.FRIES);
    }
}
