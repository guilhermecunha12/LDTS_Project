package getcooked.model.game.elements.interactable.kitchenware.cutter;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.grabable.food.FoodState;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.model.game.elements.interactable.kitchenware.behaviour.InputBehaviour;

import java.util.HashSet;
import java.util.Set;

public class CounterCutter extends Cutter {
    protected Set<Class<? extends Food>> allowedFoods;

    public CounterCutter(int x, int y) {
        super(x, y, new InputBehaviour(5));
        this.allowedFoods = new HashSet<>(Set.of(Potato.class, Carrot.class));
        this.content = null;
    }

    @Override
    public void finish() {
        chop();
        super.finish();
    }

    public void chop(){
        if (content.getState() == FoodState.RAW){
            content.setState(FoodState.CHOPPED);
        }
    }

    public void addContent(Food content) {
        this.content = content;
    }

    protected boolean accepts(Food food) {
        return allowedFoods.contains(food.getClass()) && food.getState() == FoodState.RAW;
    }

    public boolean canAdd(Food food) {
        return isEmpty() && this.accepts(food);
    }

    @Override
    public void interact(Chef chef) {
        if (chef.isHoldingSomething()) {
            Grabable held = chef.getItem();
            if (held instanceof Food food && canAdd(food)) {
                chef.drop();
                addContent(food);
                this.start(); // cutting mode
            }
        }

        else {
             if (isFinished()) {
                chef.pickUp(content);
                clear();
            } else if (this.isExecuting()) {
                processFood();
            }
        }
    }
}
