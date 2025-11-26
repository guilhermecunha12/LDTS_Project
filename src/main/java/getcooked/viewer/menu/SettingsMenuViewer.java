package getcooked.viewer.menu;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.menu.SettingsMenu;
import getcooked.viewer.Viewer;

public class SettingsMenuViewer extends Viewer<SettingsMenu> {
    public SettingsMenuViewer(SettingsMenu settingsMenu) {
        super(settingsMenu);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.changeBackgroundColor("#468499"); // background color
        int middleX = gui.getWidth() / 2;
        String bgColor = gui.getBackgroundColor();
        gui.drawText(new Position(middleX - 6, 5), "Settings Menu", "#ffcf40", bgColor);

        int i = 0;
        for (; i < getModel().getNumberEntries() - 1; i++) { // draw the options
            gui.drawText(new Position(5, 10 + 4*i), getModel().getEntry(i),
                    getModel().isSelected(i)? "#ffff00" : "#000000", bgColor);
        }

        // draw back button
        Position posBack = new Position(gui.getWidth() - 10, gui.getHeight() - 3);
        gui.drawTextBox(posBack, getModel().getEntry(i), "#000000",
                getModel().isSelected(i)? "#ffff00" : "#a9a9a9", 1);
    }
}
