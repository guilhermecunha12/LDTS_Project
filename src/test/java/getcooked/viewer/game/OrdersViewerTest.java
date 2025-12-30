package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.game.Order;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class OrdersViewerTest {
    private GUI gui;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
    }

    @Test
    void testDrawOrders() throws Exception {
        List<Order> orders = new ArrayList<>();

        for(DishType type : DishType.values()) {
            Order order = mock(Order.class);
            Dish dish = new Dish(type);

            when(order.getDesiredDish()).thenReturn(dish);
            when(order.getRemainingTime()).thenReturn(20);

            orders.add(order);
        }

        OrdersViewer ordersViewer = new OrdersViewer(orders);

        ordersViewer.draw(gui);

        verify(gui, atLeast(orders.size())).drawPixel(anyInt(), anyInt(), any());
    }

}
