package getcooked.model.game.elements.interactable.dispenser;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.viewer.game.ElementViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DispenserTest {

    private Dispenser dispenser;
    private Chef chef;

    @BeforeEach
    void setUp() {
        this.dispenser = new Dispenser(0, 0) {
            @Override
            protected Grabable createItem(int x, int y) {
                return null;
            }

            @Override
            public ElementViewer getViewer() {
                return null;
            }
        };
        this.chef = mock(Chef.class);
    }

    @Test
    void testInteractHolding() {
        when(chef.isHoldingSomething()).thenReturn(true);
        dispenser.interact(chef);
        verify(chef, never()).pickUp(any());
    }

    @Test
    void testInteractNotHolding() {
        when(chef.isHoldingSomething()).thenReturn(false);
        dispenser.interact(chef);
        verify(chef, times(1)).pickUp(any());
    }

    @Test
    void testIsBlocking() {
        assertTrue(dispenser.isBlocking());
    }
}