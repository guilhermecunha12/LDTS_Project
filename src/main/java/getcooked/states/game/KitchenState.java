package getcooked.states.game;

import getcooked.controller.Controller;
import getcooked.controller.game.KitchenController;
import getcooked.model.game.Kitchen;
import getcooked.states.State;
import getcooked.viewer.Viewer;
import getcooked.viewer.game.KitchenViewer;

public class KitchenState extends State<Kitchen> {

    public KitchenState(Kitchen kitchen) {
        super(kitchen);
    }

    @Override
    protected Viewer<Kitchen> getViewer() {
        return new KitchenViewer(getModel());
    }

    @Override
    protected Controller<Kitchen> getController() {
        return new KitchenController(getModel());
    }
}
