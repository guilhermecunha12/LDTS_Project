package getcooked.model.game.elements.grabable.food;

import getcooked.model.game.dish.DishType;
import getcooked.viewer.game.SaladViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaladTest {

    private Salad salad;

    @BeforeEach
    void setUp() {
        this.salad = new Salad(0, 0);
    }

    @Test
    void getCookedDishType() {
        assertEquals(DishType.SALAD, salad.getCookedDishType());
    }

    @Test
    void getViewer() {
        assertInstanceOf(SaladViewer.class, salad.getViewer());
    }
}