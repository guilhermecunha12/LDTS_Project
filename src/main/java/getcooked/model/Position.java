package getcooked.model;

import java.util.Objects;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Position getUp() {
        return new Position(x, y-1);
    }

    public Position getDown() {
        return new Position(x, y+1);
    }

    public Position getLeft() {
        return new Position(x-1, y);
    }

    public Position getRight() {
        return new Position(x+1, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Position position = (Position) o;
        return (this.x == position.x && this.y == position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }
}
