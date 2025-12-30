package getcooked.model.game;

import getcooked.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class OrderManagerTest {
    private OrderManager orderManager;
    private Deque<Order> levelOrders;
    private List<Order> activeOrders;
    private Order mockOrder1;
    private Order mockOrder2;

    @BeforeEach
    void setUp() {
        levelOrders = new ArrayDeque<>();
        activeOrders = new ArrayList<>();
        mockOrder1 = mock(Order.class);
        mockOrder2 = mock(Order.class);
    }

    @Test
    void testConstructor() {
        orderManager = new OrderManager(levelOrders, activeOrders, 100, 0);
        assertEquals(Game.FPS, orderManager.getTimeForNewOrder());
        assertEquals(0, orderManager.getOrdersDelivered());
    }

    @Test
    void testMoreOrdersWhenEmpty() {
        orderManager = new OrderManager(levelOrders, activeOrders, 100, 0);
        assertFalse(orderManager.moreOrders());
    }

    @Test
    void testMoreOrdersWhenNotEmpty() {
        levelOrders.add(mockOrder1);
        orderManager = new OrderManager(levelOrders, activeOrders, 100, 0);
        assertTrue(orderManager.moreOrders());
    }

    @Test
    void testPopOrder() {
        levelOrders.add(mockOrder1);
        levelOrders.add(mockOrder2);
        orderManager = new OrderManager(levelOrders, activeOrders, 100, 0);

        assertEquals(mockOrder2, orderManager.popOrder());
        assertEquals(mockOrder1, orderManager.popOrder());
    }

    @Test
    void testAddActiveOrder() {
        orderManager = new OrderManager(levelOrders, activeOrders, 100, 0);
        orderManager.addActiveOrder(mockOrder1);

        assertTrue(activeOrders.contains(mockOrder1));
        assertEquals(1, orderManager.getActiveOrders().size());
    }

    @Test
    void testRemoveActiveOrder() {
        activeOrders.add(mockOrder1);
        orderManager = new OrderManager(levelOrders, activeOrders, 100, 0);
        orderManager.removeActiveOrder(mockOrder1);

        assertFalse(activeOrders.contains(mockOrder1));
        assertEquals(0, orderManager.getActiveOrders().size());
    }

    @Test
    void testGetActiveOrders() {
        activeOrders.add(mockOrder1);
        activeOrders.add(mockOrder2);
        orderManager = new OrderManager(levelOrders, activeOrders, 100, 0);

        assertEquals(activeOrders, orderManager.getActiveOrders());
        assertEquals(2, orderManager.getActiveOrders().size());
    }

    @Test
    void testSetTimeForNewOrder() {
        orderManager = new OrderManager(levelOrders, activeOrders, 100, 0);
        orderManager.setTimeForNewOrder();

        assertEquals(100, orderManager.getTimeForNewOrder());
    }

    @Test
    void testSetTimeForNewOrderWithParameterHigher() {
        orderManager = new OrderManager(levelOrders, activeOrders, 100, 0);
        orderManager.setTimeForNewOrder(150);

        assertEquals(Game.FPS, orderManager.getTimeForNewOrder());
    }

    @Test
    void testDecreaseTimeForNewOrder() {
        orderManager = new OrderManager(levelOrders, activeOrders, 100, 0);
        int initialTime = orderManager.getTimeForNewOrder();
        orderManager.decreaseTimeForNewOrder();

        assertEquals(initialTime - 1, orderManager.getTimeForNewOrder());
    }

    @Test
    void testIncrementOrdersDelivered() {
        orderManager = new OrderManager(levelOrders, activeOrders, 100, 5);
        orderManager.incrementOrdersDelivered();

        assertEquals(6, orderManager.getOrdersDelivered());
    }

    @Test
    void testGetOrdersDelivered() {
        orderManager = new OrderManager(levelOrders, activeOrders, 100, 10);

        assertEquals(10, orderManager.getOrdersDelivered());
    }
}
