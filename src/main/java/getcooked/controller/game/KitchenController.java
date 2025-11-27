package getcooked.controller.game;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.controller.PlayingController;
import getcooked.gui.GUI;
import getcooked.model.PlayingSession;
import getcooked.model.game.kitchen.Kitchen;
import getcooked.model.game.kitchen.elements.Chef;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class KitchenController extends Controller<Kitchen> {
    private final PlayingSession playingSession; // hold a reference to the playingSession to change its paused state
    private final List<ChefController> chefControllers = new ArrayList<>();

    public KitchenController(Kitchen kitchen, PlayingSession playingSession) {
        super(kitchen);
        this.playingSession = playingSession;
        for (Chef chef : kitchen.getChefs()) {
            this.chefControllers.add(new ChefController(chef, kitchen));
        }
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        for(GUI.ACTION action : actions) {
            if(action == GUI.ACTION.ESCAPE){
                this.playingSession.togglePause();
            }

        }
    }
}
