package getcooked.model.game.kitchen;


import getcooked.model.game.kitchen.elements.Chef;
import getcooked.model.game.kitchen.elements.interactable.Interactable;
import getcooked.model.game.kitchen.Order;

import java.util.List;

public class Kitchen {
    private final int width;
    private final int height;

    private List<Chef> chefs;
    private List<Interactable> interactables;
    private List<Order> orders;

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

    public List<Interactable> getInteractables() {
        return interactables;
    }

    public List<Chef> getChefs() {
        return chefs;
    }

    public void setChefs(List<Chef> chefs) {
        this.chefs = chefs;
    }

    public void setInteractables(List<Interactable> interactables) {
        this.interactables = interactables;
    }

    public List<Order> getOrders() { return orders; }

    public void setOrders(List<Order> orders) { this.orders = orders; }

    // Probably add orders in here
    public void addOrder(Order order) { this.orders.add(order); }

}