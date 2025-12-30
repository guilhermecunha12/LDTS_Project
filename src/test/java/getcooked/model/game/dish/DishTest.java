package getcooked.model.game.dish;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DishTest {
    private Dish dish;

    @BeforeEach
    void setUp() {
        this.dish= new Dish(DishType.POTATO_SOUP);
    }

    @Test
    void testEqualsSameObject(){
        assertTrue(this.dish.equals(dish));
    }

    @Test
    void testEqualsNull(){
        assertFalse(this.dish.equals(null));
    }

    @Test
    void testEqualsDifferentClass(){
        assertFalse(dish.equals(new Object()));
    }

    @Test
    void testEqualsSameContent(){
        Dish dish1 = new Dish(DishType.POTATO_SOUP);
        assertTrue(this.dish.equals(dish1));
    }

    @Test
    void testEqualsDifferentContent(){
        Dish dish1 = new Dish(DishType.SALAD);
        assertFalse(this.dish.equals(dish1));
    }
}
