package getcooked.model.game.elements.interactable.kitchenware;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Interactable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.interactable.kitchenware.behaviour.ExecutionBehaviour;

public abstract class Kitchenware extends Interactable {
    protected final ExecutionBehaviour executionBehaviour;
    protected boolean executing;
    protected Food content;

    protected Kitchenware(int x, int y, ExecutionBehaviour executionBehaviour) {
        super(x, y);
        this.executing = false;
        this.executionBehaviour = executionBehaviour;
    }

    public void start() {
        this.executing = true;
    }

    public void finish() {
        this.executing = false;
    }

    protected void clear() {
        this.content = null;
        this.executing = false;
    }

    public boolean isFinished() {
        return !this.executing && content != null;
    }

    public boolean isExecuting() { // for drawing purposes
        return this.executing;
    }

    public boolean isEmpty(){
        return content == null;
    }

    public Food getContent() {
        return content;
    }

    protected void processFood() {
        this.executionBehaviour.execute(this);
    }

    // part of visitor pattern: default implementation is to do nothing
    // then, each subclass implements this method with specific code
    // only if it should do something when chef is holding food.
    public void acceptFoodInChef(Food food, Chef chef) {}

    // part of visitor pattern: default implementation is to do nothing
    // then, each subclass implements this method with specific code
    // only if it should do something when chef is holding a plate.
    public void acceptPlateInChef(Plate plate, Chef chef) {}

}
