package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.Order;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class OrdersViewerTest {
    private GUI gui;
    private List<Order> orders;
    private OrdersViewer ordersViewer;

    @BeforeEach
    void setup() {
        gui = mock(GUI.class);
        orders = mock(List.class);
        ordersViewer = new OrdersViewer(orders);
    }
    @Test
    void testDrawOrders() {
        Order order = mock(Order.class);
        Dish dish = new Dish(DishType.POTATO_SOUP);

        when(orders.size()).thenReturn(1);
        when(orders.get(0)).thenReturn(order);
        when(order.getDesiredDish()).thenReturn(dish);
        when(order.getRemainingTime()).thenReturn(150); // 5 seconds if divided by 30

        ordersViewer.drawElements(gui);

        verify(gui,times(1)).drawText(new getcooked.model.Position(1, gui.getHeight() - 3), "Orders: ", "#ffffff", "#000000");
        verify(gui,times(1)).drawRectangle(new getcooked.model.Position(9, gui.getHeight() - 5), 12, 4, "#ffff88");
        verify(gui,times(1)).drawText(new getcooked.model.Position(9, gui.getHeight() - 5), "POTATO_SOUP", "#000000", "#ffff88");
        verify(gui,times(1)).drawText(new getcooked.model.Position(9, gui.getHeight() - 2), "5", "#000000", "#ffff88");
    }
}
