package getcooked.sound;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AudioManagerTest {
    private BackgroundMusicPlayer backgroundMusicPlayer;
    private AudioManager audioManager;

    @BeforeEach
    void setUp() throws Exception {
        backgroundMusicPlayer = mock(BackgroundMusicPlayer.class);
        audioManager = new AudioManager();
    }

    //TODO: Dependecy injection to mock BackgroundMusicPlayer   nd
}
