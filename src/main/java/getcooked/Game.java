package getcooked;
import getcooked.gui.LanternaGUI;
import getcooked.model.menu.MainMenu;
import getcooked.sound.BackgroundMusicPlayer;
import getcooked.sound.MusicLoader;
import getcooked.states.State;
import getcooked.states.menu.MainMenuState;

import javax.sound.sampled.AudioSystem;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Game {
    private State state;
    private final LanternaGUI gui;
    private final BackgroundMusicPlayer backgroundMusicPlayer;

    public Game() throws Exception{
        this.state = new MainMenuState(new MainMenu());
        this.gui = new LanternaGUI(50,30);
        this.backgroundMusicPlayer = new BackgroundMusicPlayer(new MusicLoader().loadSound(AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("sounds/music.wav"))), AudioSystem.getClip()));
    }

    public static void main(String[] args) throws Exception {
        new Game().start();
    }

    public BackgroundMusicPlayer getBackgroundMusicPlayer() {
        return backgroundMusicPlayer;
    }

    public void changeState(State state) {
        this.state = state;
    }

    private void start() throws IOException { // fps missing / update frequency
        int FPS = 30;
        int frameTime = 1000 / FPS; // time in milisseconds to spend in one frame

        backgroundMusicPlayer.start();
        while (state != null) {
            long startTime = System.currentTimeMillis(); // needed for limiting chef's moving velocity

            state.step(this, this.gui, startTime); // state keeps a backreference

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime; // time remaining for the current frame

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        backgroundMusicPlayer.stop();
        gui.close();
    }
}
