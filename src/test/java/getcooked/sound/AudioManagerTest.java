package getcooked.sound;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.FloatControl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AudioManagerTest {

    private BackgroundMusicPlayer backgroundMusicPlayer;
    private boolean backgroundMusicOn;
    private int currentPercentage;
    private FloatControl gain;


    @BeforeEach
    void setUp() {
        this.backgroundMusicPlayer = mock(BackgroundMusicPlayer.class);
        this.gain = mock(FloatControl.class);
    }

    @Test
    void testPlayBackgroundMusic() {
        AudioManager audioManager = new AudioManager(backgroundMusicPlayer, gain, backgroundMusicOn, currentPercentage);
        audioManager.playBackgroundMusic();
        verify(backgroundMusicPlayer, times(1)).start();
        assertTrue(audioManager.isBackgroundMusicOn());
    }

    @Test
    void testStopBackgroundMusic() {
        AudioManager audioManager = new AudioManager(backgroundMusicPlayer, gain, backgroundMusicOn, currentPercentage);
        audioManager.stopBackgroundMusic();
        verify(backgroundMusicPlayer, times(1)).stop();
        assertFalse(audioManager.isBackgroundMusicOn());
    }

    @Test
    void testSetBackgroundVolume() {
        AudioManager audioManager = new AudioManager(backgroundMusicPlayer, gain, backgroundMusicOn, currentPercentage);
        when(gain.getMinimum()).thenReturn(-80.0f);
        when(gain.getMaximum()).thenReturn(6.0f);

        audioManager.setBackgroundVolume(50);
        float expectedDb = -80.0f + (6.0f + 80.0f) * 0.5f;
        verify(gain, times(1)).setValue(expectedDb);
        assertEquals(50, audioManager.getCurrentPercentage());
    }

    @Test
    void testGetCurrentPercentage() {
        AudioManager audioManager = new AudioManager(backgroundMusicPlayer, gain, backgroundMusicOn, currentPercentage);
        audioManager.setBackgroundVolume(30);
        assertEquals(30, audioManager.getCurrentPercentage());
    }

    @Test
    void testIsBackgroundMusicOn() {
        AudioManager audioManager = new AudioManager(backgroundMusicPlayer, gain, backgroundMusicOn, currentPercentage);
        audioManager.playBackgroundMusic();
        assertTrue(audioManager.isBackgroundMusicOn());
    }

    @Test
    void testIsBackgroundMusicOff() {
        AudioManager audioManager = new AudioManager(backgroundMusicPlayer, gain, backgroundMusicOn, currentPercentage);
        audioManager.stopBackgroundMusic();
        assertFalse(audioManager.isBackgroundMusicOn());
    }
}