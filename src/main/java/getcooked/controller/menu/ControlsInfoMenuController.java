package getcooked.controller.menu;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.PlayingSession;
import getcooked.model.game.kitchen.LoaderKitchenBuilder;
import getcooked.model.menu.ControlsInfoMenu;
import getcooked.model.menu.EscapeMenu;
import getcooked.states.PlayingState;

import java.io.IOException;
import java.util.Set;

public class ControlsInfoMenuController extends Controller<ControlsInfoMenu> {

    private static final int FRAMES_TO_WAIT = 150;
    private int framesWaited;

    public ControlsInfoMenuController(ControlsInfoMenu controlsInfoMenu) {
        super(controlsInfoMenu);
        this.framesWaited = 0;
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        if(framesWaited == FRAMES_TO_WAIT) {
            game.changeState(new PlayingState(new PlayingSession(new LoaderKitchenBuilder(1).createKitchen(), new EscapeMenu())));
            framesWaited = 0;
        }
        framesWaited++;
    }
}
