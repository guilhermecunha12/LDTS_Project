package getcooked.states;
import getcooked.Game;
import getcooked.controller.Controller;
import getcooked.viewer.Viewer;

public abstract class State<T> {
    private T model;
    private Viewer<T> viewer;
    private Controller<T> controller;

    public State(T model) {
        this.model = model;
        this.viewer = this.getViewer();
        this.controller = this.getController();
    }

    protected abstract Viewer<T> getViewer();
    protected abstract Controller<T> getController();

    // public T getModel();

    public void step(Game game, GUI gui, long time) {
        GUI.ACTION action = gui.getNextAction();
        this.controller.step(game, action, time);
        this.viewer.draw(gui);
    }
}
