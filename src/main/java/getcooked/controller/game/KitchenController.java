package getcooked.controller.game;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.PlayingSession;
import getcooked.model.game.kitchen.Kitchen;
import getcooked.model.game.kitchen.LoaderKitchenBuilder;
import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.menu.EscapeMenu;
import getcooked.states.PlayingState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class KitchenController extends Controller<Kitchen> {
    private final PlayingSession playingSession; // hold a reference to the playingSession to change its paused state
    private final List<Controller<Kitchen>> chefControllers = new ArrayList<>();
    private final OrdersController ordersController;

    public KitchenController(Kitchen kitchen, PlayingSession playingSession) {
        super(kitchen);
        this.playingSession = playingSession;

        List<Chef> chefs = kitchen.getChefs();
        if (chefs.size() >= 1) {
            chefControllers.add(new ChefController1(chefs.get(0), kitchen));
        }
        if (chefs.size() >= 2) {
            chefControllers.add(new ChefController2(chefs.get(1), kitchen));
        }

        this.ordersController = new OrdersController(kitchen);

    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        Kitchen kitchen = getModel();
        ordersController.step(game, actions, time);

        if (kitchen.getOrders().isEmpty() && kitchen.getActiveOrders().isEmpty()) {
            this.playingSession.nextLevel();
        }

        if (actions == null || actions.isEmpty()) return;

        // handle global actions (pause)
        for (GUI.ACTION action : actions) {
            if (action == GUI.ACTION.ESCAPE) {
                this.playingSession.togglePause();
            }
            chefControllers.get(0).step(game, actions, time);
            if (chefControllers.size() == 2) chefControllers.get(1).step(game, actions, time);
        }
    }
}