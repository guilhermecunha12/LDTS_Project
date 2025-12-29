package getcooked.model.game.elements;

public abstract class Tile extends Element {
    protected Tile(int x, int y) {
        super(x, y);
    }

    public abstract boolean isBlocking();
}
