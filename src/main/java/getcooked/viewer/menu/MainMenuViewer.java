package getcooked.viewer.menu;

import getcooked.model.Position;
import getcooked.model.menu.MainMenu;
import getcooked.viewer.Viewer;
import getcooked.gui.GUI;

public class MainMenuViewer extends Viewer<MainMenu> {
    public MainMenuViewer(MainMenu mainMenu) {
        super(mainMenu); // initialise model field via superclass
        }

    @Override
    public void drawElements(GUI gui) {
        gui.changeBackgroundColor("#468499");
        gui.paint();

        int middleX = gui.getWidth() / 2;
        String bgColor = gui.getBackgroundColor();
        gui.drawText(new Position(middleX - 20, gui.getHeight()/12), "Get Cooked", "#ffcf40", bgColor);

        //positions
        Position posCook = new Position(middleX -9, (gui.getHeight() / 2)- 45);
        Position posSettings = new Position(middleX - 15, (gui.getHeight() / 2) - 35);
        Position posExit = new Position(middleX - 8, (gui.getHeight() / 2) - 25);
        // draw menu entries
        for(int i = 0; i < getModel().getNumberEntries(); i++) {
            Position curPos;
            if (i == 0) curPos = posCook;
            else if (i == 1) curPos = posSettings;
            else curPos = posExit;

            gui.drawText(curPos, getModel().getEntry(i),
                    getModel().isSelected(i)? "#ffff00": "#000000", bgColor);
        }
    }
}