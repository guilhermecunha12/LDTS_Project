package getcooked.controller.game;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.game.Kitchen;
import getcooked.model.game.KitchenModelStorage;
import getcooked.model.game.LoaderKitchenBuilder;
import getcooked.model.game.Order;
import getcooked.model.game.elements.Chef;
import getcooked.model.menu.EscapeMenu;
import getcooked.model.menu.GameOverMenu;
import getcooked.states.game.KitchenState;
import getcooked.states.menu.EscapeMenuState;
import getcooked.states.menu.GameOverState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class KitchenController extends Controller<Kitchen> {

    public KitchenController(Kitchen kitchen) {
        super(kitchen);
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        Kitchen kitchen = getModel();
        // handle global actions (pause)
        if (actions.contains(GUI.ACTION.ESCAPE)) {
            KitchenModelStorage.storeKitchen(kitchen);
            game.changeState(new EscapeMenuState(new EscapeMenu()));
        }

        for(int i = 0; i < kitchen.getChefs().size(); i++) {
            Chef chef = kitchen.getChefs().get(i);
            ChefController chefController = new ChefController(chef, kitchen, i);
            chefController.step(game, actions, time);
        }

        this.manageOrders();
        if (kitchen.getLives() == 0) {
            game.changeState(new GameOverState(new GameOverMenu()));
            return;
        }

        if (kitchen.getOrders().isEmpty() && kitchen.getActiveOrders().isEmpty()) {
            game.changeState(new KitchenState(
                    new LoaderKitchenBuilder(kitchen.getLevel() + 1, kitchen.getNumPlayers(), kitchen.getLives()).createKitchen()));
        }
    }

    private void manageOrders() {
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

        int timeForNewOrder = kitchen.getTimeForNewOrder();
        if (timeForNewOrder <= 0  && activeOrders.size() < Kitchen.MAX_ACTIVE_ORDERS && !kitchen.getOrders().isEmpty()) {
            Order newOrder = kitchen.getOrders().removeLast();
            kitchen.addActiveOrder(newOrder);
            kitchen.setTimeForNewOrder((int)(400/Math.sqrt(kitchen.getLevel()))); // gets lower each level
        }

        else kitchen.setTimeForNewOrder(timeForNewOrder - 1);
    }
}