package getcooked.model.game.elements;

import getcooked.gui.GUI;
import getcooked.model.Position;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

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

    public abstract void draw(GUI gui) throws IOException, URISyntaxException, FontFormatException;
}
