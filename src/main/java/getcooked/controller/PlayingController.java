package getcooked.controller;

import getcooked.Game;
import getcooked.controller.game.KitchenController;
import getcooked.controller.menu.EscapeMenuController;
import getcooked.gui.GUI;
import getcooked.model.PlayingSession;
import getcooked.model.game.kitchen.Kitchen;

import java.io.IOException;
import java.util.Set;

// this controller has a KitchenController and a EscapeMenuController
public class PlayingController extends Controller<PlayingSession> {
    private final KitchenController kitchenController;
    private final EscapeMenuController escapeMenuController;

    public PlayingController(PlayingSession playingSession) {
        super(playingSession);
        this.kitchenController = new KitchenController(playingSession.getKitchen(), playingSession);
        this.escapeMenuController = new EscapeMenuController(playingSession.getEscapeMenu(), playingSession);
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        if (getModel().isPaused()) {
            // IMPORTANT: pass the playingSession as argument because the EscapeMenuController can modify the paused state of the playingSession
            this.escapeMenuController.step(game, actions, time);
        } else {
            // IMPORTANT: pass the playingSession as argument because the KitchenController can modify the paused state of the playingSession
            this.kitchenController.step(game, actions, time);
        }
    }
}
