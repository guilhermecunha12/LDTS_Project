package getcooked.states.menu;

import getcooked.controller.Controller;
import getcooked.controller.menu.EscapeMenuController;
import getcooked.model.menu.EscapeMenu;
import getcooked.states.State;
import getcooked.viewer.Viewer;
import getcooked.viewer.menu.EscapeMenuViewer;

public class EscapeMenuState extends State<EscapeMenu> {

    public EscapeMenuState(EscapeMenu escapeMenu) {
        super(escapeMenu);
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
