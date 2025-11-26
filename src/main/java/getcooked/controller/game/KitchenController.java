package getcooked.controller.game;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.controller.PlayingController;
import getcooked.gui.GUI;
import getcooked.model.PlayingSession;
import getcooked.model.game.kitchen.Kitchen;

import java.io.IOException;
import java.util.Set;

public class KitchenController extends Controller<Kitchen> {
    private final PlayingSession playingSession; // hold a reference to the playingSession to change its paused state
    //TODO: add all the other controllers (chef, interactables,...)

    public KitchenController(Kitchen kitchen, PlayingSession playingSession) {
        super(kitchen);
        this.playingSession = playingSession;
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        for(GUI.ACTION action : actions) {
            switch (action) {
                //TODO: handle all other inputs
                case ESCAPE:
                    this.playingSession.togglePause();
            }
        }
    }
}
