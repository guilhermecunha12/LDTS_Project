package getcooked.model.game.elements.interactable.kitchenware.behaviour;

import getcooked.model.game.elements.interactable.kitchenware.Kitchenware;

public class TimedBehaviour implements ExecutionBehaviour {
    private final int timeRequired;
    private int timeLeft;

    public TimedBehaviour(int timeRequired) {
        this.timeRequired = timeRequired;
        this.timeLeft = timeRequired;
    }

    @Override
    public void execute(Kitchenware kitchenware) {
        timeLeft--;
        if (timeLeft == 0) {
            kitchenware.finish();
            timeLeft = timeRequired;
        }
    }
}
