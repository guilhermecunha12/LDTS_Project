package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.grabable.food.Carrot;
import getcooked.viewer.game.CarrotDispenserViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarrotDispenserTest {

    private CarrotDispenser carrotDispenser;

    @BeforeEach
    void setUp() {
        this.carrotDispenser = new CarrotDispenser(0, 0);
    }

    @Test
    void testGetViewer() {
        assertInstanceOf(CarrotDispenserViewer.class, carrotDispenser.getViewer());
    }

    @Test
    void testCreateItem() {
        assertInstanceOf(Carrot.class, carrotDispenser.createItem(0, 0));
    }
}