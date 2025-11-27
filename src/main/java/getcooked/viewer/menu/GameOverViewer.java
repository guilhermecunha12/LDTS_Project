package getcooked.viewer.menu;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.menu.GameOverMenu;
import getcooked.viewer.Viewer;

public class GameOverViewer extends Viewer<GameOverMenu> {

    public GameOverViewer(GameOverMenu model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) {
        gui.changeBackgroundColor("#468499");
        gui.paint();
        // draw game title in the middle
        int middleX = gui.getWidth() / 2;
        String bgColor = gui.getBackgroundColor();
        gui.drawText(new Position(middleX - 5, gui.getHeight()/12), "Get Cooked", "#ffcf40", bgColor);

        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            String entry = getModel().getEntry(i);
            int padding = 1;
            // incrementing y using i multiplied by a factor 4
            Position topLeftPos = new Position(middleX - (entry.length() / 2) - padding, 10 + 4*i);

            gui.drawTextBox(topLeftPos, entry, "#000000",
                    getModel().isSelected(i)? "#ffff00" : "#a9a9a9", padding);
        }
    }

}
