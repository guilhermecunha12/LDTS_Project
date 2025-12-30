package getcooked.controller.menu;

import getcooked.Game;
import getcooked.model.menu.GameOverMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.mockito.Mockito.*;

class GameOverControllerTest {
    private Game game;
    private GameOverMenu gameOverMenu;
    private GameOverController gameOverController;

    @BeforeEach
    void setUp() {
        this.game = mock(Game.class);
        this.gameOverMenu = mock(GameOverMenu.class);
        this.gameOverController = new GameOverController(gameOverMenu);
    }

    @Test
    void testUp() throws Exception {
        gameOverController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_UP), 0);

        verify(gameOverMenu, times(1)).previousEntry();
    }

    @Test
    void testDown() throws Exception {
        gameOverController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_DOWN), 0);

        verify(gameOverMenu, times(1)).nextEntry();
    }

    @Test
    void testSelectBackToMainMenu() throws Exception {
        when(gameOverMenu.isSelectedBackToMainMenu()).thenReturn(true);
        when(gameOverMenu.isSelectedExitGame()).thenReturn(false);

        gameOverController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_SELECT), 0);

        verify(game, times(1)).changeState(any(getcooked.states.menu.MainMenuState.class));
    }

    @Test
    void testSelectExitGame() throws Exception {
        when(gameOverMenu.isSelectedBackToMainMenu()).thenReturn(false);
        when(gameOverMenu.isSelectedExitGame()).thenReturn(true);

        gameOverController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_SELECT), 0);

        verify(game, times(1)).changeState(null);
    }
}
