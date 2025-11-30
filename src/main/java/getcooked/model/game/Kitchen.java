package getcooked.model.game;


import getcooked.model.Position;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Floor;
import getcooked.model.game.elements.Tile;

import java.util.Deque;
import java.util.List;

public class Kitchen {
    public static final int MAX_ACTIVE_ORDERS = 3;
    private final int width;
    private final int height;
    private final int level;
    private final int numPlayers;
    private final List<Chef> chefs;
    private final List<Tile> tiles;
    private final Deque<Order> levelOrders;
    private final List<Order> activeOrders;
    private int lives;
    private int timeForNewOrder;


    public Kitchen(int width, int height, int level, int numPlayers, List<Chef> chefs, List<Tile> tiles,
                   Deque<Order> levelOrders, List<Order> activeOrders, int lives, int timeForNewOrder) {
        this.width = width;
        this.height = height;
        this.level = level;
        this.numPlayers = numPlayers;
        this.chefs = chefs;
        this.tiles = tiles;
        this.levelOrders = levelOrders;
        this.activeOrders = activeOrders;
        this.lives = lives;
        this.timeForNewOrder = timeForNewOrder;
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

    public Deque<Order> getOrders() {
        return levelOrders;
    }

    public void addActiveOrder(Order order) {
        this.activeOrders.add(order);
    }

    public void removeActiveOrder(Order order) {
        this.activeOrders.remove(order);
    }

    public List<Order> getActiveOrders() {
        return activeOrders;
    }

    public int getTimeForNewOrder() {
        return timeForNewOrder;
    }

    public void setTimeForNewOrder (int timeForNewOrder) {
        this.timeForNewOrder = timeForNewOrder;
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