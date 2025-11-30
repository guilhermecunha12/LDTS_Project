package getcooked.model.game.dish;

import java.util.Objects;

public class Dish {

    private final DishType type;

    public Dish(DishType type) {
        this.type = type;
    }

    public DishType getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Dish dish = (Dish) obj;
        return type == dish.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
