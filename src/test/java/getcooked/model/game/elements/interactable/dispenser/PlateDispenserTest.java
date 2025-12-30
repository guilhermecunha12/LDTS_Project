package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.grabable.Plate;
import getcooked.viewer.game.PlateDispenserViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlateDispenserTest {

    private PlateDispenser plateDispenser;

    @BeforeEach
    void setUp() {
        this.plateDispenser = new PlateDispenser(0, 0);
    }

    @Test
    void testGetViewer() {
        assertInstanceOf(PlateDispenserViewer.class, plateDispenser.getViewer());
    }

    @Test
    void testCreateItem() {
        assertInstanceOf(Plate.class, plateDispenser.createItem(0, 0));
    }
}