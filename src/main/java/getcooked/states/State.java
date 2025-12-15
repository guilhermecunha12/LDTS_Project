package getcooked.states;
import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.viewer.Viewer;

import java.util.Set;

public abstract class State<T> {
    private T model;
    private Viewer<T> viewer;
    private Controller<T> controller;
    private boolean first;

    public State(T model) {
        this.model = model;
        this.viewer = this.getViewer();
        this.controller = this.getController();
        this.first = true;
    }

    protected abstract Viewer<T> getViewer();
    protected abstract Controller<T> getController();

    public T getModel() {
        return model;
    }

    public void step(Game game, GUI gui, long time) throws Exception {
        /*
        Set<GUI.ACTION> actions = gui.getNextAction();
        this.controller.step(game, actions, time);
        this.viewer.draw(gui);
        */
        if (first) { // if state changed, then draw gui (for performance improvement)
            this.viewer.draw(gui);
            first = false;
        }

        Set<GUI.ACTION> actions = gui.getNextAction();
        this.controller.step(game, actions, time);

        if (!actions.isEmpty())  // if inputs were processed, then draw gui (for performance improvement)
            this.viewer.draw(gui);
    }
}
