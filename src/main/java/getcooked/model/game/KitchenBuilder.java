package getcooked.model.game;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Interactable;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.interactable.DeliverySpot;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Cookware;

import java.util.*;

import static getcooked.Game.FPS;

public abstract class KitchenBuilder {
    protected final int level;
    protected final int lives;
    protected final List<DishType> availableDishTypes;
    private final int numPlayers;
    private final int ordersDelivered;
    private final Random random = new Random(); // for order generation
    protected final List<DeliverySpot> deliverySpots; // to later set order manager to each of them
    protected final List<Cookware> cookwares; // to later update its cooking state every step (timed behaviour)

    protected KitchenBuilder(int level, int numPlayers, int lives, int ordersDelivered) {
        // After level 10 the kitchen to be drawn is always the same
        this.level = Math.min(level, 10);
        this.numPlayers = numPlayers;
        this.lives = lives;
        this.availableDishTypes = new ArrayList<>();
        this.ordersDelivered = ordersDelivered;
        this.deliverySpots = new ArrayList<>();
        this.cookwares = new ArrayList<>();
    }

    public Kitchen createKitchen() {
        List<Chef> chefs = createChefs();
        List<Interactable> interactables = createInteractables();
        OrderManager orderManager = new OrderManager(createOrders(), new ArrayList<>(),
                orderFrequency(this.level), this.ordersDelivered);
        for(DeliverySpot deliverySpot : deliverySpots) {
            deliverySpot.setOrderManager(orderManager);
        }
        return new Kitchen(this.level, this.numPlayers, chefs, interactables, this.lives, orderManager, cookwares);
    }

    // Creates the kitchen based on the width and the height of the file
    protected abstract int getWidth();

    protected abstract int getHeight();

    protected abstract List<Interactable> createInteractables();

    protected void addAvailableDishType(DishType dishType) {
        this.availableDishTypes.add(dishType);
    }

    private List<Chef> createChefs() {
        List<Chef> chefs = new ArrayList<>();
        int middleY = getHeight()/2;
        int middleX = getWidth()/2;

        if (this.numPlayers == 1) {
            chefs.add(new Chef(middleX, middleY, 0));
        } else {
            chefs.add(new Chef(middleX-2, middleY, 0));
            chefs.add(new Chef(middleX+1, middleY, 1));
        }
        return chefs;
    }

    private static int ordersPerLevel(int level) {
        return level + 2;
    }

    private static int orderTimeLimit(int level) {
        double baseSeconds = 40.0;
        double minSeconds  = 15.0;
        double seconds     = baseSeconds - level;
        seconds = Math.max(seconds, minSeconds);
        return (int)Math.round(seconds * FPS);
    }

    private int orderFrequency(int level) { // gets lower each level
        double baseSeconds = 12.0;
        if (this.numPlayers == 1){
            return (int)Math.max(7.0*FPS, (baseSeconds - 0.5*level)*FPS);
        } else{
            return (int)Math.max(5.0*FPS, (baseSeconds - level)*FPS);
        }
    }

    private Deque<Order> createOrders() {
        Deque<Order> res = new ArrayDeque<>();
        int numOrders = ordersPerLevel(this.level);
          
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
}
