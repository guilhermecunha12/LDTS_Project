package getcooked.states;

import getcooked.controller.Controller;
import getcooked.viewer.Viewer;

public class MainMenuState extends State<MainMenu>{
    public MainMenuState(MainMenu model) {
        super(model);
    }

    @Override
    protected Viewer<MainMenu> getViewer() {
        return new MainMenuViewer(getModel());
    }

    @Override
    protected Controller<MainMenu> getController() {
        return new MainMenuController(getModel());
    }
}
