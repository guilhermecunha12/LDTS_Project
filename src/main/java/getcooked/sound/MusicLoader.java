package getcooked.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

public class MusicLoader {
    public Clip loadSound(AudioInputStream audioInput, Clip musicClip) throws LineUnavailableException, IOException {
        musicClip.open(audioInput);
        return musicClip;
    }
}
