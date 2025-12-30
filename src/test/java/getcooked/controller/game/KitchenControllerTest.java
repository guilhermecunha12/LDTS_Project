package getcooked.controller.game;

import getcooked.Game;
import getcooked.gui.GUI;
import getcooked.model.game.Kitchen;

import getcooked.model.game.Order;
import getcooked.model.game.OrderManager;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Cookware;
import getcooked.states.menu.EscapeMenuState;
import getcooked.states.menu.GameOverState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class KitchenControllerTest {
    private Kitchen kitchen;
    private Game game;
    private KitchenController kitchenController;

    @BeforeEach
    void setUp() {
        this.kitchen = mock(Kitchen.class);
        this.game = mock(Game.class);
        when(kitchen.getChefs()).thenReturn(List.of(mock(Chef.class)));
        this.kitchenController = new KitchenController(kitchen);
    }

    @Test
    void testStepWithEscapeAction() throws IOException {
        OrderManager orderManager = mock(OrderManager.class);

        when(kitchen.getOrderManager()).thenReturn(orderManager);
        when(kitchen.getActiveOrders()).thenReturn(Collections.emptyList());
        when(kitchen.getTimeForNewOrder()).thenReturn(1);
        when(kitchen.getLives()).thenReturn(3);

        kitchenController.step(game, Set.of(GUI.ACTION.ESCAPE), 0);

        verify(game,times(1)).changeState(any(EscapeMenuState.class));
    }

    @Test
    void testGameOverWhenLivesZero() throws IOException {
        OrderManager orderManager = mock(OrderManager.class);

        when(kitchen.getOrderManager()).thenReturn(orderManager);
        when(kitchen.getActiveOrders()).thenReturn(Collections.emptyList());
        when(kitchen.getTimeForNewOrder()).thenReturn(1);
        when(kitchen.getLives()).thenReturn(0);

        kitchenController.step(game, Set.of(), 0);

        verify(game,times(1)).changeState(any(GameOverState.class));
    }

    @Test
    void testOrderExpiresAndLifeDecreases() throws IOException {
        OrderManager orderManager = mock(OrderManager.class);
        Order order = mock(Order.class);

        List<Order> activeOrders = new ArrayList<>();
        activeOrders.add(order);

        when(kitchen.getOrderManager()).thenReturn(orderManager);
        when(orderManager.getActiveOrders()).thenReturn(activeOrders);
        when(order.isExpired()).thenReturn(true);
        when(orderManager.moreOrders()).thenReturn(false);
        when(kitchen.getLives()).thenReturn(2);

        kitchenController.step(game, Set.of(), 0);

        assertEquals(0, activeOrders.size());
        verify(order).tick();
        verify(kitchen).decreaseLives();
    }

    @Test
    void testAddNewOrderWhenTimeElapsed() throws IOException {
        OrderManager orderManager = mock(OrderManager.class);
        Order newOrder = mock(Order.class);

        List<Order> activeOrders = new ArrayList<>();

        when(kitchen.getOrderManager()).thenReturn(orderManager);
        when(orderManager.getActiveOrders()).thenReturn(activeOrders);
        when(orderManager.getTimeForNewOrder()).thenReturn(0);
        when(orderManager.moreOrders()).thenReturn(true);
        when(orderManager.popOrder()).thenReturn(newOrder);

        kitchenController.step(game, Set.of(), 0);

        verify(orderManager).addActiveOrder(newOrder);
        verify(orderManager).setTimeForNewOrder();
    }

    @Test
    void testNoNewOrderWhenMaxActiveOrdersReached() throws IOException {
        OrderManager orderManager = mock(OrderManager.class);
        List<Order> activeOrders = new ArrayList<>();

        when(kitchen.getOrderManager()).thenReturn(orderManager);
        when(orderManager.getTimeForNewOrder()).thenReturn(1);
        when(orderManager.getActiveOrders()).thenReturn(activeOrders);
        when(orderManager.moreOrders()).thenReturn(true);

        kitchenController.step(game, Set.of(), 0);

        verify(orderManager, times(1)).setTimeForNewOrder(Game.FPS);
    }

    @Test
    void testCookwareUpdateCalled() throws IOException {
        Cookware cookware = mock(Cookware.class);

        when(kitchen.getCookwares()).thenReturn(List.of(cookware));
        when(kitchen.getOrderManager()).thenReturn(mock(OrderManager.class));
        when(kitchen.getActiveOrders()).thenReturn(Collections.emptyList());
        when(kitchen.getLives()).thenReturn(1);

        kitchenController.step(game, Set.of(), 0);

        verify(cookware).update();
    }
}