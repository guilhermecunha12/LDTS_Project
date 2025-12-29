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

        int middleX = gui.getWidth() / 2;
        gui.drawText(new Position(middleX - 9, gui.getHeight()/12), "Game Over", "#ffcf40");
        gui.drawText(new Position(middleX - 24, gui.getHeight()/6 + 5), "Delivered dishes: "
                + getModel().getOrdersDelivered(), "#ffcf40");

        Position posBackToMainMenu = new Position(middleX - 23, gui.getHeight()/2 - 10);
        Position posExit = new Position(middleX - 8, gui.getHeight()/2);

        for(int i = 0; i < getModel().getNumberEntries(); i++) {
            Position curPos = posBackToMainMenu;
            if (i == 1) curPos = posExit;

            gui.drawText(curPos, getModel().getEntry(i),
                    getModel().isSelected(i)? "#ffff00": "#000000");
        }
    }

}
