package getcooked.model.game.dish;

public class Dish {

    private final DishType type;

    public Dish(DishType type) {
        this.type = type;
    }

    public DishType getType() {
        return type;
    }
}
