package getcooked.model.game.elements;
import getcooked.model.Position;
import getcooked.viewer.game.ElementViewer;

import java.awt.*;

public abstract class Element {

    private Position position;

    protected Element(int x,int y) {
        this.position = new Position(x,y);
    }

    public Position getPosition() {
        return position;
    }

    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    // Factory
    public abstract ElementViewer getViewer();
}
