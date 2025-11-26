package getcooked.controller.menu;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.menu.MainMenu;
import getcooked.model.menu.SettingsMenu;
import getcooked.states.menu.MainMenuState;

import java.io.IOException;
import java.util.Set;

public class SettingsMenuController extends Controller<SettingsMenu> {

    public SettingsMenuController(SettingsMenu settingsMenu) {
        super(settingsMenu);
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        for(GUI.ACTION action : actions) {
            switch (action) {
                case PLAYER2_UP:
                    getModel().previousEntry();
                    break;
                case PLAYER2_DOWN:
                    getModel().nextEntry();
                    break;
                case PLAYER2_SELECT:
                    if(getModel().isSelectedVolume()) {
                        // Toggles the menu
                        getModel().toggleVolume();
                        // TODO: Changes the music inside game
                    }
                    if (getModel().isSelectedBack()) game.changeState(new MainMenuState(new MainMenu()));
            }
        }
    }
}
