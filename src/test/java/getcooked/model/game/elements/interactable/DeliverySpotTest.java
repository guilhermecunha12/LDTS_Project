package getcooked.model.game.elements.interactable;

import getcooked.model.game.Kitchen;
import getcooked.model.game.Order;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static getcooked.model.game.dish.DishType.CARROT_SOUP;
import static getcooked.model.game.dish.DishType.POTATO_SOUP;
import static org.mockito.Mockito.*;

public class DeliverySpotTest {
    private DeliverySpot deliverySpot;
    private Chef chef;
    private Grabable item;
    private Kitchen kitchen;

    @BeforeEach
    void setUp() {
        this.deliverySpot = new DeliverySpot(0, 0);
        this.deliverySpot.setKitchen(mock(Kitchen.class));
        this.chef = mock(Chef.class);
        this.kitchen = mock(Kitchen.class);
    }

    @Test
    void testInteract_chefIsNotHoldingAnything() {
        this.deliverySpot.setKitchen(kitchen); // more branch coverage

        when(chef.isHoldingSomething()).thenReturn(false);

        deliverySpot.interact(chef, kitchen);
        // does not satisfy the branch condition (short circuit evaluation)
        verify(chef, times(0)).getItem();
    }

    @Test
    void testInteract_chefIsHoldingNonPlate() {
        when(chef.isHoldingSomething()).thenReturn(true);
        this.item = mock(Food.class); // every Grabable that is not a Plate
        when(chef.getItem()).thenReturn(item);

        deliverySpot.interact(chef, kitchen);
        // does not satisfy the branch condition
        verify(kitchen, times(0)).getActiveOrders();
    }

    @Test
    void testInteract_chefIsHoldingEmptyPlate() {
        when(chef.isHoldingSomething()).thenReturn(true);
        this.item = mock(Plate.class);
        when(chef.getItem()).thenReturn(item);
        when(((Plate)item).hasContent()).thenReturn(false);

        deliverySpot.interact(chef, kitchen);
        // no order matches, so drop is never called
        verify(chef, times(2)).getItem();
        verify(kitchen, times(0)).getActiveOrders();
    }

    @Test
    void testInteract_chefIsHoldingPlate_noOrderMatch() {
        when(chef.isHoldingSomething()).thenReturn(true);
        this.item = mock(Plate.class);
        when(chef.getItem()).thenReturn(item);
        when(((Plate)item).hasContent()).thenReturn(true);

        Dish chefDish = mock(Dish.class);
        when(((Plate)item).getContent()).thenReturn(chefDish);

        Order order1 = mock(Order.class);
        Order order2 = mock(Order.class);
        Order order3 = mock(Order.class);
        List<Order> mockedOrders = new ArrayList<>(Arrays.asList(order1, order2, order3));

        when(kitchen.getActiveOrders()).thenReturn(mockedOrders);

        Dish orderDish = mock(Dish.class);

        when(order1.getDesiredDish()).thenReturn(orderDish);
        when(order2.getDesiredDish()).thenReturn(orderDish);
        when(order3.getDesiredDish()).thenReturn(orderDish);

        when(chefDish.getType()).thenReturn(POTATO_SOUP);
        when(orderDish.getType()).thenReturn(CARROT_SOUP); // will not verify branch condition

        deliverySpot.interact(chef, kitchen);
        verify(chef, times(2)).getItem();
        verify(kitchen, times(1)).getActiveOrders();
        verify(chef, times(0)).drop();
        verify(kitchen, times(0)).removeActiveOrder(any(Order.class));
    }

    @Test
    void testInteract_chefIsHoldingPlate_orderMatch() {
        when(chef.isHoldingSomething()).thenReturn(true);
        this.item = mock(Plate.class);
        when(chef.getItem()).thenReturn(item);
        when(((Plate)item).hasContent()).thenReturn(true);

        Dish chefDish = mock(Dish.class);
        when(((Plate)item).getContent()).thenReturn(chefDish);

        Order order1 = mock(Order.class);
        Order order2 = mock(Order.class);
        Order order3 = mock(Order.class);
        List<Order> mockedOrders = new ArrayList<>(Arrays.asList(order1, order2, order3));

        when(kitchen.getActiveOrders()).thenReturn(mockedOrders);

        Dish orderDish1 = mock(Dish.class);
        Dish orderDish2 = chefDish; // make order2 match
        Dish orderDish3 = mock(Dish.class);

        when(order1.getDesiredDish()).thenReturn(orderDish1);
        when(order2.getDesiredDish()).thenReturn(orderDish2);
        when(order3.getDesiredDish()).thenReturn(orderDish3);

        deliverySpot.interact(chef, kitchen);
        verify(chef, times(2)).getItem();
        verify(kitchen, times(1)).getActiveOrders();
        verify(chef, times(1)).drop();
        verify(kitchen, times(1)).removeActiveOrder(order2); // only order2 matches
    }
}
