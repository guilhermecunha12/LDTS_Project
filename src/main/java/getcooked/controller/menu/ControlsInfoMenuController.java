package getcooked.controller.menu;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.game.LoaderKitchenBuilder;
import getcooked.model.menu.ControlsInfoMenu;
import getcooked.states.game.KitchenState;

import java.io.IOException;
import java.util.Set;

import static getcooked.Game.FPS;

public class ControlsInfoMenuController extends Controller<ControlsInfoMenu> {

    private static final int FRAMES_TO_WAIT = 5 * FPS; // number of frames to wait
    private int framesWaited;

    public ControlsInfoMenuController(ControlsInfoMenu controlsInfoMenu) {
        super(controlsInfoMenu);
        this.framesWaited = 0;
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        if(framesWaited == FRAMES_TO_WAIT) {
            if (getModel().isOnePlayer())
                game.changeState(new KitchenState(new LoaderKitchenBuilder(1, 1, 3, 0).createKitchen()));
            else
                game.changeState(new KitchenState(new LoaderKitchenBuilder(1, 2, 3, 0).createKitchen()));
            framesWaited = 0;
        }
        framesWaited++;
    }
}
