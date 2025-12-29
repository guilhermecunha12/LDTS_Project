package getcooked.model.game.elements;

import getcooked.viewer.game.ElementViewer;
import getcooked.viewer.game.FloorViewer;

public class Floor extends Tile {
    public Floor(int x, int y) {
        super(x,y);
    }

    @Override
    public boolean isBlocking() {
        return false;
    }

    @Override
    public ElementViewer<Floor> getViewer() {
        return new FloorViewer();
    }
}
