package getcooked.model.game;

import getcooked.Game;

import java.util.Deque;
import java.util.List;

public class OrderManager {
    public static final int MAX_ACTIVE_ORDERS = 4;
    private final Deque<Order> levelOrders;
    private final List<Order> activeOrders;
    private final int orderFrequency;
    private int timeForNewOrder;
    private int ordersDelivered;

    public OrderManager(Deque<Order> levelOrders, List<Order> activeOrders, int orderFrequency, int ordersDelivered) {
        this.levelOrders = levelOrders;
        this.activeOrders = activeOrders;
        this.orderFrequency = orderFrequency;
        this.timeForNewOrder = Game.FPS; // wait a second before showing the first order
        this.ordersDelivered = ordersDelivered;
    }

    public boolean moreOrders() {
        return !this.levelOrders.isEmpty();
    }

    public Order popOrder() {
        return this.levelOrders.removeLast();
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

    public void setTimeForNewOrder() {
        this.timeForNewOrder = orderFrequency;
    }

    public void setTimeForNewOrder(int time) {
        this.timeForNewOrder = Math.min(this.timeForNewOrder, time);
    }

    public void decreaseTimeForNewOrder() {
        this.timeForNewOrder--;
    }

    public void incrementOrdersDelivered() {
        this.ordersDelivered++;
    }

    public int getOrdersDelivered() {
        return ordersDelivered;
    }
}
