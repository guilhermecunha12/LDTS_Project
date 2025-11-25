package getcooked;
import getcooked.gui.LanternaGUI;
import getcooked.states.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private State state;
    private final LanternaGUI gui;

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.state = new MainMenuState();
        this.gui = new LanternaGUI(20, 20);
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        new Game().start();
    }

    public void changeState(State state) {
        this.state = state;
    }

    private void start() { // fps missing / update frequency
        int FPS = 20;
        int frameTime = 1000 / FPS; // time in milisseconds to spend in one frame

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
    }
}
