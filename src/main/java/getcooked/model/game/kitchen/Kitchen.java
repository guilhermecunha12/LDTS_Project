package getcooked.model.game.kitchen;


import getcooked.model.Position;
import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.Tile;

import java.util.Deque;
import java.util.List;

public class Kitchen {
    private final int width;
    private final int height;

    private List<Chef> chefs;
    private List<Tile> tiles;
    private Deque<Order> orders;
    private List<Order> activeOrders;

    public Kitchen(int width, int height) {
        this.width = width;
        this.height = height;
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