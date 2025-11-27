package getcooked.controller.game;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.game.kitchen.Kitchen;
import getcooked.model.game.kitchen.LoaderKitchenBuilder;
import getcooked.model.game.kitchen.Order;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class OrdersController extends Controller<Kitchen> {
    private int timeToAddNewOrder;

    public OrdersController(Kitchen kitchen) {
        super(kitchen);
        this.timeToAddNewOrder = 0; // immediately add the first order
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        Kitchen kitchen = getModel();
        List<Order> activeOrders = kitchen.getActiveOrders();
        for (Order activeOrder : activeOrders) { // decrease 1 frame to each remaining time
            activeOrder.tick();
            if (activeOrder.isExpired()) { // lose a life and remove that order from activeOrders
                kitchen.lives--; //TODO
                kitchen.removeActiveOrder(activeOrder);
            }
        }

        if (timeToAddNewOrder <= 0  && activeOrders.size() < kitchen.MAX_ACTIVE_ORDERS && !kitchen.getOrders().isEmpty()) {
            Order newOrder = kitchen.getOrders().removeLast();
            kitchen.addActiveOrder(newOrder);
            timeToAddNewOrder = 401; //TODO: consider the current level
        }

        timeToAddNewOrder--;

        if (kitchen.getOrders().isEmpty()) {
            kitchen = new LoaderKitchenBuilder(kitchen.getLevel() + 1).createKitchen();
        }
    }
}
