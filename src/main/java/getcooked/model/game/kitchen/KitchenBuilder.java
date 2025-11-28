package getcooked.model.game.kitchen;

import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.Tile;
import getcooked.model.game.kitchen.elements.grabable.Dish;
import getcooked.model.game.kitchen.elements.grabable.DishType;

import java.util.*;

public abstract class KitchenBuilder {
    protected final int level;
    protected final int numPlayers;
    protected List<DishType> availableDishTypes; // used Class because food has a position and that is not important here

    public KitchenBuilder(int level, int numPlayers) {
        // After the level 10 the kitchen to be drawn is always the same
        this.level = Math.min(level, 10);
        this.numPlayers = numPlayers;
        this.availableDishTypes = new ArrayList<>(); // empty list
    }

    public Kitchen createKitchen() {
        Kitchen kitchen = new Kitchen(getWidth(), getHeight(), this.level);
        // Set the different elements inside the kitchen
        kitchen.setChefs(createChefs());
        kitchen.setTiles(createTiles());
        kitchen.setOrders(createOrders());
        kitchen.setNumPlayers(this.numPlayers);
        kitchen.setLives(3); // TODO: lives should belong to the playing session, otherwise lives are reset everytime the kitchen is created
        return kitchen;
    }

    // Creates the kitchen based on the width and the height of the file
    protected abstract int getWidth();

    protected abstract int getHeight();

    protected abstract List<Tile> createTiles();

    //TODO: add exception that verifies that the chefs are on floor tiles positions
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

    private Deque<Order> createOrders() {
        Deque<Order> res = new ArrayDeque<>(); // empty list
        int numOrders = 3*this.level; // linear

        for (int i = 0; i < numOrders; i++) {
            DishType randomDishType = getRandomDishType();
            Order order = new Order(new Dish(randomDishType), 900); //TODO: change the timeLimit according to the current level
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
