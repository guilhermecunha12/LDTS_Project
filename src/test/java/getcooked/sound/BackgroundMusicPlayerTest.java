package getcooked.sound;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class BackgroundMusicPlayerTest {
    @Test
    void testMusicLoader() throws Exception {
        AudioInputStream audioInput = mock(AudioInputStream.class);
        Clip musicClip = mock(Clip.class);

        Clip sound = new MusicLoader().loadSound(audioInput, musicClip);
        Mockito.verify(musicClip, Mockito.times(1)).open(audioInput);

        BackgroundMusicPlayer backgroundSoundPlayer = new BackgroundMusicPlayer(sound);
        assertEquals(sound, backgroundSoundPlayer.getSound());
    }

    @Test
    public void musicTesting() {
        Clip sound= mock(Clip.class);
        BackgroundMusicPlayer backgroundSoundPlayer = new BackgroundMusicPlayer(sound);
        backgroundSoundPlayer.setSound(sound);
        backgroundSoundPlayer.start();
        Mockito.verify(sound, times(1)).start();
        Mockito.verify(sound, times(1)).loop(Clip.LOOP_CONTINUOUSLY);

        backgroundSoundPlayer.stop();
        Mockito.verify(sound, times(1)).stop();
    }
}
