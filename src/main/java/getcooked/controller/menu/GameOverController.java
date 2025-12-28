package getcooked.controller.menu;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.menu.GameOverMenu;
import getcooked.model.menu.MainMenu;
import getcooked.states.menu.MainMenuState;

import java.io.IOException;
import java.util.Set;

public class GameOverController extends Controller<GameOverMenu> {

    public GameOverController(GameOverMenu gameOverMenu) {
        super(gameOverMenu);
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        for (GUI.ACTION action : actions) {
            switch (action) {
                case PLAYER2_DOWN: getModel().nextEntry(); break;
                case PLAYER2_UP: getModel().previousEntry(); break;
                case PLAYER2_SELECT: selectEntry(game); break;
                default: break;
            }
        }
    }

    private void selectEntry(Game game) {
        if (getModel().isSelectedBackToMainMenu()) game.changeState(new MainMenuState(new MainMenu()));
        if (getModel().isSelectedExitGame()) game.changeState(null);
    }
}
