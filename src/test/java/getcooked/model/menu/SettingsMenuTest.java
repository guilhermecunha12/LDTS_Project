package getcooked.model.menu;

import getcooked.sound.AudioManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SettingsMenuTest {
    private SettingsMenu menu;
    private AudioManager audioManager;

    @BeforeEach
    void setUp() {
        audioManager = mock(AudioManager.class);
        when(audioManager.isBackgroundMusicOn()).thenReturn(true);
        menu = new SettingsMenu(audioManager);
    }

    @Test
    void testUpdateVolumeStateReflectsMusicStateWhenOn() {
        when(audioManager.isBackgroundMusicOn()).thenReturn(true);
        menu.updateVolumeState();

        assertEquals("Background Music ON", menu.getEntry(0));
    }

    @Test
    void testUpdateVolumeStateReflectsMusicStateWhenOff() {
        when(audioManager.isBackgroundMusicOn()).thenReturn(false);
        menu.updateVolumeState();

        assertEquals("Background Music OFF", menu.getEntry(0));
    }
}
