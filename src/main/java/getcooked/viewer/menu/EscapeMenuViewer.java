package getcooked.viewer.menu;

import com.googlecode.lanterna.TextColor;
import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.kitchen.Kitchen;
import getcooked.model.menu.EscapeMenu;
import getcooked.viewer.Viewer;

public class EscapeMenuViewer extends Viewer<EscapeMenu> {
    public EscapeMenuViewer(EscapeMenu escapeMenu) {
        super(escapeMenu); // initialise model field via superclass
    }

    @Override
    public void drawElements(GUI gui) {
        int rectangleWidth = 20;
        int rectangleHeight = 20;
        Position topleftPos = new Position(gui.getWidth()/2 - rectangleWidth/2, gui.getHeight()/2 - rectangleHeight/2);
        String rectangleColor = "#808080"; // gray
        gui.drawRectangle(topleftPos, rectangleWidth, rectangleHeight, rectangleColor);

        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            String entry = getModel().getEntry(i);
            Position curPos = new Position(gui.getWidth()/2 - entry.length()/2, gui.getHeight()/2 - rectangleHeight/2 + 2 + 4*i);
            gui.drawText(curPos, entry,
                    getModel().isSelected(i)? "#ffff00" : "#ffffff", rectangleColor);
        }
    }
}