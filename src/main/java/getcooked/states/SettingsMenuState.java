package getcooked.states;

import getcooked.controller.Controller;
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
