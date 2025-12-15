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
        gui.changeBackgroundColor("#468499");
        gui.paint();

        int middleX = gui.getWidth() / 2;
        gui.drawText(new Position(middleX - 13, gui.getHeight() / 12), "Settings", "#ffcf40");


        Position posMusic = new Position(middleX- 35, (gui.getHeight() / 2) - 30);
        Position posSound = new Position(middleX - 52, (gui.getHeight() / 2) - 15);
        Position posBack = new Position(gui.getWidth() - 50, gui.getHeight() - 35);

        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            Position curPos = posMusic;
            if (i == 1) curPos = posSound;
            else if (i==2) curPos = posBack;

            gui.drawText(curPos, getModel().getEntry(i),
                    getModel().isSelected(i) ? "#ffff00" : "#000000");
        }
    }
}