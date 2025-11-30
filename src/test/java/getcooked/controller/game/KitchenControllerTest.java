package getcooked.controller.game;

import getcooked.Game;
import getcooked.gui.GUI;
import getcooked.model.game.Kitchen;

import getcooked.model.game.Order;
import getcooked.model.game.elements.Chef;
import getcooked.states.game.KitchenState;
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
        when(kitchen.getActiveOrders()).thenReturn(Collections.emptyList());
        when(kitchen.getTimeForNewOrder()).thenReturn(1);
        when(kitchen.getOrders()).thenReturn(new ArrayDeque<>());
        when(kitchen.getLives()).thenReturn(3);

        kitchenController.step(game, Set.of(GUI.ACTION.ESCAPE), 0);

        verify(game,times(1)).changeState(any(EscapeMenuState.class));
    }

    @Test
    void testGameOverWhenLivesZeroAndDecreaseOrderTime() throws IOException {
        when(kitchen.getActiveOrders()).thenReturn(Collections.emptyList());
        when(kitchen.getTimeForNewOrder()).thenReturn(1);
        when(kitchen.getOrders()).thenReturn(new ArrayDeque<>());
        when(kitchen.getLives()).thenReturn(0);

        kitchenController.step(game, Set.of(), 0);

        verify(kitchen,times(1)).setTimeForNewOrder(0);
        verify(game).changeState(any(GameOverState.class));
    }

    @Test
    void testGameOverWhenLivesZeroAndAddNewOrder() throws IOException {
        Order order = mock(Order.class);
        Deque<Order> orders = new ArrayDeque<>();
        orders.add(order);

        when(kitchen.getActiveOrders()).thenReturn(Collections.emptyList());
        when(kitchen.getTimeForNewOrder()).thenReturn(0);
        when(kitchen.getLives()).thenReturn(0);
        when(kitchen.getOrders()).thenReturn(orders);
        when(kitchen.getLevel()).thenReturn(1);

        kitchenController.step(game, Set.of(), 0);

        verify(kitchen,times(1)).addActiveOrder(order);
        verify(kitchen,times(1)).setTimeForNewOrder(400);
    }

    @Test
    void testAdvanceToNextLevelWhenNoOrdersLeft() throws IOException {
        when(kitchen.getActiveOrders()).thenReturn(new ArrayList<>());
        when(kitchen.getTimeForNewOrder()).thenReturn(1);
        when(kitchen.getOrders()).thenReturn(new ArrayDeque<>());
        when(kitchen.getLevel()).thenReturn(1);
        when(kitchen.getLives()).thenReturn(1);

        kitchenController.step(game, Set.of(), 0);

        verify(game,times(1)).changeState(any(KitchenState.class));
    }

    @Test
    void testManageOrdersTick() throws IOException {
        Order orderNotExpired = mock(Order.class);
        Order orderExpired = mock(Order.class);
        List<Order> orders = new ArrayList<>();
        orders.add(orderNotExpired);
        orders.add(orderExpired);

        when(kitchen.getActiveOrders()).thenReturn(orders);
        when(kitchen.getTimeForNewOrder()).thenReturn(0);
        when(kitchen.getTimeForNewOrder()).thenReturn(1);
        when(orderNotExpired.isExpired()).thenReturn(false);
        when(orderExpired.isExpired()).thenReturn(true);

        kitchenController.step(game, Set.of(), 0);

        verify(orderNotExpired,times(1)).tick();
        verify(orderExpired,times(1)).tick();
        verify(kitchen,times(1)).decreaseLives();
        assertEquals(1, orders.size());
    }
}