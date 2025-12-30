package getcooked.model.game.elements.grabable.food;

import getcooked.model.game.dish.DishType;
import getcooked.viewer.game.CarrotViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarrotTest {

    private Carrot carrot;

    @BeforeEach
    void setUp() {
        this.carrot = new Carrot(0, 0);
    }

    @Test
    void testGetCookedDishTypeWhenChopped() {
        carrot.setState(FoodState.CHOPPED);
        assertEquals(DishType.CARROT_SOUP, carrot.getCookedDishType());
    }

    @Test
    void testGetCookedDishTypeWhenNotChopped() {
        assertNull( carrot.getCookedDishType());
    }


    @Test
    void testGetViewer() {
        assertInstanceOf(CarrotViewer.class, carrot.getViewer());
    }
}