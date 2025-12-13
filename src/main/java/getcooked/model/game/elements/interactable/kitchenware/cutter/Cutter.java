package getcooked.model.game.elements.interactable.kitchenware.cutter;

import getcooked.model.game.elements.interactable.kitchenware.Kitchenware;
import getcooked.model.game.elements.interactable.kitchenware.behaviour.ExecutionBehaviour;

public abstract class Cutter extends Kitchenware {

    protected Cutter(int x, int y, ExecutionBehaviour inputBehaviour) {
        super(x, y, inputBehaviour);
    }
}
