package getcooked.model.game.elements.interactable.kitchenware.cutter;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.grabable.food.FoodState;
import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.model.game.elements.interactable.kitchenware.Kitchenware;
import getcooked.model.game.elements.interactable.kitchenware.behaviour.InputBehaviour;
import getcooked.viewer.game.CounterCutterViewer;
import getcooked.viewer.game.ElementViewer;

import java.util.HashSet;
import java.util.Set;


public class CounterCutter extends Kitchenware {
    protected Set<Class<? extends Food>> allowedFoods;

    public CounterCutter(int x, int y) {
        super(x, y, new InputBehaviour(5));
        this.allowedFoods = new HashSet<>(Set.of(Potato.class, Carrot.class));
        this.content = null;
    }

    @Override
    public ElementViewer<CounterCutter> getViewer() {
        return new CounterCutterViewer();
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
            chef.getItem().interactWithKitchenware(this, chef);

        } else {
             if (isFinished()) {
                chef.pickUp(content);
                clear();

            } else if (this.isExecuting()) {
                processFood();
            }
        }
    }

    @Override
    public void acceptFoodInChef(Food food, Chef chef) {
        if (canAdd(food)) {
            chef.drop();
            addContent(food);
            this.start(); // cutting mode
        }
    }
}
