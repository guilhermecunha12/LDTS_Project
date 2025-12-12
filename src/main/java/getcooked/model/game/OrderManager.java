package getcooked.model.game;

import java.util.Deque;
import java.util.List;

public class OrderManager {
    public static final int MAX_ACTIVE_ORDERS = 3;
    private final Deque<Order> levelOrders;
    private final List<Order> activeOrders;
    private int timeForNewOrder;
    private int ordersDelivered;

    public OrderManager(Deque<Order> levelOrders, List<Order> activeOrders, int timeForNewOrder, int ordersDelivered) {
        this.levelOrders = levelOrders;
        this.activeOrders = activeOrders;
        this.timeForNewOrder = timeForNewOrder;
        this.ordersDelivered = ordersDelivered;
    }

    public Deque<Order> getLevelOrders() {
        return levelOrders;
    }

    public boolean ordersAreEmpty() {
        return this.levelOrders.isEmpty();
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

    public void setTimeForNewOrder (int timeForNewOrder) {
        this.timeForNewOrder = timeForNewOrder;
    }

    public void incrementOrdersDelivered() {
        this.ordersDelivered++;
    }

    public int getOrdersDelivered() {
        return ordersDelivered;
    }
}
