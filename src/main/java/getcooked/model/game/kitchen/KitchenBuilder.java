package getcooked.model.game.kitchen;

import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.Tile;
import getcooked.model.game.kitchen.elements.grabable.Dish;
import getcooked.model.game.kitchen.elements.grabable.DishType;

import java.util.*;

public abstract class KitchenBuilder {
    protected final int level;
    protected List<DishType> availableDishTypes; // used Class because food has a position and that is not important here

    public KitchenBuilder(int level) {
        // After the level 10 the kitchen to be drawn is always the same
        this.level = Math.min(level, 10);
        this.availableDishTypes = new ArrayList<>(); // empty list
    }

    public Kitchen createKitchen() {
        Kitchen kitchen = new Kitchen(getWidth(), getHeight());
        // Set the different elements inside the kitchen
        kitchen.setChefs(createChefs());
        kitchen.setTiles(createTiles());
        kitchen.setOrders(createOrders());
        kitchen.setLevel(this.level); // initial level
        kitchen.setLives(3); // initial lives
        return kitchen;
    }

    // Creates the kitchen based on the width and the height of the file
    protected abstract int getWidth();

    protected abstract int getHeight();


    protected abstract List<Tile> createTiles();

    protected abstract List<Chef> createChefs();

    private Deque<Order> createOrders() {
        Deque<Order> res = new ArrayDeque<>(); // empty list
        int numOrders = 3*this.level; // linear

        for (int i = 0; i < numOrders; i++) {
            DishType randomDishType = getRandomDishType();
            Order order = new Order(new Dish(randomDishType), 1000);
            res.addFirst(order);
        }
        return res;
    }

    private DishType getRandomDishType() {
        Random random = new Random();
        int randomIndex = random.nextInt(this.availableDishTypes.size());
        return this.availableDishTypes.get(randomIndex);
    }
}
