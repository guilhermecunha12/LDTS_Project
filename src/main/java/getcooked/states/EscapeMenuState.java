package getcooked.states;

import getcooked.controller.Controller;
import getcooked.viewer.Viewer;

public class EscapeMenuState extends State<EscapeMenu>{
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
