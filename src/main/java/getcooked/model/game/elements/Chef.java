package getcooked.model.game.elements;

import getcooked.model.Position;
import getcooked.model.game.elements.grabable.Grabable;

public class Chef extends Element {
    private enum Direction {UP, DOWN, LEFT, RIGHT}

    private Grabable item;
    private Direction direction;

    public Chef(int x, int y) {
        super(x, y);
        item = null;
        this.direction = Direction.UP;
    }

    public Grabable getItem() {
        return item;
    }

    public void pickUp(Grabable item) {
        this.item = item;
        item.setPosition(this.getPosition());
    }

    public Grabable drop() {
        Grabable tmp = this.item;
        this.item = null;
        return tmp;
    }

    public void turnLeft() {
        this.direction = Direction.LEFT;
    }

    public void turnRight() {
        this.direction = Direction.RIGHT;
    }

    public void turnUp() {
        this.direction = Direction.UP;
    }

    public void turnDown() {
        this.direction = Direction.DOWN;
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
        if (this.isHoldingSomething()) item.setPosition(position); // also update the item's position
    }

    public Position getFrontPosition() {
        return switch (direction) {
            case UP -> this.getPosition().getUp();
            case DOWN -> this.getPosition().getDown();
            case LEFT -> this.getPosition().getLeft();
            default -> this.getPosition().getRight();
        };
    }

    public boolean isHoldingSomething(){
        return item != null;
    }
}
