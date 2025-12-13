package getcooked.model.game.elements.interactable.kitchenware;

import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.interactable.Interactable;
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

    protected boolean isExecuting() { // for drawing purposes
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
}
