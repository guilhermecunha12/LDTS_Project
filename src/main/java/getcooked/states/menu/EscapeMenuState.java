package getcooked.states.menu;

import getcooked.controller.Controller;
import getcooked.model.menu.EscapeMenu;
import getcooked.states.State;
import getcooked.viewer.Viewer;

public class EscapeMenuState extends State<EscapeMenu> {
    public EscapeMenuState(EscapeMenu model) {
        super(model);
    }

    @Override
    protected Viewer<EscapeMenu> getViewer() {
        return new EscapeMenuViewer(getModel());
    }

    @Override
    protected Controller<EscapeMenu> getController() {
        return new EscapeMenuController(getModel());
    }
}
