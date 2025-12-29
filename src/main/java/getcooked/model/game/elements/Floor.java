package getcooked.model.game.elements;

import getcooked.viewer.game.ElementViewer;
import getcooked.viewer.game.FloorViewer;

public class Floor extends Interactable {
    public Floor(int x, int y) {
        super(x,y);
    }

    @Override
    public boolean isBlocking() {
        return false;
    }

    @Override
    public void interact(Chef chef){
        // Default implementation ignored
    }

    @Override
    public ElementViewer<Floor> getViewer() {
        return new FloorViewer();
    }
}
