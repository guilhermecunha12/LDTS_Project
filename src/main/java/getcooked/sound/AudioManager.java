package getcooked.sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Objects;

// Class that manages the all the audio of the game
public class AudioManager {
    private final BackgroundMusicPlayer backgroundMusicPlayer;
    private boolean backgroundMusicOn;
    private int currentPercentage;
    private final FloatControl gain;

    public AudioManager() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        this.backgroundMusicPlayer = new BackgroundMusicPlayer(
                new MusicLoader().loadSound(AudioSystem.getAudioInputStream
                (Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                        .getResource("sounds/music.wav"))),
                AudioSystem.getClip()));
        this.backgroundMusicOn = true;
        // Initialize gain control (costly operation if done everytime inside setBackgroundVolume
        this.gain = (FloatControl) backgroundMusicPlayer.getSound().getControl(FloatControl.Type.MASTER_GAIN);
        // Set default volume
        setBackgroundVolume(70);
    }

    public AudioManager(BackgroundMusicPlayer backgroundMusicPlayer, FloatControl gain,
                        boolean backgroundMusicOn, int currentPercentage) {
        this.backgroundMusicPlayer = backgroundMusicPlayer;
        this.gain = gain;
        this.backgroundMusicOn = backgroundMusicOn;
        this.currentPercentage = currentPercentage;

    }

    public void playBackgroundMusic() {
        backgroundMusicPlayer.start();
        backgroundMusicOn = true;
    }

    public void stopBackgroundMusic() {
        backgroundMusicPlayer.stop();
        backgroundMusicOn = false;
    }

    public void setBackgroundVolume(int percentage) {
        float min = gain.getMinimum();
        float max = gain.getMaximum();
        float dB = min + (max - min) * ((float)percentage / 100);
        gain.setValue(dB);
        currentPercentage = percentage;
    }

    public int getCurrentPercentage() {
        return currentPercentage;
    }

    public boolean isBackgroundMusicOn() {
        return backgroundMusicOn;
    }
}
