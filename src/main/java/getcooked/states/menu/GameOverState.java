package getcooked.states.menu;

import getcooked.controller.Controller;
import getcooked.controller.menu.GameOverController;
import getcooked.model.menu.GameOverMenu;
import getcooked.model.menu.MainMenu;
import getcooked.states.State;
import getcooked.viewer.Viewer;
import getcooked.viewer.menu.GameOverViewer;

public class GameOverState extends State<GameOverMenu> {

    public GameOverState(GameOverMenu model) {
        super(model);
    }

    @Override
    protected Viewer<GameOverMenu> getViewer() {
        return new GameOverViewer(getModel());
    }

    @Override
    protected Controller<GameOverMenu> getController() {
        return new GameOverController(getModel());
    }
}
