package getcooked.model.game.elements.interactable.kitchenware.behaviour;

import getcooked.model.game.elements.interactable.kitchenware.Kitchenware;

public class TimedBehaviour implements ExecutionBehaviour {
    private final long cookingTime;

    public TimedBehaviour(long cookingTime) {
        this.cookingTime = cookingTime;
    }

    @Override
    public void execute(Kitchenware kitchenware) {
        Thread.ofVirtual().start(() -> {
            try {
                Thread.sleep(cookingTime * 1000);
                kitchenware.finish();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
