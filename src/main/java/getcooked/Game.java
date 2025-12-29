package getcooked;
import getcooked.gui.LanternaGUI;
import getcooked.model.menu.MainMenu;
import getcooked.sound.AudioManager;
import getcooked.states.State;
import getcooked.states.menu.MainMenuState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;

public class Game {
    private State<?> state;
    private final LanternaGUI gui;
    private final AudioManager audioManager;
    public static final int FPS = 15; // Frames per second

    public Game() throws IOException, URISyntaxException, FontFormatException,
            UnsupportedAudioFileException, LineUnavailableException {
        this.state = new MainMenuState(new MainMenu());
        this.gui = new LanternaGUI();
        this.audioManager = new AudioManager();
    }

    public static void main(String[] args) {
        try {
            new Game().start();
        } catch (IOException | URISyntaxException | FontFormatException | InterruptedException |
                 UnsupportedAudioFileException | LineUnavailableException e) {
            Logger.getLogger(Game.class.getName()).severe("Failed to start game: " + e.getMessage());
            Thread.currentThread().interrupt();
            System.exit(1);
        }
    }

    public void changeState(State<?> state) {
        this.state = state;
    }

    public AudioManager getAudioManager() {
        return this.audioManager;
    }

    private void start() throws IOException, URISyntaxException, FontFormatException, InterruptedException {

        audioManager.playBackgroundMusic();
        int frameTime = 1000 / FPS;
        while (state != null) {
            long startTime = System.currentTimeMillis(); // needed for limiting chef's moving velocity

            state.step(this, this.gui, startTime); // state keeps a backreference

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime; // time remaining for the current frame

            if (sleepTime > 0) {
                Thread.sleep(sleepTime);
            }
        }
        audioManager.stopBackgroundMusic();
        gui.close();
    }
}
