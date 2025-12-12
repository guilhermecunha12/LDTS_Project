package getcooked.model.game;


import getcooked.model.Position;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Floor;
import getcooked.model.game.elements.Tile;

import java.util.Deque;
import java.util.List;

public class Kitchen {
    private final int width;
    private final int height;
    private final int level;
    private final int numPlayers;
    private final List<Chef> chefs;
    private final List<Tile> tiles;
    private int lives;
    private final OrderManager orderManager;


    public Kitchen(int width, int height, int level, int numPlayers, List<Chef> chefs, List<Tile> tiles, int lives, OrderManager orderManager) {
        this.width = width;
        this.height = height;
        this.level = level;
        this.numPlayers = numPlayers;
        this.chefs = chefs;
        this.tiles = tiles;
        this.lives = lives;
        this.orderManager = orderManager;
    }

    public int getLevel() {
        return level;
    }

    public int getLives() {
        return lives;
    }

    public void decreaseLives() {
        this.lives--;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public List<Chef> getChefs() {
        return chefs;
    }

    public OrderManager getOrderManager() {
        return orderManager;
    }

    //TODO: after correcting the tests, verify the methods related to orders are needed here
    public Deque<Order> getOrders() {
        return orderManager.getOrders();
    }

    public void addActiveOrder(Order order) {
        orderManager.addActiveOrder(order);
    }

    public void removeActiveOrder(Order order) {
        orderManager.removeActiveOrder(order);
    }

    public List<Order> getActiveOrders() {
        return orderManager.getActiveOrders();
    }

    public int getTimeForNewOrder() {
        return orderManager.getTimeForNewOrder();
    }

    public void setTimeForNewOrder (int timeForNewOrder) {
        orderManager.setTimeForNewOrder(timeForNewOrder);
    }

    public void incrementOrdersDelivered() {
        orderManager.incrementOrdersDelivered();
    }

    public int getOrdersDelivered() {
        return orderManager.getOrdersDelivered();
    }

    // Return the object of the tile
    public Tile getTile(Position position) {
        for(Tile tile : tiles){
            if(tile.getPosition().equals(position))
                return tile;
        }
        return null;
    }

    public boolean isEmpty(Position position) {
        for(Tile tile : tiles){
            if(tile.getPosition().equals(position) && !(tile instanceof Floor))
                return false;
        }
        for(Chef chef : chefs){
            if(chef.getPosition().equals(position))
                return false;
        }
        return true;
    }
}