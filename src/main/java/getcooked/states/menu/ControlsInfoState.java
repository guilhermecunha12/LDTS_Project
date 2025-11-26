package getcooked.states.menu;

import getcooked.controller.Controller;
import getcooked.controller.menu.ControlsInfoMenuController;
import getcooked.model.menu.ControlsInfoMenu;
import getcooked.states.State;
import getcooked.viewer.Viewer;
import getcooked.viewer.menu.ControlsInfoMenuViewer;


public class ControlsInfoState extends State<ControlsInfoMenu> {
    public ControlsInfoState(ControlsInfoMenu model) {
        super(model);
    }

    @Override
    protected Viewer<ControlsInfoMenu> getViewer() {
        return new ControlsInfoMenuViewer(getModel());
    }

    @Override
    protected Controller<ControlsInfoMenu> getController() {
        return new ControlsInfoMenuController(getModel());
    }
}
