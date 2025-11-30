package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

 class PlateDispenserTest {
    private PlateDispenser plateDispenser;

    @BeforeEach
    void setUp() {
        plateDispenser = new PlateDispenser(3, 7);
    }

    @Test
    void testCreateItem() {
        Grabable item = plateDispenser.createItem(3, 7);
        assertNotNull(item);
        assertInstanceOf(Plate.class, item);
        // Can cast after the instance check
        Plate plate = (Plate) item;
        assertEquals(3, plate.getX());
        assertEquals(7, plate.getY());
    }
}
