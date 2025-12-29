package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.grabable.food.Salad;
import getcooked.viewer.game.SaladDispenserViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaladDispenserTest {

    private SaladDispenser saladDispenser;

    @BeforeEach
    void setUp() {
        this.saladDispenser = new SaladDispenser(0, 0);
    }

    @Test
    void testGetViewer() {
        assertInstanceOf(SaladDispenserViewer.class, saladDispenser.getViewer());
    }

    @Test
    void testCreateItem() {
        assertInstanceOf(Salad.class, saladDispenser.createItem(0, 0));
    }
}