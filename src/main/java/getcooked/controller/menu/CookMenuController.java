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
                case PLAYER2_LEFT: getModel().previousEntry(); break;
                case PLAYER2_RIGHT: getModel().nextEntry(); break;
                case PLAYER2_DOWN: getDownEntry(); break;
                case PLAYER2_UP: getUpEntry(); break;
                case PLAYER2_SELECT: selectEntry(game); break;
                default: break;
            }
        }
    }

    private void getDownEntry() {
        if (getModel().isSelected1Player()) getModel().previousEntry();
        else if (getModel().isSelected2Player()) getModel().nextEntry();
    }

    private void getUpEntry() {
        if (getModel().isSelectedBack()) getModel().previousEntry();
    }

    private void selectEntry(Game game) {
        if (getModel().isSelected1Player()) game.changeState(new ControlsInfoState(new ControlsInfoMenu(true)));
        else if(getModel().isSelected2Player()) game.changeState(new ControlsInfoState(new ControlsInfoMenu(false)));
        else if(getModel().isSelectedBack()) game.changeState(new MainMenuState(new MainMenu()));

    }
}
