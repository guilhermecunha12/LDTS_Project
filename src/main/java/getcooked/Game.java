package getcooked;
import getcooked.gui.LanternaGUI;
import getcooked.states.State;

public class Game {
    private State state;
    private final LanternaGUI gui;

    public Game() {
        this.state = new MainMenuState();
        this.gui = new LanternaGUI();
    }

    public static void main(String[] args) {
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
