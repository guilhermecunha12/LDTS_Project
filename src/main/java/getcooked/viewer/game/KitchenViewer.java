package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.kitchen.Kitchen;
import getcooked.viewer.Viewer;

import java.util.List;

public class KitchenViewer extends Viewer<Kitchen> {
    public KitchenViewer(Kitchen kitchen) {
        super(kitchen); // initialise model field via superclass
    }

    @Override
    public void drawElements(GUI gui) {
        List<Chef> chefs = getModel().getChefs();
        drawElement(gui, chefs[0], new Chef1Viewer());
        if (chefs.size() > 1) drawElement(gui, chefs[1], new Chef2Viewer());

        drawElements(gui, getModel().getTrashes(), new TrashViewer());
        // other elements...
    }

    // generic method with type T
    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element: elements) {
            drawElement(gui, element, viewer);
        }
    }

    // generic method with type T
    private <T extends Element> void drawElement(GUI gui, Element element, ElementViewer<T> viewer) {
        viewer.draw(element, gui);
    }
}
