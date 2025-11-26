package getcooked.controller.menu;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.menu.CookMenu;
import getcooked.model.menu.MainMenu;
import getcooked.states.menu.MainMenuState;

import java.io.IOException;
import java.util.Set;

public class CookMenuController extends Controller<CookMenu> {

    public CookMenuController(CookMenu cookMenu) {
        super(cookMenu);
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        for(GUI.ACTION action : actions) {
            switch (action) {
                // Considering arrow movement to select options from the menu
                case PLAYER2_LEFT:
                    getModel().previousEntry();
                    break;
                case PLAYER2_RIGHT:
                    getModel().nextEntry();
                    break;
                case PLAYER2_SELECT:
                    // Add support for both one player and two player in a PlayingState
                    // if(getModel().isSelected1Player())
                    // if(getModel().isSelected2Player())
                    if(getModel().isSelectedBack()) game.changeState(new MainMenuState(new MainMenu()));
            }
        }
    }
}
