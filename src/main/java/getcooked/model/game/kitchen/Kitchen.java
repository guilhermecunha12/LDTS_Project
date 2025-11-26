package getcooked.model.game.kitchen;


import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.interactable.Interactable;

import java.util.List;

public class Kitchen {
    private final int width;
    private final int height;
    private boolean escapeMenuOn;

    private List<Chef> chefs;
    private List<Interactable> interactables;


    public Kitchen(int width, int height) {
        this.width = width;
        this.height = height;
        this.escapeMenuOn = false;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public List<Interactable> getInteractables() {
        return interactables;
    }

    public List<Chef> getChefs() {
        return chefs;
    }

    public boolean isEscapeMenuOn() {
        return escapeMenuOn;
    }

}
