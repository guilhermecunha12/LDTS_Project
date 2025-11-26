package getcooked.model;

import getcooked.model.game.kitchen.Kitchen;
import getcooked.model.menu.EscapeMenu;

// model that contains the Kitchen model and the EscapeMenu model
public class PlayingSession {
    private final Kitchen kitchen;
    private final EscapeMenu escapeMenu;
    private boolean paused;

    public PlayingSession(Kitchen kitchen, EscapeMenu escapeMenu) {
        this.kitchen = kitchen;
        this.escapeMenu = escapeMenu;
        this.paused = false;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }

    public EscapeMenu getEscapeMenu() {
        return escapeMenu;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
