package getcooked.viewer.menu;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.menu.CookMenu;
import getcooked.viewer.Viewer;

public class CookMenuViewer extends Viewer<CookMenu> {
    public CookMenuViewer (CookMenu cookMenu) {
        super(cookMenu); // initialise model field via superclass
    }

    @Override
    public void drawElements(GUI gui) {
        gui.changeBackgroundColor("#468499"); // background color
        int middleX = gui.getWidth() / 2;
        String bgColor = gui.getBackgroundColor();
        gui.drawText(new Position(middleX - 8, 5), "Get Ready To Cook", "#ffcf40", bgColor);

        // define positions for the text boxes
        Position pos1Player = new Position(gui.getWidth() / 4, gui.getHeight() / 2);
        Position pos2Player = new Position(3 * gui.getWidth() / 4, gui.getHeight() / 2);
        Position posBack = new Position(gui.getWidth() - 10, gui.getHeight() - 2);

        // this is a fixed, not scalable menu
        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            Position curPos = pos1Player;
            if (i == 1) curPos = pos2Player;
            else if (i == 2) curPos = posBack;

            gui.drawTextBox(curPos, getModel().getEntry(i), "#000000",
                    getModel().isSelected(i)? "#ffff00": "#a9a9a9", 1);
        }
    }
}
