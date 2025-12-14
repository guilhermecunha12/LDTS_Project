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
        gui.changeBackgroundColor("#468499"); // background color
        gui.paint();
        int middleX = gui.getWidth() / 2;
        String bgColor = gui.getBackgroundColor();
        gui.drawText(new Position(middleX - 19, gui.getHeight()/12), "Get Cooked", "#ffcf40", bgColor);

        int i = 0;
        for (; i < getModel().getNumberEntries(); i++) {
            gui.drawText(new Position(middleX - 15, 40 + 19*i), getModel().getEntry(i),
                    getModel().isSelected(i)? "#ffff00" : "#000000", bgColor);
        }
    }
}