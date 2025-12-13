package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.Floor;
import getcooked.model.game.elements.interactable.Counter;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class FloorViewer implements ElementViewer<Floor> {
    private final Sprite sprite;
    public FloorViewer(String path) throws Exception {
        this.sprite = new Sprite(path);
        //TODO: fazer switch case para diferentes sprites de counter, é preicso mudaar o map
    }
    @Override
    public void draw(Floor element, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = element.getPosition().getX() * 8;
        int pixelY = element.getPosition().getY() * 8;
        sprite.draw(gui, pixelX, pixelY);
    }
}
