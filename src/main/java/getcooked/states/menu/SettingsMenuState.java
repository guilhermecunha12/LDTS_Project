package getcooked.states.menu;

import getcooked.controller.Controller;
import getcooked.model.menu.SettingsMenu;
import getcooked.states.State;
import getcooked.viewer.Viewer;

public class SettingsMenuState extends State<SettingsMenu> {
    public SettingsMenuState(SettingsMenu model){
        super(model);
    }

    @Override
    protected Viewer<SettingsMenu> getViewer() {
        return new SettingsMenuViewer(getModel());
    }

    @Override
    protected Controller<SettingsMenu> getController() {
        return new SettingsMenuController(getModel());
    }
}
