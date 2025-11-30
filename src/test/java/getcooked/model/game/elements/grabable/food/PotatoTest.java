package getcooked.model.game.elements.grabable.food;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PotatoTest {
    private Potato potato;

    @BeforeEach
    void setUp() {
        potato = new Potato(0, 0);
    }

    @Test
    void testChopFromRawToChopped() {
        assertSame(Food.FoodState.RAW,potato.getState());
        assertTrue(potato.chop());
        assertSame(Food.FoodState.CHOPPED,potato.getState());
        assertFalse(potato.chop());
    }
}
