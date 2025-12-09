package getcooked.model.game.elements.interactable.cookware;

import getcooked.model.game.dish.Dish;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.grabable.food.FoodState;
import getcooked.model.game.elements.grabable.food.Potato;

import java.util.HashSet;
import java.util.Set;

public class Cutter extends Cookware {
    public Cutter(int x, int y) {
        super(x, y);
        this.allowedFoods = new HashSet<>(Set.of(Potato.class, Carrot.class));
    }

    public void chop(){
        if(content.getState() == FoodState.RAW){
            content.setState(FoodState.CHOPPED);
        }
    }

    @Override // different implementation from cookware
    public void interact(Chef chef) {
        if (chef.isHoldingSomething()) {
            Grabable held = chef.getItem();
            if (held instanceof Food food && canAdd(food)) {
                chef.drop();
                addContent(food);
                processFood();
            }
        }
        else {
            if (isFinished()) {
                chef.pickUp(content);
                clearCookware();
            }
        }
    }

    @Override
    public void processFood() {
        chop();
        finish();
    }

    @Override
    protected boolean accepts(Food food) {
        return allowedFoods.contains(food.getClass()) && food.getState() == FoodState.RAW;
    }

    @Override
    protected Dish createFinishedDish() {
        return null;
    }
}
