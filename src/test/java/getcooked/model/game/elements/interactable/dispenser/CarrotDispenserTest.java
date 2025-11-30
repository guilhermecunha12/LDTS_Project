package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.food.Carrot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarrotDispenserTest {
    private CarrotDispenser dispenser;
    @BeforeEach
    void setup() {
        this.dispenser = new CarrotDispenser(5, 10);
    }
    @Test
    void testcreateItem() {
        Grabable item = dispenser.createItem(5, 10);
        assertNotNull(item);
        assertInstanceOf(Carrot.class, item);
        Carrot carrot = (Carrot) item;
        assertEquals(5,  carrot.getX());
        assertEquals(10, carrot.getY());
    }
}
