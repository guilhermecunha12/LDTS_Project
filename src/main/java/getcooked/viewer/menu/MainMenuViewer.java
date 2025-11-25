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
        // draw game title in the middle
        int middleX = gui.getWidth() / 2;
        gui.drawText(new Position(middleX, 5), "Get Cooked", "#ffcf40");

        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            Position posToDraw = new Position(middleX, 10 + 4*i);

            if (getModel().isSelected(i)) {
                gui.drawTextBox(posToDraw, getModel().getEntry(i), "#ffffff", "#ffff00", 1);
            } else {
                gui.drawTextBox(posToDraw, getModel().getEntry(i), "#ffffff", "#eeeeee", 1);
            }
        }
    }
}
