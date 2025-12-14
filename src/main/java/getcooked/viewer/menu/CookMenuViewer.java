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
        gui.changeBackgroundColor("#468499");
        gui.paint();

        int middleX = gui.getWidth() / 2;
        String bgColor = gui.getBackgroundColor();
        gui.drawText(new Position(middleX - 33, gui.getHeight()/12), "Get Ready To Cook", "#ffcf40", bgColor);

        // define positions for the text boxes
        Position pos1Player = new Position((gui.getWidth() / 4) - 5, (gui.getHeight() / 2) - 15);
        Position pos2Player = new Position((3 * gui.getWidth() / 4) - 30, (gui.getHeight() / 2) - 15);
        Position posBack = new Position(gui.getWidth() - 50, gui.getHeight() - 35);

        // this is a fixed, not scalable menu
        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            Position curPos = pos1Player;
            if (i == 1) curPos = pos2Player;
            else if (i == 2) curPos = posBack;

            gui.drawText(curPos, getModel().getEntry(i),
                    getModel().isSelected(i)? "#ffff00": "#000000", bgColor);
        }
    }
}
