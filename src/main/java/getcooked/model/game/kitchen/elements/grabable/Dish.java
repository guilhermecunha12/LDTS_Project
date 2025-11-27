package getcooked.model.game.kitchen.elements.grabable;

public class Dish extends Grabable {

    public enum DishType {
        POTATO_SOUP,
        CARROT_SOUP;
    }

    private final DishType type;

    public Dish(int x, int y, DishType type) {
        super(x, y);
        this.type = type;
    }

    public DishType getType() {
        return type;
    }
}
