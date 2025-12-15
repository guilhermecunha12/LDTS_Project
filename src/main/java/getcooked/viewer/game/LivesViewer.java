package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.Kitchen;
import getcooked.viewer.Sprite;
import getcooked.viewer.Viewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class LivesViewer extends Viewer<Kitchen> {
    public LivesViewer(Kitchen kitchen) {
        super(kitchen);
    }

    @Override
    public void drawElements(GUI gui) throws IOException, URISyntaxException, FontFormatException {
        Sprite sprite = new Sprite("sprites/life.png");
        int h = gui.getHeight();
        String lifeColor = "#ff0000";
        gui.drawText(new Position(270, 20), "Lives: ", "#ffffff");
        for (int i = 0; i < getModel().getLives(); i++) {
            Position curPos = new Position(270 + 10*i, 30);
            sprite.draw(gui, curPos.getX(), curPos.getY());
        }
    }
}
