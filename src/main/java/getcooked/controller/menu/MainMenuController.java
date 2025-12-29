package getcooked.controller.menu;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.menu.CookMenu;
import getcooked.model.menu.MainMenu;
import getcooked.model.menu.SettingsMenu;
import getcooked.states.menu.CookMenuState;
import getcooked.states.menu.SettingsMenuState;

import java.io.IOException;
import java.util.Set;

public class MainMenuController extends Controller<MainMenu> {

    public MainMenuController(MainMenu mainMenu){
        super(mainMenu);
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        for(GUI.ACTION action : actions){
            switch (action){
                // Considering that we want to use arrows to move in the menu
                case PLAYER2_UP: getModel().previousEntry(); break;
                case PLAYER2_DOWN: getModel().nextEntry(); break;
                case PLAYER2_SELECT: selectEntry(game); break;
                default: break;
            }
        }
    }

    private void selectEntry(Game game) {
        if(getModel().isSelectedCook()) game.changeState(new CookMenuState(new CookMenu()));
        if(getModel().isSelectedSettings()) game.changeState(
                new SettingsMenuState(new SettingsMenu(game.getAudioManager())));
        if(getModel().isSelectedExit()) game.changeState(null);
    }
}
