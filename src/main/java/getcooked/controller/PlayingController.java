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
    private KitchenController kitchenController; // not final, because kitchen will change during levels
    private final EscapeMenuController escapeMenuController;

    public PlayingController(PlayingSession playingSession) {
        super(playingSession);
        // IMPORTANT: pass the playingSession as argument because the EscapeMenuController can modify the paused state of the playingSession
        this.kitchenController = new KitchenController(playingSession.getKitchen(), playingSession);
        // IMPORTANT: pass the playingSession as argument because the KitchenController can modify the paused state of the playingSession
        this.escapeMenuController = new EscapeMenuController(playingSession.getEscapeMenu(), playingSession);
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        PlayingSession curPlayingSession = getModel();
        if (curPlayingSession.isPaused()) {
            this.escapeMenuController.step(game, actions, time);
        } else {
            Kitchen curKitchen = kitchenController.getModel();
            if (curKitchen.getLevel() != curPlayingSession.getLevel()) { // in this case, update the controller's model to the new kitchen
                // IMPORTANT: pass the playingSession as argument because the KitchenController can modify the paused state of the playingSession
                this.kitchenController = new KitchenController(curPlayingSession.getKitchen(), curPlayingSession);
            }
            this.kitchenController.step(game, actions, time);
        }
    }
}
