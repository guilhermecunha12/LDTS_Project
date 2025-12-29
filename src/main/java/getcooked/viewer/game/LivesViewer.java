package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.Kitchen;
import getcooked.viewer.Sprite;
import getcooked.viewer.Viewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static getcooked.viewer.Sprite.SCALE;

public class LivesViewer extends Viewer<Kitchen> {
    public LivesViewer(Kitchen kitchen) {
        super(kitchen);
    }

    @Override
    public void drawElements(GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/life.png");
        gui.drawText(new Position(270, 20), "Lives: ", "#ffffff");
        Position pos = new Position(270, 30);
        for (int i = 0; i < getModel().getLives(); i++) {
            sprite.draw(gui, pos.x() / SCALE, pos.y() / SCALE, 5+10*i, 5);
        }
    }
}
