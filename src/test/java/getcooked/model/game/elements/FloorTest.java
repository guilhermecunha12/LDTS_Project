package getcooked.model.game.elements;

import getcooked.viewer.game.FloorViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class FloorTest {
    private Floor floor;

    @BeforeEach
    void setUp() {
        this.floor = new Floor(0, 0);
    }

    @Test
    void testIsBlocking() {
        assertFalse(this.floor.isBlocking());
    }

    @Test
    void testInteract() {
        // Since the default implementation is ignored, we just ensure no exceptions are thrown
        Chef chef = mock(Chef.class);
        assertDoesNotThrow(() -> floor.interact(chef));
    }

    @Test
    void testGetViewer() {
        assertTrue(this.floor.getViewer() instanceof FloorViewer);
    }
}