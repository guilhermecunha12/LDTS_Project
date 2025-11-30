package getcooked.model.game.elements.grabable;

import getcooked.model.game.dish.Dish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class PlateTest {
    private Plate plate;

    @BeforeEach
    void testPlateCreation(){
        plate = new Plate(5,10);
    }

    @Test
    void testPutNull(){
        assertFalse(plate.put(null));
    }

    @Test
    void testPutWhenHasContent(){
        Dish dish = mock(Dish.class);
        plate.put(dish);
        assertTrue(plate.hasContent());
        assertFalse(plate.put(dish));
    }

    @Test
    void testPutWhenEmpty(){
        Dish dish = mock(Dish.class);
        assertTrue(plate.put(dish));
        assertEquals(dish, plate.getContent());
        assertTrue(plate.hasContent());
    }
}
