package getcooked.controller.menu;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.PlayingSession;
import getcooked.model.menu.EscapeMenu;
import getcooked.model.menu.MainMenu;
import getcooked.states.menu.MainMenuState;

import java.io.IOException;
import java.util.Set;

public class EscapeMenuController extends Controller<EscapeMenu> {
    private final PlayingSession playingSession; // hold a reference to the playingSession to change its paused state

    public EscapeMenuController(EscapeMenu escapeMenu, PlayingSession playingSession) {
        super(escapeMenu);
        this.playingSession = playingSession;
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) {
        for(GUI.ACTION action : actions) {
            switch (action) {
                case PLAYER2_UP:
                    getModel().previousEntry();
                    break;
                case PLAYER2_DOWN:
                    getModel().nextEntry();
                    break;
                case PLAYER2_SELECT:
                    if (getModel().isSelectedResume()) this.playingSession.togglePause();
                    else if (getModel().isSelectedExitToMainMenu()) game.changeState(new MainMenuState(new MainMenu()));
                    break;
                case ESCAPE:
                    this.playingSession.togglePause();
            }
        }
    }
}