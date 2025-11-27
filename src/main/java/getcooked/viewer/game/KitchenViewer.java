package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.kitchen.Kitchen;
import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.Element;
import getcooked.model.game.kitchen.elements.interactable.Counter;
import getcooked.model.game.kitchen.elements.interactable.DeliverySpot;
import getcooked.model.game.kitchen.elements.interactable.Interactable;
import getcooked.model.game.kitchen.elements.interactable.Trash;
import getcooked.model.game.kitchen.elements.interactable.cookware.Pot;
import getcooked.model.game.kitchen.elements.interactable.dispenser.Dispenser;
import getcooked.model.game.kitchen.elements.interactable.dispenser.PotatoDispenser;
import getcooked.model.menu.EscapeMenu;
import getcooked.viewer.Viewer;
import getcooked.viewer.menu.EscapeMenuViewer;

import java.util.ArrayList;
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

        // TODO: draw all the other elements...
        List<Interactable> interactables = getModel().getInteractables();
        drawElements(gui, filterOfType(Counter.class, interactables), new CounterViewer());
        drawElements(gui, filterOfType(PotatoDispenser.class, interactables), new PotatoDispenserViewer());
        drawElements(gui, filterOfType(DeliverySpot.class, interactables), new DeliverySpotViewer());
        drawElements(gui, filterOfType(Trash.class, interactables), new TrashViewer());
        drawElements(gui, filterOfType(Pot.class, interactables), new PotViewer());
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

    // This is a filter that will aid on drawing each type of element from the interactables list
    private <T extends Interactable> List<T> filterOfType(Class<T> interactableClass, List<Interactable> all) {
        List<T> result = new ArrayList<>();
        // Filters interactable by their class
        for (Interactable i : all) {
            // If the interactable is an instance of that class, we cast the interactable and return a parameterized
            // list of that type
            if (interactableClass.isInstance(i)) result.add(interactableClass.cast(i));
        }
        return result;
    }

}
