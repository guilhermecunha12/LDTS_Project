package getcooked.sound;

import javax.sound.sampled.AudioSystem;
import java.util.Objects;

// Class that manages the all the audio of the game
public class AudioManager {
    private final BackgroundMusicPlayer backgroundMusicPlayer;
    private boolean backgroundMusicOn;
    // TODO: private final SoundEffectPlayer soundEffectPlayer;
    // TODO: make volume levels (by %)

    public AudioManager() throws Exception {
        this.backgroundMusicPlayer = new BackgroundMusicPlayer(new MusicLoader().loadSound(AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("sounds/music1.wav"))), AudioSystem.getClip()));
        this.backgroundMusicOn = true;
    }

    public void playBackgroundMusic() {
        backgroundMusicPlayer.start();
        backgroundMusicOn = true;
    }

    public void stopBackgroundMusic() {
        backgroundMusicPlayer.stop();
        backgroundMusicOn = false;
    }

    public boolean isBackgroundMusicOn() {
        return backgroundMusicOn;
    }
}
