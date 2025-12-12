package getcooked.model.game.elements.interactable.cookware;

import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.grabable.food.Beef;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.grabable.food.FoodState;
import getcooked.model.game.elements.interactable.strategies.TimedBehaviour;

import java.util.HashSet;
import java.util.Set;

public class Grill extends Cookware {

    public Grill(int x, int y) {
        super(x, y);
        this.allowedFoods = new HashSet<>(Set.of(Beef.class));
        this.executionBehaviour = new TimedBehaviour(100, this);
    }

    @Override
    protected boolean accepts(Food food) {
        return allowedFoods.contains(food.getClass()) && food.getState() == FoodState.RAW;
    }

    @Override
    protected void processFood() {
        executionBehaviour.execute();
    }

    @Override
    protected Dish createFinishedDish() {
        return new Dish(DishType.STEAK);
    }
}
