package getcooked.controller.menu;

import getcooked.Game;
import getcooked.model.PlayingSession;
import getcooked.model.menu.EscapeMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.mockito.Mockito.*;

public class EscapeMenuControllerTest {
    private Game game;
    private EscapeMenu escapeMenu;
    private PlayingSession playingSession;
    private EscapeMenuController escapeMenuController;

    @BeforeEach
    void setUp() {
        this.game = mock(Game.class);
        this.escapeMenu = mock(EscapeMenu.class);
        this.playingSession = mock(PlayingSession.class);
        this.escapeMenuController = new EscapeMenuController(escapeMenu, playingSession);
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
    @Test
    void testEscape(){
        escapeMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.ESCAPE),0);
        verify(playingSession,times(1)).togglePause();
    }

    @Test
    void testSelectResume(){
        when(escapeMenu.isSelectedResume()).thenReturn(true);
        when(escapeMenu.isSelectedExitToMainMenu()).thenReturn(false);
        escapeMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_SELECT),0);
        verify(playingSession,times(1)).togglePause();
    }

    @Test
    void testSelectExitToMainMenu(){
        when(escapeMenu.isSelectedResume()).thenReturn(false);
        when(escapeMenu.isSelectedExitToMainMenu()).thenReturn(true);
        escapeMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_SELECT),0);
        verify(game,times(1)).changeState(any(getcooked.states.menu.MainMenuState.class));
    }
}
