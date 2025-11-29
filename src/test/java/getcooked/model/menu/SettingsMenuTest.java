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
    void testUpdateEntriesReflectsMusicStateWhenOn() {
        when(audioManager.isBackgroundMusicOn()).thenReturn(true);
        menu.updateEntries();

        assertEquals("Background Music ON", menu.getEntry(0));
    }

    @Test
    void testUpdateEntriesReflectsMusicStateWhenOff() {
        when(audioManager.isBackgroundMusicOn()).thenReturn(false);
        menu.updateEntries();

        assertEquals("Background Music OFF", menu.getEntry(0));
    }
}
