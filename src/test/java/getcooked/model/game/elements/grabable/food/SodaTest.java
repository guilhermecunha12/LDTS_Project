package getcooked.model.game.elements.grabable.food;

import getcooked.model.game.dish.DishType;
import getcooked.viewer.game.SodaViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SodaTest {

    private Soda soda;

    @BeforeEach
    void setUp() {
        this.soda = new Soda(0, 0);
    }

    @Test
    void testGetCookedDishType() {
        assertEquals(DishType.SODA, soda.getCookedDishType());
    }

    @Test
    void testGetViewer() {
        assertInstanceOf(SodaViewer.class, soda.getViewer());
    }
}