package getcooked.controller.menu;

import getcooked.Game;
import getcooked.model.menu.EscapeMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.mockito.Mockito.*;

public class EscapeMenuControllerTest {
    private Game game;
    private EscapeMenu escapeMenu;
    private EscapeMenuController escapeMenuController;

    @BeforeEach
    void setUp() {
        this.game = mock(Game.class);
        this.escapeMenu = mock(EscapeMenu.class);
        this.escapeMenuController = new EscapeMenuController(escapeMenu);
    }

    @Test
    void testPLayerUp(){
        escapeMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_UP),0);
        verify(escapeMenu,times(1)).previousEntry();
    }

    @Test
    void testPLayerDown(){
        escapeMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_DOWN),0);
        verify(escapeMenu,times(1)).nextEntry();
    }
}
