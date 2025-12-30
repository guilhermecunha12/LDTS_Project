package getcooked.model.game.elements.grabable.food;

import getcooked.model.game.dish.DishType;
import getcooked.viewer.game.PotatoViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PotatoTest {

    private Potato potato;

    @BeforeEach
    void setUp() {
        this.potato = new Potato(0, 0);
    }

    @Test
    void testGetCookedDishTypeWhenChopped() {
        potato.setState(FoodState.CHOPPED);
        assertEquals(DishType.POTATO_SOUP, potato.getCookedDishType());
    }

    @Test
    void testGetCookedDishTypeWhenNotChopped() {
        assertNull(potato.getCookedDishType());
    }


    @Test
    void testGetViewer() {
        assertInstanceOf(PotatoViewer.class, potato.getViewer());
    }
}