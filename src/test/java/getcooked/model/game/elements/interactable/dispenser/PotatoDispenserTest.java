package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.grabable.food.Potato;
import getcooked.viewer.game.PotatoDispenserViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class PotatoDispenserTest  {
    private PotatoDispenser potatoDispenser;
    @BeforeEach
    void setup() {
        this.potatoDispenser = new PotatoDispenser(0, 0);
    }

    @Test
    void testGetViewer() {
        assertInstanceOf(PotatoDispenserViewer.class, potatoDispenser.getViewer());
    }

    @Test
    void testCreateItem() {
        assertInstanceOf(Potato.class, potatoDispenser.createItem(0, 0));
    }

}
