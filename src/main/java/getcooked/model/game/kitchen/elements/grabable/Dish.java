package getcooked.model.game.kitchen.elements.grabable;

public class Dish {

    private final DishType type;

    public Dish(DishType type) {
        this.type = type;
    }

    public DishType getType() {
        return type;
    }
}
