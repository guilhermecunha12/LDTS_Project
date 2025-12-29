package getcooked.model.game.elements.interactable.kitchenware.cookware;

import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.grabable.food.FoodState;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.model.game.elements.interactable.kitchenware.behaviour.TimedBehaviour;
import getcooked.viewer.game.ElementViewer;
import getcooked.viewer.game.PotViewer;

import java.util.HashSet;
import java.util.Set;

public class Pot extends Cookware {

    public Pot(int x, int y){
        super(x,y, new TimedBehaviour(4));
        this.allowedFoods = new HashSet<>(Set.of(Potato.class, Carrot.class));
    }

    @Override
    public ElementViewer<Pot> getViewer() {
        return new PotViewer();
    }

    @Override
    protected boolean accepts(Food food){
        return allowedFoods.contains(food.getClass()) && food.getState() == FoodState.CHOPPED;
    }

    @Override
    protected Dish createFinishedDish(){
        DishType type  = content.getCookedDishType();
        return type != null ? new Dish(type) : null;
    }
}
