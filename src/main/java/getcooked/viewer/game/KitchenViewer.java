package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.kitchen.Kitchen;
import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.Element;
import getcooked.model.menu.EscapeMenu;
import getcooked.viewer.Viewer;
import getcooked.viewer.menu.EscapeMenuViewer;

import java.util.List;

public class KitchenViewer extends Viewer<Kitchen> {
    public KitchenViewer(Kitchen kitchen) {
        super(kitchen); // initialise model field via superclass
    }

    @Override
    public void drawElements(GUI gui) {
        List<Chef> chefs = getModel().getChefs();
        drawElement(gui, chefs.getFirst(), new Chef1Viewer());
        if (chefs.size() > 1) drawElement(gui, chefs.get(1), new Chef2Viewer());

        drawElements(gui, getModel().getTrashes(), new TrashViewer());
        // TODO: draw all the other elements...

        // and then, check if the EscapeMenu is on
        // TODO: review if EscapeMenu should exists as a state (in reality, the state is Playing)
        if (getModel().isEscapeMenuOn()) {
            EscapeMenuViewer escapeMenuViewer = new EscapeMenuViewer(new EscapeMenu());
            escapeMenuViewer.drawElements(gui);
        }
    }

    // generic method with type T
    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element: elements) {
            drawElement(gui, element, viewer);
        }
    }

    // generic method with type T
    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        viewer.draw(element, gui);
    }
}
