package getcooked.model.game.elements.interactable;

import getcooked.model.game.Order;
import getcooked.model.game.OrderManager;
import getcooked.model.game.dish.Dish;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.viewer.game.DeliverySpotViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static getcooked.model.game.dish.DishType.CARROT_SOUP;
import static getcooked.model.game.dish.DishType.POTATO_SOUP;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

class DeliverySpotTest {
    private DeliverySpot deliverySpot;
    private Chef chef;
    private Grabable item;
    private OrderManager orderManager;

    @BeforeEach
    void setUp() {
        this.deliverySpot = new DeliverySpot(0, 0);
        this.orderManager = mock(OrderManager.class);
        this.deliverySpot.setOrderManager(orderManager);
        this.chef = mock(Chef.class);
    }

    @Test
    void testInteract_chefIsNotHoldingAnything() {
        this.deliverySpot.setOrderManager(orderManager); // more branch coverage

        when(chef.isHoldingSomething()).thenReturn(false);

        deliverySpot.interact(chef);
        // does not satisfy the branch condition (short circuit evaluation)
        verify(chef, never()).getItem();
    }

    @Test
    void testInteract_chefIsHoldingNonPlate() {
        when(chef.isHoldingSomething()).thenReturn(true);
        this.item = mock(Food.class); // every Grabable that is not a Plate
        when(chef.getItem()).thenReturn(item);

        deliverySpot.interact(chef);
        // does not satisfy the branch condition
        verify(item, times(1)).toDeliver();
        verify(orderManager, never()).getActiveOrders();
    }

    @Test
    void testInteract_chefIsHoldingEmptyPlate() {
        when(chef.isHoldingSomething()).thenReturn(true);
        this.item = mock(Plate.class);
        when(chef.getItem()).thenReturn(item);
        when(((Plate)item).hasContent()).thenReturn(false);

        deliverySpot.interact(chef);

        verify(chef, times(1)).getItem();
        verify(item, times(1)).toDeliver();
        verify(orderManager, times(0)).getActiveOrders();
    }

    @Test
    void testInteract_chefIsHoldingPlate_noOrderMatch() {
        when(chef.isHoldingSomething()).thenReturn(true);
        this.item = mock(Plate.class);
        when(chef.getItem()).thenReturn(item);
        when(((Plate)item).hasContent()).thenReturn(true);

        Dish chefDish = mock(Dish.class);
        when(item.toDeliver()).thenReturn(chefDish);

        Order order1 = mock(Order.class);
        Order order2 = mock(Order.class);
        Order order3 = mock(Order.class);
        List<Order> mockedOrders = new ArrayList<>(Arrays.asList(order1, order2, order3));

        when(orderManager.getActiveOrders()).thenReturn(mockedOrders);

        Dish orderDish = mock(Dish.class);

        when(order1.getDesiredDish()).thenReturn(orderDish);
        when(order2.getDesiredDish()).thenReturn(orderDish);
        when(order3.getDesiredDish()).thenReturn(orderDish);

        when(chefDish.type()).thenReturn(POTATO_SOUP);
        when(orderDish.type()).thenReturn(CARROT_SOUP); // will not verify branch condition

        deliverySpot.interact(chef);
        verify(chef, times(1)).getItem();
        verify(item, times(1)).toDeliver();
        verify(orderManager, times(1)).getActiveOrders();
        verify(order1, times(1)).getDesiredDish();
        verify(order2, times(1)).getDesiredDish();
        verify(order3, times(1)).getDesiredDish();
        verify(chef, times(0)).drop();
        verify(orderManager, times(0)).removeActiveOrder(any(Order.class));
    }

    @Test
    void testInteract_chefIsHoldingPlate_orderMatch() {
        when(chef.isHoldingSomething()).thenReturn(true);
        this.item = mock(Plate.class);
        when(chef.getItem()).thenReturn(item);
        when(((Plate)item).hasContent()).thenReturn(true);

        Dish chefDish = mock(Dish.class);
        when(item.toDeliver()).thenReturn(chefDish);

        Order order1 = mock(Order.class);
        Order order2 = mock(Order.class);
        Order order3 = mock(Order.class);
        List<Order> mockedOrders = new ArrayList<>(Arrays.asList(order1, order2, order3));

        when(orderManager.getActiveOrders()).thenReturn(mockedOrders);

        Dish orderDish1 = mock(Dish.class);
        Dish orderDish3 = mock(Dish.class);

        when(order1.getDesiredDish()).thenReturn(orderDish1);
        when(order2.getDesiredDish()).thenReturn(chefDish); // make order2 match
        when(order3.getDesiredDish()).thenReturn(orderDish3);

        deliverySpot.interact(chef);
        verify(chef, times(1)).getItem();
        verify(item, times(1)).toDeliver();
        verify(orderManager, times(1)).getActiveOrders();
        verify(order1, times(1)).getDesiredDish();
        verify(order2, times(1)).getDesiredDish();
        verify(order3, never()).getDesiredDish();
        verify(chef, times(1)).drop();
        verify(orderManager, times(1)).removeActiveOrder(order2); // only order2
        verify(orderManager, times(1)).incrementOrdersDelivered();
    }

    @Test
    void testInteract_nullOrderManager() {
        deliverySpot = new DeliverySpot(0, 0); // without setting orderManager

        deliverySpot.interact(chef);
        verify(chef, never()).isHoldingSomething();
    }

    @Test
    void testGetViewer() {
        assertInstanceOf(DeliverySpotViewer.class, deliverySpot.getViewer());
    }
}
