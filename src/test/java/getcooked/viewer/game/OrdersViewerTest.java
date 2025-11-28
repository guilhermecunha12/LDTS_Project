package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.kitchen.Order;
import getcooked.model.game.kitchen.elements.grabable.Dish;
import getcooked.model.game.kitchen.elements.grabable.DishType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class OrdersViewerTest {
    private GUI gui;
    private List<Order> orders;
    private OrdersViewer ordersViewer;

    @BeforeEach
    void setup() {
        gui = mock(GUI.class);
        orders = mock(List.class);
        ordersViewer = new OrdersViewer(orders);
    }

    //TODO: do drawElements test in OrdersViewer
    @Test
    void testDrawElementsOnlyCounts() {
        when(gui.getHeight()).thenReturn(20);
        when(orders.size()).thenReturn(1);

        Order order = mock(Order.class);
        when(orders.get(0)).thenReturn(order);

        Dish dish = mock(Dish.class);
        when(order.getDesiredDish()).thenReturn(dish);

        // do not mock the final enum class; return a real enum constant instead
        when(dish.getType()).thenReturn(DishType.POTATO_SOUP);

        when(order.getRemainingTime()).thenReturn(90);

        ordersViewer.drawElements(gui);

        verify(gui, times(1)).drawText(any(), anyString(), anyString(), anyString());
        verify(gui, times(1)).drawRectangle(any(), anyInt(), anyInt(), anyString());
        verify(gui, times(3)).drawText(any(), anyString(), anyString(), anyString());
    }
}
