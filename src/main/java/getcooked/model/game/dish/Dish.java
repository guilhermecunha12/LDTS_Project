package getcooked.model.game.dish;

import java.util.Objects;

public class Dish {
    private final DishType type;

    public Dish(DishType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(obj == null) return false;
        if (!(obj instanceof Dish dish)) return false;

        return this.type == dish.type;
    }

    public DishType type() {
        return type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
