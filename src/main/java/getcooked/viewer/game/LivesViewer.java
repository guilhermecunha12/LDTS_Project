package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.Kitchen;
import getcooked.viewer.Viewer;

public class LivesViewer extends Viewer<Kitchen> {
    public LivesViewer(Kitchen kitchen) {
        super(kitchen);
    }

    @Override
    public void drawElements(GUI gui) {
        int h = gui.getHeight();
        String lifeColor = "#ff0000";
        gui.drawText(new Position(1, h - 9), "Lives: ", "#ffffff", "#000000");
        for (int i = 0; i < getModel().getLives(); i++) {
            Position curPos = new Position(8 + 4*i, h - 10);
            gui.drawRectangle(curPos, 3, 3, lifeColor);
        }
    }
}
