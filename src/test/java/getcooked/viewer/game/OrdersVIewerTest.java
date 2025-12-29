package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.Order;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.mockito.Mockito.*;

class OrdersVIewerTest {
    private GUI gui;
    private OrdersViewer ordersViewer;
    private List<Order> orders;
    //size 3
    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        Order order = mock(Order.class);
        orders = List.of(order);
        ordersViewer = new OrdersViewer(orders);
    }

}
