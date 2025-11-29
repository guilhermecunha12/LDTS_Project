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

        int side = 21;
        Position topleftPos = new Position(w/2 - side/2, h/2 - side/2);
        String rectangleColor = "#808080"; // gray
        gui.drawRectangle(topleftPos, side, side, rectangleColor);
        gui.drawText(new Position(w/2 - 5, h/2 - side/2 + side/10), "Escape Menu", "#ffcf40", rectangleColor);

        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            String entry = getModel().getEntry(i);
            Position curPos = new Position(w/2 - entry.length()/2, h/2 - side/2 + 4*side/10 + 4*i);
            gui.drawText(curPos, entry,
                    getModel().isSelected(i)? "#ffff00" : "#ffffff", rectangleColor);
        }
    }
}