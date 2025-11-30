package getcooked.model.game;

import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    private Order order;

    @BeforeEach
    void setUp() {
        Dish dish = new Dish(DishType.POTATO_SOUP);
        order = new Order(dish, 30);
    }

    @Test
    void testGetDesiredDish() {
        assertEquals(DishType.POTATO_SOUP, order.getDesiredDish().getType());
    }

    @Test
    void testGetRemainingTime() {
        assertEquals(30, order.getRemainingTime());
    }

    @Test
    void testTick() {
        order.tick();
        assertEquals(29, order.getRemainingTime());

        for (int i = 0; i < 29; i++) { // get remaining time to 0
            order.tick();
        }
        order.tick(); // extra tick to ensure remaining time doesn't go negative
        assertEquals(0, order.getRemainingTime());
    }

    @Test
    void testIsExpired() {
        assertFalse(order.isExpired());
        for (int i = 0; i < 30; i++) { // tick 30 times
            order.tick();
        }
        assertTrue(order.isExpired());
    }
}
