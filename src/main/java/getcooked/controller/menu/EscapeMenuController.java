package getcooked.controller.menu;

import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.gui.GUI;
import getcooked.model.PlayingSession;
import getcooked.model.menu.EscapeMenu;

import java.io.IOException;
import java.util.Set;

public class EscapeMenuController extends Controller<EscapeMenu> {
    public EscapeMenuController(EscapeMenu escapeMenu) {
        super(escapeMenu);
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actions, long time) throws IOException {
        return;
    }

    public void step(Game game, PlayingSession playingSession, Set<GUI.ACTION> actions, long time) {
        for(GUI.ACTION action : actions) {
            switch (action) {
                //...
            }
        }
    }
}
