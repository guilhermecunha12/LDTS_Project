package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.elements.interactable.Trash;
import getcooked.viewer.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class TrashViewer extends ElementViewer<Trash> {

    @Override
    public void draw(Trash trash, GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/trash.png");
        sprite.draw(gui, trash.getX(), trash.getY(), 0);
    }
}

