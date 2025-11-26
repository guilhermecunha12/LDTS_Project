package getcooked.states;

import getcooked.controller.Controller;
import getcooked.model.PlayingSession;
import getcooked.viewer.Viewer;

public class PlayingState extends State<PlayingSession> {
    public PlayingState(PlayingSession playingSession) {
        super(playingSession);
    }

    @Override
    protected Viewer<PlayingSession> getViewer() {
        return new PlayingViewer(getModel());
    }

    @Override
    protected Controller<PlayingSession> getController() {
        return new PlayingController(getModel());
    }
}
