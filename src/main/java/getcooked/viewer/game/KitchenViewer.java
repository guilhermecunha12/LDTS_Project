package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.kitchen.Kitchen;
import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.Element;
import getcooked.model.game.kitchen.elements.interactable.Counter;
import getcooked.model.game.kitchen.elements.interactable.Interactable;
import getcooked.model.game.kitchen.elements.interactable.Trash;
import getcooked.model.game.kitchen.elements.interactable.dispenser.Dispenser;
import getcooked.model.game.kitchen.elements.interactable.dispenser.PotatoDispenser;
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

        // TODO: draw all the other elements...
        List<Interactable> interactables = getModel().getInteractables();
        for(Interactable interactable : interactables) {
            if(interactable instanceof Trash) {
                Trash trash = (Trash) interactable;
                drawElement(gui, trash, new TrashViewer());
            }
            else if (interactable instanceof Counter) {
                Counter counter = (Counter) interactable;
                drawElement(gui, counter, new CounterViewer());
            }
            else if(interactable instanceof Dispenser){
                Dispenser dispenser = (Dispenser) interactable;
                PotatoDispenser potatoDispenser = (PotatoDispenser) dispenser;
                drawElement(gui, potatoDispenser, new PotatoDispenserViewer() );
            }


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
