package getcooked.controller.menu;

import getcooked.Game;
import getcooked.model.menu.SettingsMenu;
import getcooked.sound.AudioManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.mockito.Mockito.*;

class SettingsMenuControllerTest {
    private Game game;
    private SettingsMenu settingsMenu;
    private SettingsMenuController settingsMenuController;

    @BeforeEach
    void setup(){
        this.game = mock(Game.class);
        this.settingsMenu = mock(SettingsMenu.class);
        this.settingsMenuController = new SettingsMenuController(settingsMenu);
    }

    @Test
    void testUp() throws Exception {
        settingsMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_UP), 0);

        verify(settingsMenu, times(1)).previousEntry();
    }

    @Test
    void testDown() throws Exception {
        settingsMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_DOWN), 0);

        verify(settingsMenu, times(1)).nextEntry();
    }

    @Test
    void testSelectBack() throws IOException {
        when(settingsMenu.isSelectedVolume()).thenReturn(false);
        when(settingsMenu.isSelectedBack()).thenReturn(true);

        settingsMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_SELECT), 0);

        verify(game, times(1)).changeState(any(getcooked.states.menu.MainMenuState.class));
    }

    @Test
    void testStopVolume() throws IOException {
        when(settingsMenu.isSelectedBack()).thenReturn(false);
        when(settingsMenu.isSelectedVolume()).thenReturn(true);

        AudioManager audioManager = mock(AudioManager.class);
        when(game.getAudioManager()).thenReturn(audioManager);
        when(audioManager.isBackgroundMusicOn()).thenReturn(true);

        settingsMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_SELECT), 0);

        verify(audioManager, times(1)).stopBackgroundMusic();
    }

    @Test
    void testPlayVolume() throws IOException {
        when(settingsMenu.isSelectedBack()).thenReturn(false);
        when(settingsMenu.isSelectedVolume()).thenReturn(true);

        AudioManager audioManager = mock(AudioManager.class);
        when(game.getAudioManager()).thenReturn(audioManager);
        when(audioManager.isBackgroundMusicOn()).thenReturn(false);

        settingsMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_SELECT), 0);

        verify(audioManager, times(1)).playBackgroundMusic();
    }

    @Test
    void testVolumeIncrease() throws IOException {
        when(settingsMenu.isSelectedBackgroundVolume()).thenReturn(true);

        AudioManager audioManager = mock(AudioManager.class);
        when(game.getAudioManager()).thenReturn(audioManager);
        when(audioManager.getCurrentPercentage()).thenReturn(50);

        settingsMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_RIGHT), 0);

        verify(audioManager, times(1)).setBackgroundVolume(55);
        verify(settingsMenu, times(1)).updateVolumePercentage();
    }

    @Test
    void testVolumeDecrease() throws IOException {
        when(settingsMenu.isSelectedBackgroundVolume()).thenReturn(true);

        AudioManager audioManager = mock(AudioManager.class);
        when(game.getAudioManager()).thenReturn(audioManager);
        when(audioManager.getCurrentPercentage()).thenReturn(50);

        settingsMenuController.step(game, Set.of(getcooked.gui.GUI.ACTION.PLAYER2_LEFT), 0);

        verify(audioManager, times(1)).setBackgroundVolume(45);
        verify(settingsMenu, times(1)).updateVolumePercentage();
    }
}
