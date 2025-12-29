package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.Element;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public interface ElementViewer<T extends Element> {
    void draw(T element, GUI gui) throws IOException, URISyntaxException, FontFormatException;
}
