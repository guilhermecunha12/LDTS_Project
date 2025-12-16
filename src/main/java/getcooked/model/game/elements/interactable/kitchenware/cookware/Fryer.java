package getcooked.model.game.elements.interactable.kitchenware.cookware;

import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.grabable.food.FoodState;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.model.game.elements.interactable.kitchenware.behaviour.TimedBehaviour;
import getcooked.viewer.game.ElementViewer;
import getcooked.viewer.game.FryerViewer;

import java.util.HashSet;
import java.util.Set;

public class Fryer extends Cookware {

    public Fryer(int x, int y) {
        super(x, y, new TimedBehaviour(100));
        this.allowedFoods = new HashSet<>(Set.of(Potato.class));
    }

    @Override
    public ElementViewer<Fryer> getViewer() {
        return new FryerViewer();
    }

    @Override
    protected boolean accepts(Food food) {
        return allowedFoods.contains(food.getClass()) && food.getState() == FoodState.CHOPPED;
    }

    @Override
    protected Dish createFinishedDish() {
        return new Dish(DishType.FRIES);
    }
}
