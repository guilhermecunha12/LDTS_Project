package getcooked.model.game.elements.interactable.strategies;

import getcooked.Game;
import getcooked.model.game.elements.interactable.cookware.Cookware;

public class TimedBehaviour implements ExecutionBehaviour {
    private final long cookingTime;
    private final Cookware cookware;

    public TimedBehaviour(long cookingTime, Cookware cookware) {
        this.cookingTime = cookingTime;
        this.cookware = cookware;
    }

    @Override
    public void execute() {
        Thread.ofVirtual().start(() -> {
            cookware.cook();
            try {
                Thread.sleep(cookingTime * 1000 / Game.FPS); // Compliant
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cookware.finish();
        });
    }


}
