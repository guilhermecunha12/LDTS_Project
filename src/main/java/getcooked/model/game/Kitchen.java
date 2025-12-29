package getcooked.model.game;


import getcooked.model.Position;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Interactable;

import java.util.List;

public class Kitchen {
    private final int level;
    private final int numPlayers;
    private final List<Chef> chefs;
    private final List<Interactable> interactables;
    private int lives;
    private final OrderManager orderManager;


    public Kitchen(int level, int numPlayers, List<Chef> chefs,
                   List<Interactable> interactables, int lives, OrderManager orderManager) {
        this.level = level;
        this.numPlayers = numPlayers;
        this.chefs = chefs;
        this.interactables = interactables;
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

    public List<Interactable> getInteractables() {
        return interactables;
    }

    public List<Chef> getChefs() {
        return chefs;
    }

    public OrderManager getOrderManager() {
        return orderManager;
    }

    public boolean moreOrders() {
        return orderManager.moreOrders();
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

    public void setTimeForNewOrder() {
        orderManager.setTimeForNewOrder();
    }

    public int getOrdersDelivered() {
        return orderManager.getOrdersDelivered();
    }

    public Interactable getInteractable(Position position) {
        for(Interactable interactable : interactables){
            if(interactable.getPosition().equals(position))
                return interactable;
        }
        return null;
    }

    public boolean isEmpty(Position position) {
        for(Interactable interactable : interactables){
            if(interactable.getPosition().equals(position) && interactable.isBlocking())
                return false;
        }
        for(Chef chef : chefs){
            if(chef.getPosition().equals(position))
                return false;
        }
        return true;
    }
}
