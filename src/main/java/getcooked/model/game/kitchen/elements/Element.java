package getcooked.model.game.kitchen.elements;

import getcooked.model.Position;

public abstract class Element {

    private Position position;

    public Element(int x,int y) {
        this.position = new Position(x,y);
    }

    public Position getPosition() {
        return position;
    }

    public int  getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
