package getcooked.model.game;

import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.interactable.Interactable;
import getcooked.model.game.elements.interactable.DeliverySpot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KitchenBuilderTest {
    protected int level;
    protected int lives;
    protected List<DishType> availableDishTypes;
    private int numPlayers;
    private int ordersDelivered;
    protected List<DeliverySpot> deliverySpots;
    private KitchenBuilder kitchenBuilder;

    @BeforeEach
    void setUp() {
        this.level = 10;
        this.lives = 3;
        this.numPlayers = 2;
        this.availableDishTypes = new ArrayList<>();
        this.ordersDelivered = 0;
        this.deliverySpots = new ArrayList<>();
        this.kitchenBuilder = new KitchenBuilder(level, numPlayers, lives, ordersDelivered) {
            @Override
            protected int getWidth() {
                return 100;
            }

            @Override
            protected int getHeight() {
                return 100;
            }

            @Override
            protected List<Interactable> createInteractables() {
                return new ArrayList<>();
            }
        };
    }

    @Test
    void testAddDishTypes(){
        DishType dishType1 = DishType.SALAD;
        DishType dishType2 = DishType.SODA;
        kitchenBuilder.addAvailableDishType(dishType1);
        kitchenBuilder.addAvailableDishType(dishType2);
        assertFalse(kitchenBuilder.availableDishTypes.contains(DishType.POTATO_SOUP));
        assertTrue(kitchenBuilder.availableDishTypes.contains(dishType2));
    }

    @Test
    void testCreateKitchen2players(){
        DishType dishType1 = DishType.SALAD;
        kitchenBuilder.addAvailableDishType(dishType1);
        DeliverySpot deliverySpot = mock(DeliverySpot.class);
        kitchenBuilder.deliverySpots.add(deliverySpot);
        Kitchen kitchen = kitchenBuilder.createKitchen();
        assertEquals(kitchen.getLevel(), level);
        assertEquals(kitchen.getNumPlayers(), numPlayers);
        assertEquals(kitchen.getLives(), lives);
        assertFalse(kitchenBuilder.deliverySpots.isEmpty());
    }

    @Test
    void testCreateKitchen1player(){
        kitchenBuilder = new KitchenBuilder(level, 1, lives, ordersDelivered) {
            @Override
            protected int getWidth() {
                return 100;
            }

            @Override
            protected int getHeight() {
                return 100;
            }

            @Override
            protected List<Interactable> createInteractables() {
                return new ArrayList<>();
            }
        };
        DishType dishType1 = DishType.SALAD;
        kitchenBuilder.addAvailableDishType(dishType1);
        Kitchen kitchen = kitchenBuilder.createKitchen();
        assertEquals(1, kitchen.getNumPlayers());
    }
}
