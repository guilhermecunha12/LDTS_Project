package getcooked.sound;

import javax.sound.sampled.Clip;

public class BackgroundMusicPlayer implements MusicPlayer{
    private Clip sound;

    public BackgroundMusicPlayer(Clip sound){
        this.sound = sound;
    }

    @Override
    public void start(){
        sound.start();
        sound.loop(Clip.LOOP_CONTINUOUSLY);
    }
    @Override
    public void stop(){
        sound.stop();
    }
    @Override
    public Clip getSound(){
        return sound;
    }
    @Override
    public void setSound(Clip sound){
        this.sound = sound;
    }
}
