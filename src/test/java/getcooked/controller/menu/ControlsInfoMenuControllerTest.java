package getcooked.controller.menu;

import getcooked.Game;
import getcooked.model.menu.ControlsInfoMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.mockito.Mockito.*;

public class ControlsInfoMenuControllerTest {
    private Game game;
    private ControlsInfoMenu controlsInfoMenu;
    private ControlsInfoMenuController controlsInfoMenuController;

    @BeforeEach
    void setUp() {
        this.game = mock(Game.class);
        this.controlsInfoMenu = mock(ControlsInfoMenu.class);
        this.controlsInfoMenuController = new ControlsInfoMenuController(controlsInfoMenu);
    }

    @Test
    void testStep_AfterFramesWaitedOnePLayer() throws Exception {
        when(controlsInfoMenu.isOnePlayer()).thenReturn(true);

        // Simulate the passage of frames
        for (int i = 0; i <= 150; i++) {
            controlsInfoMenuController.step(game, Set.of(), 0);
        }

        verify(game, times(1)).changeState(any(getcooked.states.PlayingState.class));
    }

    @Test
    void testStep_AfterFramesWaitedTwoPlayers() throws Exception {
        when(controlsInfoMenu.isOnePlayer()).thenReturn(false);

        // Simulate the passage of frames
        for (int i = 0; i <= 150; i++) {
            controlsInfoMenuController.step(game, Set.of(), 0);
        }

        verify(game, times(1)).changeState(any(getcooked.states.PlayingState.class));
    }
}
