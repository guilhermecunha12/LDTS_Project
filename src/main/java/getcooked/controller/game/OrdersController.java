package getcooked.controller.game;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.game.Kitchen;
import getcooked.model.game.Order;
import getcooked.model.menu.GameOverMenu;
import getcooked.states.menu.GameOverState;

import java.io.IOException;
import java.util.Iterator;
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
        Iterator<Order> iterator = activeOrders.iterator();

        // Used iterators because elements are being removed from the iterable, causing errors in a for-each loop
        while (iterator.hasNext()) {
            Order activeOrder = iterator.next();
            activeOrder.tick();
            if (activeOrder.isExpired()) {
                kitchen.decreaseLives();
                iterator.remove();
            }
        }

        if (timeToAddNewOrder <= 0  && activeOrders.size() < Kitchen.MAX_ACTIVE_ORDERS && !kitchen.getOrders().isEmpty()) {
            Order newOrder = kitchen.getOrders().removeLast();
            kitchen.addActiveOrder(newOrder);
            timeToAddNewOrder = (int)(400/Math.sqrt(kitchen.getLevel())); // gets lower each level
        }

        timeToAddNewOrder--;
    }
}

