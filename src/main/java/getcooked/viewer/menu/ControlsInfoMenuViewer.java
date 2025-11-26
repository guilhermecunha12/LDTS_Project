package getcooked.viewer.menu;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.menu.ControlsInfoMenu;
import getcooked.viewer.Viewer;

public class ControlsInfoMenuViewer extends Viewer<ControlsInfoMenu> {

    public ControlsInfoMenuViewer(ControlsInfoMenu controlsInfoMenu) {
        super(controlsInfoMenu);
    }

    @Override
    protected void drawElements(GUI gui) {
        gui.changeBackgroundColor("#468499"); // background color
        int middleX = gui.getWidth() / 2;
        String bgColor = gui.getBackgroundColor();
        gui.drawText(new Position(middleX - 4, 5), "Controls", "#ffcf40", bgColor);
        if(getModel().isOnePlayer()){

        }
        else{

        }
    }
}
