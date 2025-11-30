package getcooked.controller.menu;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.menu.ControlsInfoMenu;
import getcooked.model.menu.CookMenu;
import getcooked.model.menu.MainMenu;
import getcooked.states.menu.ControlsInfoState;
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
                    if (getModel().isSelected2Player()) getModel().previousEntry();
                    break;
                case PLAYER2_RIGHT:
                    if (getModel().isSelected1Player()) getModel().nextEntry();
                    break;
                case PLAYER2_DOWN:
                    if (getModel().isSelected1Player()) getModel().previousEntry();
                    else if (getModel().isSelected2Player()) getModel().nextEntry();
                    break;
                case PLAYER2_UP:
                    if (getModel().isSelectedBack()) getModel().previousEntry();
                    break;
                case PLAYER2_SELECT:
                    // Add support for both one player and two player in a PlayingSessionState
                    if(getModel().isSelected1Player()) game.changeState(new ControlsInfoState(new ControlsInfoMenu(true)));
                    else if(getModel().isSelected2Player()) game.changeState(new ControlsInfoState(new ControlsInfoMenu(false)));
                    else if(getModel().isSelectedBack()) game.changeState(new MainMenuState(new MainMenu()));
                    break;
                default:
                    break;
            }
        }
    }
}
