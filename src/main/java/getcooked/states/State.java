package getcooked.states;
import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.menu.CookMenu;
import getcooked.viewer.Viewer;

import java.io.IOException;
import java.util.Set;

public abstract class State<T> {
    private T model;
    private Viewer<T> viewer;
    private Controller<T> controller;

    public State(T model) {
        this.model = model;
        this.viewer = this.getViewer();
        this.controller = this.getController();
    }

    protected abstract Viewer<T> getViewer();
    protected abstract Controller<T> getController();

    public T getModel() {
        return model;
    }

    public void step(Game game, GUI gui, long time) throws IOException {
        Set<GUI.ACTION> actions = gui.getNextAction();
        this.controller.step(game, actions, time);
        this.viewer.draw(gui);
    }
}
