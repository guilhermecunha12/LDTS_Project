package getcooked.model.game;

import getcooked.model.game.dish.Dish;
import getcooked.model.game.dish.DishType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order order;
    private Dish dish;

    @BeforeEach
    void setUp() {
        this.dish = new Dish(DishType.POTATO_SOUP);
        this.order = new Order(dish, 30);
    }

    @Test
    void testGetDesiredDish() {
        assertEquals(DishType.POTATO_SOUP, order.getDesiredDish().type());
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


    @Test
    void testEqualsSameObject(){
        assertTrue(order.equals(order));
    }

    @Test
    void testEqualsNull(){
        assertFalse(order.equals(null));
    }

    @Test
    void testEqualsDifferentClass(){
        assertFalse(order.equals(new Object()));
    }

    @Test
    void testEqualsSameContent(){
        Order order1 = new Order(dish, 30);
        assertTrue(order.equals(order1));
    }


    @Test
    void testHashCode(){
        Order order1 = new Order(dish, 30);
        assertEquals(order.hashCode(), order1.hashCode());
    }
}
