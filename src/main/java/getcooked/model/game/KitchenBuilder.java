package getcooked.model.game;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Tile;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;

import java.util.*;

import static getcooked.Game.FPS;

public abstract class KitchenBuilder {
    protected final int level;
    protected final int lives;
    protected final List<DishType> availableDishTypes;
    private final int numPlayers;
    private final int ordersDelivered;

    public KitchenBuilder(int level, int numPlayers, int lives, int ordersDelivered) {
        // After the level 10 the kitchen to be drawn is always the same
        this.level = Math.min(level, 10);
        this.numPlayers = numPlayers;
        this.lives = lives;
        this.availableDishTypes = new ArrayList<>(); // empty list
        this.ordersDelivered = ordersDelivered;
    }

    public Kitchen createKitchen() {
        return new Kitchen(getWidth(), getHeight(), this.level, this.numPlayers,
                createChefs(), createTiles(), createOrders(), new ArrayList<>(), this.lives, 0, this.ordersDelivered);
    }

    // Creates the kitchen based on the width and the height of the file
    protected abstract int getWidth();

    protected abstract int getHeight();


    protected abstract List<Tile> createTiles();

    protected void addAvailableDishType(DishType dishType) {
        this.availableDishTypes.add(dishType);
    }
    private List<Chef> createChefs() {
        List<Chef> chefs = new ArrayList<>();
        int middleY = getHeight()/2;
        int middleX = getWidth()/2;

        if (this.numPlayers == 1) {
            chefs.add(new Chef(middleX, middleY));
        } else {
            chefs.add(new Chef(middleX-2, middleY));
            chefs.add(new Chef(middleX+1, middleY));
        }
        return chefs;
    }

    private int ordersPerLevel(int level) {
        return 2 + (int)Math.round(1.5 * level + Math.sqrt(level));
    }

    private int orderTimeLimit(int level) {
        double baseSeconds = 30.0;
        double minSeconds  = 15.0;
        double seconds     = baseSeconds - 2.0 * Math.log(level + 1.0);
        seconds = Math.max(seconds, minSeconds);
        return (int)Math.round(seconds * FPS);
    }

    private Deque<Order> createOrders() {
        Deque<Order> res = new ArrayDeque<>(); // empty list
        int numOrders = ordersPerLevel(this.level); // linear

        for (int i = 0; i < numOrders; i++) {
            DishType randomDishType = getRandomDishType();
            int orderTimeLimit = orderTimeLimit(this.level);
            Order order = new Order(new Dish(randomDishType), orderTimeLimit);
            res.addFirst(order);
        }
        return res;
    }

    private DishType getRandomDishType() {
        Random random = new Random();
        int randomIndex = random.nextInt(this.availableDishTypes.size());
        return this.availableDishTypes.get(randomIndex);
    }

    // constructor for testing, injecting availableDishTypes
    public KitchenBuilder(int level, int numPlayers, int lives, List<DishType> availableDishTypes, int ordersDelivered) {
        // After the level 10 the kitchen to be drawn is always the same
        this.level = Math.min(level, 10);
        this.numPlayers = numPlayers;
        this.lives = lives;
        this.availableDishTypes = availableDishTypes;
        this.ordersDelivered = ordersDelivered;
    }
}
