package getcooked.model.game.elements.interactable.kitchenware.cookware;

import getcooked.Game;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.grabable.food.Beef;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.grabable.food.FoodState;
import getcooked.model.game.elements.interactable.kitchenware.behaviour.TimedBehaviour;
import getcooked.viewer.game.ElementViewer;
import getcooked.viewer.game.GrillViewer;

import java.util.HashSet;
import java.util.Set;

public class Grill extends Cookware {

    public Grill(int x, int y) {
        super(x, y, new TimedBehaviour(4*Game.FPS));
        this.allowedFoods = new HashSet<>(Set.of(Beef.class));
    }

    @Override
    public ElementViewer<Grill> getViewer() {
        return new GrillViewer();
    }

    @Override
    protected boolean accepts(Food food) {
        return allowedFoods.contains(food.getClass()) && food.getState() == FoodState.RAW;
    }

    @Override
    protected Dish createFinishedDish() {
        return new Dish(DishType.STEAK);
    }
}
