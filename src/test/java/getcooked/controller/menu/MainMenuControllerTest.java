package getcooked.controller.menu;

import getcooked.Game;
import getcooked.model.menu.MainMenu;
import getcooked.sound.AudioManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.mockito.Mockito.*;

public class MainMenuControllerTest {
    private Game game;
    private MainMenu mainMenu;
    private MainMenuController mainMenuController;

    @BeforeEach
    void setUp() {
        this.game = mock(Game.class);
        this.mainMenu = mock(MainMenu.class);

        AudioManager audioManager = mock(AudioManager.class);
        when(game.getAudioManager()).thenReturn(audioManager);
        when(audioManager.isBackgroundMusicOn()).thenReturn(true);
        this.mainMenuController = new MainMenuController(mainMenu);
    }

    @Test
    void testStep_Player2UpAction() throws Exception {
        mainMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_UP), 0);
        verify(mainMenu, times(1)).previousEntry();
    }

    @Test
    void testStep_Player2DownAction() throws Exception {
        mainMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_DOWN), 0);
        verify(mainMenu, times(1)).nextEntry();
    }

    @Test
    void testStep_Player2SelectCookAction() throws Exception {
        when(mainMenu.isSelectedCook()).thenReturn(true);
        when(mainMenu.isSelectedSettings()).thenReturn(false);
        when(mainMenu.isSelectedExit()).thenReturn(false);

        mainMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_SELECT), 0);
        verify(game, times(1)).changeState(any(getcooked.states.menu.CookMenuState.class));
    }

    @Test
    void testStep_Player2SelectSettingsAction() throws Exception {
        when(mainMenu.isSelectedCook()).thenReturn(false);
        when(mainMenu.isSelectedSettings()).thenReturn(true);
        when(mainMenu.isSelectedExit()).thenReturn(false);

        mainMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_SELECT), 0);
        verify(game, times(1)).changeState(any(getcooked.states.menu.SettingsMenuState.class));
    }

    @Test
    void testStep_Player2SelectExitAction() throws Exception {
        when(mainMenu.isSelectedCook()).thenReturn(false);
        when(mainMenu.isSelectedSettings()).thenReturn(false);
        when(mainMenu.isSelectedExit()).thenReturn(true);

        mainMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_SELECT), 0);
        verify(game, times(1)).changeState(null);
    }
}
