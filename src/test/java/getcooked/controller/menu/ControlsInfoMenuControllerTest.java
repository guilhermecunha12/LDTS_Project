package getcooked.controller.menu;

import getcooked.Game;
import getcooked.model.menu.ControlsInfoMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class ControlsInfoMenuControllerTest {
    private Game game;
    private ControlsInfoMenuController controlsInfoMenuController;
    private ControlsInfoMenu controlsInfoMenu;

    @BeforeEach
    void setUp() {
        this.game = mock(Game.class);
        this.controlsInfoMenu = mock(ControlsInfoMenu.class);
        this.controlsInfoMenuController = new ControlsInfoMenuController(controlsInfoMenu);
    }

    @Test
    void testStepFor1PlayerWaitedFramesForTransition() throws IOException {
        when(controlsInfoMenu.isOnePlayer()).thenReturn(true);

        for (int i = 0; i <= 75; i++) {
            controlsInfoMenuController.step(game, null, 0);
        }

        verify(game, times(1)).changeState(any());
    }

    @Test
    void testStepFor2PlayersWaitedFramesForTransition() throws IOException {
        when(controlsInfoMenu.isOnePlayer()).thenReturn(false);

        for (int i = 0; i <= 75; i++) {
            controlsInfoMenuController.step(game, null, 0);
        }

        verify(game, times(1)).changeState(any());
    }
}
