package getcooked.sound;

import javax.sound.sampled.Clip;

public interface MusicPlayer {

    void start();
    void stop();

    Clip getSound();
    void setSound(Clip sound);

}
