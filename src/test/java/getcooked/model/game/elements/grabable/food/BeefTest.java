package getcooked.model.game.elements.grabable.food;

import getcooked.model.game.dish.DishType;
import getcooked.viewer.game.BeefViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeefTest {
    private Beef beef;

    @BeforeEach
    void setUp() {
        beef = new Beef(0, 0);
    }

    @Test
    void testGetCookedDishTypeWhenRaw() {
        assertEquals(DishType.STEAK, beef.getCookedDishType());
    }

    @Test
    void testGetCookedDishTypeWhenNotRaw() {
        beef.setState(FoodState.CHOPPED);
        assertNull(beef.getCookedDishType());
    }

    @Test
    void testGetViewer() {
        assertInstanceOf(BeefViewer.class, beef.getViewer());
    }
}
