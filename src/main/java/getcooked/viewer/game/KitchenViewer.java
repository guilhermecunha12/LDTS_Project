package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.Kitchen;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Tile;
import getcooked.model.game.elements.interactable.dispenser.*;
import getcooked.viewer.Viewer;

import java.awt.*;
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
        gui.drawText(new Position(gui.getWidth() - 85, 2),
                "Level: " + getModel().getLevel(), "#ffffff");

        List<Tile> tiles = getModel().getTiles();
        for(Tile tile : tiles) {
            tile.getViewer().draw(tile, gui);
        }

        List<Chef> chefs = getModel().getChefs();
        for(Chef chef : chefs) {
            chef.getViewer().draw(chef, gui);
        }

        // update the viewers because the active orders and lives could have changed
        this.ordersViewer = new OrdersViewer(getModel().getActiveOrders());
        ordersViewer.drawElements(gui);
        this.livesViewer = new LivesViewer(getModel());
        livesViewer.drawElements(gui);
    }
}
