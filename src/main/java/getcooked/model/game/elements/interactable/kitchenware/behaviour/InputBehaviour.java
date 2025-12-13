package getcooked.model.game.elements.interactable.kitchenware.behaviour;

import getcooked.model.game.elements.interactable.kitchenware.Kitchenware;

public class InputBehaviour implements ExecutionBehaviour {
    private final int inputsRequired;
    private int inputsLeft;

    public InputBehaviour(int inputsRequired) {
        this.inputsRequired = inputsRequired;
        this.inputsLeft = inputsRequired;
    }

    @Override
    public void execute(Kitchenware kitchenware) {
        inputsLeft--;
        if (inputsLeft == 0) {
            kitchenware.finish();
            inputsLeft = inputsRequired;
        }
    }
}
