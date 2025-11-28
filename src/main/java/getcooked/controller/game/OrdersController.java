package getcooked.controller.game;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.game.kitchen.Kitchen;
import getcooked.model.game.kitchen.LoaderKitchenBuilder;
import getcooked.model.game.kitchen.Order;
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

        // Used iterators in order
        while (iterator.hasNext()) {
            Order activeOrder = iterator.next();
            activeOrder.tick();
            if (activeOrder.isExpired()) {
                int currentLives = kitchen.getLives();
                kitchen.setLives(currentLives - 1); //TODO
                iterator.remove();
            }
        }

        if (timeToAddNewOrder <= 0  && activeOrders.size() < Kitchen.MAX_ACTIVE_ORDERS && !kitchen.getOrders().isEmpty()) {
            Order newOrder = kitchen.getOrders().removeLast();
            kitchen.addActiveOrder(newOrder);
            timeToAddNewOrder = 401; //TODO: consider the current level
        }

        timeToAddNewOrder--;

        if (kitchen.getLives() == 0){
            // When the player loses all lives go to the GameOverMenu
            game.changeState(new GameOverState(new GameOverMenu()));
            return;
        }

        if (kitchen.getOrders().isEmpty() && kitchen.getActiveOrders().isEmpty()) {
            // Change level
            kitchen.setLevel(kitchen.getLevel() + 1);
            // Create new kitchen
            kitchen = new LoaderKitchenBuilder(kitchen.getLevel(), kitchen.getNumPlayers()).createKitchen();
        }
    }
}
