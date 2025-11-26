package getcooked.model.game.kitchen;

import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.interactable.Interactable;

import java.util.List;

public abstract class KitchenBuilder {
    public Kitchen createKitchen() {
        Kitchen kitchen = new Kitchen(getWidth(), getHeight());

        // Set the different elements inside the kitchen


        return kitchen;
    }

    // Creates the kitchen based on the width and the height of the file
    protected abstract int getWidth();

    protected abstract int getHeight();

    protected abstract List<Interactable> createInteractables();

    protected abstract List<Chef> createChefs();

}
