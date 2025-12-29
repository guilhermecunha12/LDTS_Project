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

    @Test
    void testUpdateVolumePercentageReflectsCurrentPercentage() {
        when(audioManager.getCurrentPercentage()).thenReturn(75);
        menu.updateVolumePercentage();

        assertEquals("Background Music Volume < 75 >", menu.getEntry(1));
    }

    @Test
    void testIsSelectedMethods() {
        menu.currentEntry = 0;
        assertTrue(menu.isSelectedVolume());
        assertFalse(menu.isSelectedBackgroundVolume());
        assertFalse(menu.isSelectedBack());

        menu.currentEntry = 1;
        assertFalse(menu.isSelectedVolume());
        assertTrue(menu.isSelectedBackgroundVolume());
        assertFalse(menu.isSelectedBack());

        menu.currentEntry = 2;
        assertFalse(menu.isSelectedVolume());
        assertFalse(menu.isSelectedBackgroundVolume());
        assertTrue(menu.isSelectedBack());
    }
}
