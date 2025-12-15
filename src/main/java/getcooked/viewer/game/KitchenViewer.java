package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.Kitchen;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Element;
import getcooked.model.game.elements.Floor;
import getcooked.model.game.elements.Tile;
import getcooked.model.game.elements.grabable.food.Kebab;
import getcooked.model.game.elements.interactable.Counter;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Grill;
import getcooked.model.game.elements.interactable.dispenser.CarrotDispenser;
import getcooked.model.game.elements.interactable.kitchenware.cutter.CounterCutter;
import getcooked.model.game.elements.interactable.DeliverySpot;
import getcooked.model.game.elements.interactable.Trash;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Pot;
import getcooked.model.game.elements.interactable.dispenser.PlateDispenser;
import getcooked.model.game.elements.interactable.dispenser.PotatoDispenser;
import getcooked.model.game.elements.interactable.kitchenware.cutter.KebabCutter;
import getcooked.viewer.Viewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class KitchenViewer extends Viewer<Kitchen> {
    private OrdersViewer ordersViewer;
    private LivesViewer livesViewer;

    public KitchenViewer(Kitchen kitchen) {
        super(kitchen); // initialise model field via superclass
        this.ordersViewer = new OrdersViewer(kitchen.getActiveOrders());
        this.livesViewer = new LivesViewer(kitchen);
    }

    @Override
    public void drawElements(GUI gui) throws Exception {
        // TODO: Refactor needed
        gui.drawText(new Position(gui.getWidth() - 20, 2),
                "Level: " + getModel().getLevel(), "#ffffff");

        List<Tile> tiles = getModel().getTiles();

        drawElements(gui, filterOfType(Floor.class, tiles), new FloorViewer("sprites/floor.png"));
        drawElements(gui, filterOfType(Counter.class, tiles), new CounterViewer());
        drawElements(gui, filterOfType(PotatoDispenser.class, tiles), new PotatoDispenserViewer("sprites/potato_dispenser.png"));
        drawElements(gui, filterOfType(CarrotDispenser.class, tiles), new CarrotDispenserViewer("sprites/carrot_dispenser.png"));
        drawElements(gui, filterOfType(DeliverySpot.class, tiles), new DeliverySpotViewer("sprites/delivery_spot.png"));
        drawElements(gui, filterOfType(Trash.class, tiles), new TrashViewer("sprites/trash.png"));
        drawElements(gui, filterOfType(Pot.class, tiles), new PotViewer());
        drawElements(gui, filterOfType(PlateDispenser.class, tiles), new PlateDispenserViewer("sprites/plate_dispenser.png"));
        drawElements(gui, filterOfType(CounterCutter.class, tiles), new CounterCutterViewer());
        drawElements(gui, filterOfType(Grill.class, tiles), new GrillViewer());
        drawElements(gui,filterOfType(KebabCutter.class, tiles), new KebabCutterViewer());

        List<Chef> chefs = getModel().getChefs();
        drawElement(gui, chefs.getFirst(), new ChefViewer(1));
        if (chefs.size() > 1) drawElement(gui, chefs.get(1), new ChefViewer(2));

        // update the viewers because the active orders and lives could have changed
        this.ordersViewer = new OrdersViewer(getModel().getActiveOrders());
        ordersViewer.drawElements(gui);
        this.livesViewer = new LivesViewer(getModel());
        livesViewer.drawElements(gui);
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) throws IOException, URISyntaxException, FontFormatException {
        for (T element: elements) {
            drawElement(gui, element, viewer);
        }
    }

    // generic method with type T
    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) throws IOException, URISyntaxException, FontFormatException {
        viewer.draw(element, gui);
    }

    // This is a filter that will aid on drawing each type of element from the tiles list
    private <T extends Tile> List<T> filterOfType(Class<T> tileSubclass, List<Tile> all) {
        List<T> result = new ArrayList<>();
        // Filters tiles by their class
        for (Tile i : all) {
            // If the tiles is an instance of that class, we cast the tiles and return a parameterized
            // list of that subtype
            if (tileSubclass.isInstance(i)) result.add(tileSubclass.cast(i));
        }
        return result;
    }
}
