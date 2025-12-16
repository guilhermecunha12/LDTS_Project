package getcooked.viewer.menu;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.menu.EscapeMenu;
import getcooked.viewer.Viewer;

public class EscapeMenuViewer extends Viewer<EscapeMenu> {
    public EscapeMenuViewer(EscapeMenu escapeMenu) {
        super(escapeMenu); // initialise model field via superclass
    }

    @Override
    public void drawElements(GUI gui) {
        int w = gui.getWidth();
        int h = gui.getHeight();
        int middleX = w / 2;
        int middleY = h / 2;

        int side = 90; // side length of the square rectangle
        Position topleftPos = new Position(w/2 - side/2, h/2 - side/2);
        String rectangleColor = "#808080"; // gray
        gui.drawRectangle(topleftPos, side, side, rectangleColor);
        gui.drawText(new Position(middleX - side/4, middleY- side/2 + side/10), "Escape Menu", "#ffcf40");

        Position posResume = new Position(middleX - 13, middleY - 10);
        Position posExit = new Position(middleX - 32, middleY);

        for(int i = 0; i < getModel().getNumberEntries(); i++) {
            Position curPos = posResume;
            if (i == 1) curPos = posExit;

            gui.drawText(curPos, getModel().getEntry(i),
                    getModel().isSelected(i)? "#ffff00": "#ffffff");
        }
    }
}