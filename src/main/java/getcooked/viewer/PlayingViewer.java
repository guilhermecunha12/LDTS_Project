package getcooked.viewer;

import getcooked.gui.GUI;
import getcooked.model.PlayingSession;
import getcooked.model.menu.EscapeMenu;
import getcooked.viewer.game.KitchenViewer;
import getcooked.viewer.menu.EscapeMenuViewer;

// this viewer has a KitchenViewer and a EscapeMenuViewer
public class PlayingViewer extends Viewer<PlayingSession> {
    private final KitchenViewer kitchenViewer;
    private final EscapeMenuViewer escapeMenuViewer;

    public PlayingViewer(PlayingSession playingSession) {
        super(playingSession); // initialise model field via superclass
        // create two viewers initialising their models via their constructors (via superclass)
        this.kitchenViewer = new KitchenViewer(playingSession.getKitchen());
        this.escapeMenuViewer = new EscapeMenuViewer(playingSession.getEscapeMenu());
    }


    @Override
    public void drawElements(GUI gui) {
        this.kitchenViewer.drawElements(gui);
        if (getModel().isPaused()) { // if the playing session is paused, draw the escape menu on top
            gui.darken();
            this.escapeMenuViewer.drawElements(gui);
        }
    }
}
