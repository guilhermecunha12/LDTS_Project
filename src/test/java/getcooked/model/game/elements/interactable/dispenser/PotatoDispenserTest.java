package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.food.Potato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PotatoDispenserTest  {
    private PotatoDispenser dispenser;
    @BeforeEach
    void setup() {
        this.dispenser = new PotatoDispenser(5, 10);
    }

    @Test
    void testcreateItem() {
        Grabable item = dispenser.createItem(5, 10);
        assertNotNull(item);
        assertInstanceOf(Potato.class, item);
        Potato potato = (Potato) item;
        assertEquals(5,  potato.getX());
        assertEquals(10, potato.getY());
    }

}
