package getcooked.model.game.elements.grabable.food;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarrotTest {
    private Carrot carrot;

    @BeforeEach
    void setUp() {
        carrot = new Carrot(0, 0);
    }

    @Test
    void testChopFromRawToChopped() {
        assertSame(Food.FoodState.RAW,carrot.getState());
        assertTrue(carrot.chop());
        assertSame(Food.FoodState.CHOPPED,carrot.getState());
        assertFalse(carrot.chop());
    }
}