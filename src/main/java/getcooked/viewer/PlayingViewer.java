package getcooked.viewer;

import getcooked.controller.game.KitchenController;
import getcooked.gui.GUI;
import getcooked.model.PlayingSession;
import getcooked.model.game.kitchen.Kitchen;
import getcooked.model.menu.EscapeMenu;
import getcooked.viewer.game.KitchenViewer;
import getcooked.viewer.menu.EscapeMenuViewer;

// this viewer has a KitchenViewer and a EscapeMenuViewer
public class PlayingViewer extends Viewer<PlayingSession> {
    private KitchenViewer kitchenViewer; // not final, because kitchen will change during levels
    private final EscapeMenuViewer escapeMenuViewer;

    public PlayingViewer(PlayingSession playingSession) {
        super(playingSession); // initialise model field via superclass
        // create two viewers initialising their models via their constructors (via superclass)
        this.kitchenViewer = new KitchenViewer(playingSession.getKitchen());
        this.escapeMenuViewer = new EscapeMenuViewer(playingSession.getEscapeMenu());
    }

    @Override
    public void drawElements(GUI gui) {
        PlayingSession curPlayingSession = getModel();
        Kitchen curKitchen = kitchenViewer.getModel();
        if (curKitchen.getLevel() != curPlayingSession.getLevel()) {  // in this case, update the controller's model to the new kitchen
            this.kitchenViewer = new KitchenViewer(curPlayingSession.getKitchen());
        }
        this.kitchenViewer.drawElements(gui);
        if (getModel().isPaused()) { // if the playing session is paused, draw the escape menu on top
            gui.darken();
            this.escapeMenuViewer.drawElements(gui);
        }
    }
}
