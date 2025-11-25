package getcooked.states.menu;

import getcooked.controller.Controller;
import getcooked.states.State;
import getcooked.viewer.Viewer;

public class CookMenuState extends State<CookMenu> {
    public CookMenuState(CookMenu model) {
        super(model);
    }

    @Override
    protected Viewer<CookMenu> getViewer() {
        return new CookMenuViewer(getModel());
    }

    @Override
    protected Controller<CookMenu> getController() {
        return new CookMenuController(getModel());
    }
}
