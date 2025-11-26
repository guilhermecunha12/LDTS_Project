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
        String bgColor = gui.getBackgroundColor();
        gui.drawText(new Position(middleX - 5, 5), "Get Cooked", "#ffcf40", bgColor);

        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            String entry = getModel().getEntry(i);
            // incrementing y using i multiplied by a factor 4
            Position topLeftPos = new Position(middleX - (entry.length() / 2), 10 + 4*i);

            gui.drawTextBox(topLeftPos, entry, "#ffffff",
                   getModel().isSelected(i)? "#ffff00" : "#eeeeee", 1);
        }
    }
}
