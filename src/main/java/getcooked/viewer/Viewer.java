package getcooked.viewer;

import getcooked.gui.GUI;

import java.io.IOException;

public abstract class Viewer<T> {
    private final T model;

    public Viewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return this.model; // for concrete viewers
    }

    public void draw(GUI gui) throws Exception {
        gui.clear();
        this.drawElements(gui); // implemented by concrete viewers
        gui.refresh();
    }

    protected abstract void drawElements(GUI gui) throws Exception;
}
