package getcooked.model;

import getcooked.model.game.kitchen.Kitchen;
import getcooked.model.game.kitchen.LoaderKitchenBuilder;
import getcooked.model.menu.EscapeMenu;

import java.io.IOException;

// model that contains the Kitchen model and the EscapeMenu model
public class PlayingSession {
    private Kitchen kitchen; // not final, because kitchen will change during levels
    private final EscapeMenu escapeMenu;
    private int level;
    private boolean paused;


    public PlayingSession(Kitchen kitchen, EscapeMenu escapeMenu) {
        this.kitchen = kitchen;
        this.escapeMenu = escapeMenu;
        this.paused = false;
        this.level = 1; // when a new playing session is created, the starting level is 1
    }

    public Kitchen getKitchen() {
        return kitchen;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    public void nextLevel() throws IOException {
        this.level++;
        // Update the kitchen model, according to the new level
        this.kitchen = new LoaderKitchenBuilder(level, kitchen.getNumPlayers()).createKitchen();
    }

    public int getLevel() {
        return level;
    }

    public EscapeMenu getEscapeMenu() {
        return escapeMenu;
    }

    public boolean isPaused() {
        return paused;
    }

    public void togglePause() {
        this.paused = !this.paused;
    }
}