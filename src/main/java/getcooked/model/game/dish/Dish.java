package getcooked.model.game.dish;

public record Dish(DishType type) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Dish dish = (Dish) obj;
        return type == dish.type;
    }
}
