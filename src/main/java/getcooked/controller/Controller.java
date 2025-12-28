package getcooked.controller;

import getcooked.Game;
import getcooked.gui.GUI;

import java.io.IOException;
import java.util.Set;

public abstract class Controller<T> {
    private final T model;

    protected Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException;
}
