package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.Order;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class OrdersViewerTest {
    private GUI gui;
    private OrdersViewer ordersViewer;
    private List<Order> orders;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);

        Order order = mock(Order.class);
        Dish desiredDish = new Dish(DishType.SALAD);

        when(order.getDesiredDish()).thenReturn(desiredDish);
        when(order.getRemainingTime()).thenReturn(120); // 2 seconds if FPS = 60

        orders = List.of(order);
        ordersViewer = new OrdersViewer(orders);
    }

    @Test
    void testDrawElements() throws Exception {
        ordersViewer.drawElements(gui);

        verify(gui, atLeastOnce()).drawText(any(), anyString(), anyString());
    }

}
