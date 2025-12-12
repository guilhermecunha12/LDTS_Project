package getcooked.sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import java.util.Objects;

// Class that manages the all the audio of the game
public class AudioManager {
    private final BackgroundMusicPlayer backgroundMusicPlayer;
    private boolean backgroundMusicOn;
    private int currentPercentage;
    // TODO: private final SoundEffectPlayer soundEffectPlayer;
    // TODO: make volume levels (by %)

    public AudioManager() throws Exception {
        this.backgroundMusicPlayer = new BackgroundMusicPlayer(new MusicLoader().loadSound(AudioSystem.getAudioInputStream
                (Objects.requireNonNull(getClass().getClassLoader().getResource("sounds/music.wav"))),
                AudioSystem.getClip()));
        this.backgroundMusicOn = true;
        setBackgroundVolume(100);
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
        Clip soundClip = backgroundMusicPlayer.getSound();
        FloatControl gain  = (FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
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
