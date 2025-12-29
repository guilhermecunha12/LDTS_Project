package getcooked.states;
import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.viewer.Viewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

public abstract class State<T> {
    private final T model;
    private final Viewer<T> viewer;
    private final Controller<T> controller;

    protected State(T model) {
        this.model = model;
        this.viewer = this.getViewer();
        this.controller = this.getController();
    }

    protected abstract Viewer<T> getViewer();
    protected abstract Controller<T> getController();

    public T getModel() {
        return model;
    }

    public void step(Game game, GUI gui, long time) throws IOException, URISyntaxException, FontFormatException {
        Set<GUI.ACTION> actions = gui.getNextAction();
        this.controller.step(game, actions, time);
        this.viewer.draw(gui);
    }
}
