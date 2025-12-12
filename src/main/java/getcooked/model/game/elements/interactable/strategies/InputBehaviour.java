package getcooked.model.game.elements.interactable.strategies;

import getcooked.model.game.elements.interactable.cookware.Cutter;

public class InputBehaviour implements ExecutionBehaviour {
    private final int inputsRequired;
    private int inputsLeft;
    private final Cutter cutter;

    public InputBehaviour(int inputsRequired, Cutter cutter) {
        this.inputsRequired = inputsRequired;
        this.inputsLeft = inputsRequired;
        this.cutter = cutter;
    }

    @Override
    public void execute() {
        inputsLeft--;
        if (inputsLeft == 0) {
            cutter.chop();
            cutter.finish();
            inputsLeft = inputsRequired;
        }
    }
}
