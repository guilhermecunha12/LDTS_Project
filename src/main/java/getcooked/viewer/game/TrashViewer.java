package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.Trash;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class TrashViewer implements ElementViewer<Trash> {

    private final Sprite sprite;
    public TrashViewer(String filepath) throws IOException {
        this.sprite = new Sprite(filepath);
    }

    @Override
    public void draw(Trash trash, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        int pixelX = trash.getPosition().getX() * 5;
        int pixelY = trash.getPosition().getY() * 5;
        sprite.draw(gui, pixelX, pixelY);
    }
}

