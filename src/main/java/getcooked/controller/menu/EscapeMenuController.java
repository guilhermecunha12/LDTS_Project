package getcooked.controller.menu;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.game.KitchenModelStorage;
import getcooked.model.menu.EscapeMenu;
import getcooked.model.menu.MainMenu;
import getcooked.states.game.KitchenState;
import getcooked.states.menu.MainMenuState;

import java.util.Set;

public class EscapeMenuController extends Controller<EscapeMenu> {

    public EscapeMenuController(EscapeMenu escapeMenu) {
        super(escapeMenu);
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
                    if (getModel().isSelectedResume()) game.changeState(new KitchenState(KitchenModelStorage.getStoredKitchen()));
                    else if (getModel().isSelectedExitToMainMenu()) game.changeState(new MainMenuState(new MainMenu()));
                    break;
                case ESCAPE:
                    game.changeState(new KitchenState(KitchenModelStorage.getStoredKitchen()));
                    break;
                default:
                    break;
            }
        }
    }
}