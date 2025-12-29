package getcooked.viewer.game;

import getcooked.gui.GUI;
import getcooked.model.Position;
import getcooked.model.game.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
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

    @Test
    void testDraw() throws IOException, URISyntaxException, FontFormatException {

        ordersViewer.draw(gui);

        verify(gui,times(1)).drawText(new Position(270,60),"Orders: ","#ffffff");
        verify(gui,atLeastOnce()).drawPixel(anyInt(), anyInt(), any());
    }
}
