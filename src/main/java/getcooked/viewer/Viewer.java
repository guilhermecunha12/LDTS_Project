package getcooked.viewer;

import getcooked.gui.GUI;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class Viewer<T> {
    private final T model;

    protected Viewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return this.model; // for concrete viewers
    }

    public void draw(GUI gui) throws IOException, URISyntaxException, FontFormatException {
        gui.clear();
        this.drawElements(gui); // implemented by concrete viewers
        gui.refresh();
    }

    protected abstract void drawElements(GUI gui) throws IOException, URISyntaxException, FontFormatException;
}
