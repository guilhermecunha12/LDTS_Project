package getcooked.model.game.elements.interactable.cookware;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.interactable.Interactable;
import getcooked.model.game.elements.interactable.strategies.ExecutionBehaviour;

import java.util.Set;

public abstract class Cookware extends Interactable {

    protected Set<Class<? extends Food>> allowedFoods;
    protected Food content;
    protected boolean cooking; // for drawing cooking state
    protected boolean finished;
    protected ExecutionBehaviour executionBehaviour;

    protected Cookware(int x, int y) {
        super(x, y);
        this.content = null;
        this.finished = false;
        this.cooking = false;
    }

    public Food getContent() {
        return content;
    }

    public void addContent(Food content) {
        this.content = content;
    }

    public boolean isFinished() {
        return finished;
    }

    public void cook() {
        this.cooking = true;
    }

    public void finish() {
        this.cooking = false;
        this.finished = true;
    }

    public boolean isEmpty(){
        return content == null;
    }

    public void clearCookware(){
        content = null;
        finished = false;
    }

    public boolean canAdd(Food food) {
        // Can add to cookware if not full and food can be accepted
        return isEmpty() && this.accepts(food);
    }

    @Override
    public void interact(Chef chef) {
        if (!cooking && chef.isHoldingSomething()){
            Grabable held = chef.getItem();

            if (held instanceof Food food && canAdd(food)) {
                chef.drop();
                addContent(food);
                processFood();
            }

            else if (held instanceof Plate plate && this.isFinished() && !plate.hasContent()) {
                Dish dish = createFinishedDish();
                plate.setContent(dish);
                clearCookware();
            }
        }
    }

    protected abstract boolean accepts(Food food);
    protected abstract void processFood();
    protected abstract Dish createFinishedDish();
}