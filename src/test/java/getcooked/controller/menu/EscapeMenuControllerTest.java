package getcooked.controller.menu;

import getcooked.Game;
import getcooked.gui.GUI;
import getcooked.model.menu.EscapeMenu;
import getcooked.states.menu.MainMenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.mockito.Mockito.*;

class EscapeMenuControllerTest {
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
    void testUp(){
        escapeMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_UP),0);

        verify(escapeMenu,times(1)).previousEntry();
    }

    @Test
    void testDown(){
        escapeMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_DOWN),0);

        verify(escapeMenu,times(1)).nextEntry();
    }

    @Test
    void testSelectExitToMainMenu() {
        when(escapeMenu.isSelectedResume()).thenReturn(false);
        when(escapeMenu.isSelectedExitToMainMenu()).thenReturn(true);

        escapeMenuController.step(game, Set.of(GUI.ACTION.PLAYER2_SELECT), 0);

        verify(game, times(1)).changeState(any(MainMenuState.class));
    }

    @Test
    void testSelectResume() {
        when(escapeMenu.isSelectedResume()).thenReturn(true);

        escapeMenuController.step(game, Set.of(GUI.ACTION.PLAYER2_SELECT), 0);

        verify(game, times(1)).changeState(any());
    }
}