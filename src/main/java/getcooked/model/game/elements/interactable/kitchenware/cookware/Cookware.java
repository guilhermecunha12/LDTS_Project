package getcooked.model.game.elements.interactable.kitchenware.cookware;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.interactable.kitchenware.Kitchenware;
import getcooked.model.game.elements.interactable.kitchenware.behaviour.ExecutionBehaviour;

import java.util.Set;

public abstract class Cookware extends Kitchenware {

    protected Set<Class<? extends Food>> allowedFoods;

    protected Cookware(int x, int y, ExecutionBehaviour timedBehaviour) {
        super(x, y, timedBehaviour);
        this.content = null;
    }

    public void addContent(Food content) {
        this.content = content;
    }

    public boolean canAdd(Food food) {
        // Can add to cookware if not full and food can be accepted
        return isEmpty() && this.accepts(food);
    }

    @Override
    public void interact(Chef chef) {
        if (!isExecuting() && chef.isHoldingSomething()){
            Grabable held = chef.getItem();

            if (held instanceof Food food && canAdd(food)) {
                chef.drop();
                addContent(food);
                this.start();
                processFood();
            }

            else if (held instanceof Plate plate && this.isFinished() && !plate.hasContent()) {
                Dish dish = createFinishedDish();
                plate.setContent(dish);
                clear();
            }
        }
    }

    protected abstract boolean accepts(Food food);
    protected abstract Dish createFinishedDish();
}