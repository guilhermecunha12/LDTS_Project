package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.grabable.food.Beef;
import getcooked.viewer.game.BeefDispenserViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class BeefDispenserTest {

    private BeefDispenser beefDispenser;

    @BeforeEach
    void setUp() {
        this.beefDispenser = new BeefDispenser(0, 0);
    }

    @Test
    void testGetViewer() {
        assertInstanceOf(BeefDispenserViewer.class, beefDispenser.getViewer());
    }

    @Test
    void testCreateItem() {
        assertInstanceOf(Beef.class, beefDispenser.createItem(0, 0));
    }
}