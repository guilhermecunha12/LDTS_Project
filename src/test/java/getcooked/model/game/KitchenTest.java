package getcooked.model.game;

import getcooked.model.Position;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.Interactable;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Cookware;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KitchenTest {
    private int level;
    private int numPlayers;
    private List<Chef> chefs;
    private List<Interactable> interactables;
    private int lives;
    private OrderManager orderManager;
    private Kitchen kitchen;
    private List<Cookware> cookwares;


    @BeforeEach
    public void setUp() {
        this.level = 10;
        this.numPlayers = 2;
        this.lives = 3;
        this.chefs = new ArrayList<>();
        this.interactables = new ArrayList<>();
        this.orderManager = mock(OrderManager.class);
        this.kitchen = new Kitchen(level, numPlayers, chefs, interactables, lives, orderManager, cookwares);
    }

    @Test
    void testGetInteractableValidPosition(){
        Interactable interactable = mock(Interactable.class);
        interactables.add(interactable);
        Position position = new Position(0, 0);
        when(interactable.getPosition()).thenReturn(new Position(0, 0));
        assertEquals(interactable, kitchen.getInteractable(position));
    }

    @Test
    void testGetInteractableInvalidPosition(){
        Interactable interactable = mock(Interactable.class);
        interactables.add(interactable);
        Position position = new Position(1, 0);
        when(interactable.getPosition()).thenReturn(new Position(0, 0));
        assertNull(kitchen.getInteractable(position));
    }

    @Test
    void testEmptyInteractables(){
        assertNull(kitchen.getInteractable(null));
    }

    @Test
    void testIsEmptyNoBlocking(){
        Interactable interactable = mock(Interactable.class);
        interactables.add(interactable);
        Position position = new Position(0, 0);
        when(interactable.getPosition()).thenReturn(new Position(0,0));
        when(interactable.isBlocking()).thenReturn(false);
        assertTrue(kitchen.isEmpty(position));
    }

    @Test
    void testIsEmptyBlocking(){
        Interactable interactable = mock(Interactable.class);
        interactables.add(interactable);
        Position position = new Position(1, 0);
        when(interactable.getPosition()).thenReturn(new Position(0,0));
        when(interactable.isBlocking()).thenReturn(true);
        assertTrue(kitchen.isEmpty(position));
    }

    @Test
    void testNotEmpty(){
        Interactable interactable = mock(Interactable.class);
        interactables.add(interactable);
        Position position = new Position(0, 0);
        when(interactable.getPosition()).thenReturn(new Position(0,0));
        when(interactable.isBlocking()).thenReturn(true);
        assertFalse(kitchen.isEmpty(position));
    }

    @Test
    void testEmptyChefs(){
        Chef chef = mock(Chef.class);
        chefs.add(chef);
        Position position = new Position(1, 0);
        when(chef.getPosition()).thenReturn(new Position(0, 0));
        assertTrue(kitchen.isEmpty(position));
    }

    @Test
    void testNotEmptyChefs(){
        Chef chef = mock(Chef.class);
        chefs.add(chef);
        Position position = new Position(0, 0);
        when(chef.getPosition()).thenReturn(new Position(0,0));
        assertFalse(kitchen.isEmpty(position));
    }

    @Test
    void testGetLevel() {
        assertEquals(10, kitchen.getLevel());
    }

    @Test
    void testGetLives() {
        assertEquals(3, kitchen.getLives());
    }

    @Test
    void testDecreaseLives() {
        kitchen.decreaseLives();
        assertEquals(2, kitchen.getLives());
    }

    @Test
    void testGetNumPlayers() {
        assertEquals(2, kitchen.getNumPlayers());
    }

    @Test
    void testGetInteractables() {
        Interactable interactable = mock(Interactable.class);
        interactables.add(interactable);
        assertEquals(interactables, kitchen.getInteractables());
    }

    @Test
    void testGetChefs() {
        Chef chef = mock(Chef.class);
        chefs.add(chef);
        assertEquals(chefs, kitchen.getChefs());
    }

    @Test
    void testGetOrderManager() {
        assertEquals(orderManager, kitchen.getOrderManager());
    }

    @Test
    void testMoreOrders() {
        when(orderManager.moreOrders()).thenReturn(true);
        assertTrue(kitchen.moreOrders());
    }

    @Test
    void testAddActiveOrder() {
        Order order = mock(Order.class);
        kitchen.addActiveOrder(order);
        verify(orderManager, times(1)).addActiveOrder(order);
    }

    @Test
    void testRemoveActiveOrder() {
        Order order = mock(Order.class);
        kitchen.removeActiveOrder(order);
        verify(orderManager, times(1)).removeActiveOrder(order);
    }

    @Test
    void testGetActiveOrders() {
        List<Order> orders = new ArrayList<>();
        when(orderManager.getActiveOrders()).thenReturn(orders);
        assertEquals(orders, kitchen.getActiveOrders());
    }

    @Test
    void testGetTimeForNewOrder() {
        when(orderManager.getTimeForNewOrder()).thenReturn(5);
        assertEquals(5, kitchen.getTimeForNewOrder());
    }

    @Test
    void testSetTimeForNewOrder() {
        kitchen.setTimeForNewOrder();
        verify(orderManager, times(1)).setTimeForNewOrder();
    }

    @Test
    void testGetOrdersDelivered() {
        when(orderManager.getOrdersDelivered()).thenReturn(10);
        assertEquals(10, kitchen.getOrdersDelivered());
    }

}
