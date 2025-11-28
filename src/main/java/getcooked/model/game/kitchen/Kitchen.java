package getcooked.model.game.kitchen;


import getcooked.model.Position;
import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.Floor;
import getcooked.model.game.kitchen.elements.Tile;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Kitchen {
    public static final int MAX_ACTIVE_ORDERS = 3;
    private final int width;
    private final int height;
    private final int level;

    private List<Chef> chefs;
    private List<Tile> tiles;
    private Deque<Order> orders; // TODO: for each kitchen, this also should be final... pass through the constructor?
    private final List<Order> activeOrders;
    private int lives;
    private int numPlayers; // TODO: this should be final... pass through the constructor?

    public Kitchen(int width, int height, int level) {
        this.width = width;
        this.height = height;
        this.level = level;
        this.orders = new ArrayDeque<>();
        this.activeOrders = new ArrayList<>();
    }


    public int getLevel() {
        return level;
    }

    public  void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
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

    public void setChefs(List<Chef> chefs) {
        this.chefs = chefs;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public Deque<Order> getOrders() { return orders; }

    public void setOrders(Deque<Order> orders) { this.orders = orders; }

    // Probably add orders in here
    public void addActiveOrder(Order order) { this.activeOrders.add(order); }
    public void removeActiveOrder(Order order) {
        this.activeOrders.remove(order);
    }

    public List<Order> getActiveOrders() {
        return activeOrders;
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