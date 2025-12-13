package getcooked;
import getcooked.gui.LanternaGUI;
import getcooked.model.menu.MainMenu;
import getcooked.sound.AudioManager;
import getcooked.states.State;
import getcooked.states.menu.MainMenuState;

import java.io.IOException;

public class Game {
    private State state;
    private final LanternaGUI gui;
    private final AudioManager audioManager;
    public static final int FPS = 30; // Frames per second

    public Game() throws Exception{
        this.state = new MainMenuState(new MainMenu());
        this.gui = new LanternaGUI();
        this.audioManager = new AudioManager();
    }

    public static void main(String[] args) throws Exception {
        new Game().start();
    }

    public void changeState(State state) {
        this.state = state;
    }

    public AudioManager getAudioManager() {
        return this.audioManager;
    }

    private void start() throws Exception { // fps missing / update frequency

        audioManager.playBackgroundMusic();
        int frameTime = 1000 / FPS;
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
        audioManager.stopBackgroundMusic();
        gui.close();
    }
}
