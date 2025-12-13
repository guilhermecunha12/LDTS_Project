package getcooked.model.game;

import getcooked.Game;
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

    protected KitchenBuilder(int level, int numPlayers, int lives, int ordersDelivered) {
        // After the level 10 the kitchen to be drawn is always the same
        this.level = Math.min(level, 10);
        this.numPlayers = numPlayers;
        this.lives = lives;
        this.availableDishTypes = new ArrayList<>(); // empty list
        this.ordersDelivered = ordersDelivered;
    }

    public Kitchen createKitchen() {
        return new Kitchen(getWidth(), getHeight(), this.level, this.numPlayers, createChefs(), createTiles(), this.lives,
                new OrderManager(createOrders(), new ArrayList<>(), orderFrequency(this.level), this.ordersDelivered));
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
        return 2 + (int)(level/(0.3*Math.sqrt(level)));
    }

    private int orderTimeLimit(int level) {
        double baseSeconds = 35.0;
        double minSeconds  = 15.0;
        double seconds     = baseSeconds - 10.0 * Math.log10(level);
        seconds = Math.max(seconds, minSeconds);
        return (int)Math.round(seconds * FPS);
    }

    private int orderFrequency(int level) { // gets lower each level
        int decay = (int)(2.6*FPS * Math.log10(level));
        if (this.numPlayers == 2) decay = (int)(3.3*FPS * Math.log10(level));
        return Math.max(10*FPS, 15*FPS - decay);
    }

    private Deque<Order> createOrders() {
        Deque<Order> res = new ArrayDeque<>(); // empty list
        int numOrders = ordersPerLevel(this.level);
        Random random = new Random();
          
        for (int i = 0; i < numOrders; i++) {
            DishType randomDishType = getRandomDishType(random);
            int orderTimeLimit = orderTimeLimit(this.level);
            Order order = new Order(new Dish(randomDishType), orderTimeLimit);
            res.addFirst(order);
        }
        return res;
    }

    private DishType getRandomDishType(Random random) {
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
