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
                int currentLives = kitchen.getLives();
                kitchen.setLives(currentLives - 1); //TODO
                kitchen.removeActiveOrder(activeOrder);
            }
        }

        if (timeToAddNewOrder <= 0  && activeOrders.size() < Kitchen.MAX_ACTIVE_ORDERS && !kitchen.getOrders().isEmpty()) {
            Order newOrder = kitchen.getOrders().removeLast();
            kitchen.addActiveOrder(newOrder);
            timeToAddNewOrder = 401; //TODO: consider the current level
        }

        timeToAddNewOrder--;

        if (kitchen.getOrders().isEmpty()) {
            // Change level
            kitchen.setLevel(kitchen.getLevel() + 1);
            // Create new kitchen
            kitchen = new LoaderKitchenBuilder(kitchen.getLevel()).createKitchen();
        }

        if(kitchen.getLives() == 0){
            // When the player loses all lives go to the GameOverMenu
            game.changeState(new GameOverState(new GameOverMenu()));
        }

    }
}
