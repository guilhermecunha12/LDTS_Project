package getcooked.model.game.elements.interactable.dispenser;


import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class DispenserTest {
    private Dispenser dispenser;
    private Chef chef;

    @BeforeEach
    void setUp() {

        dispenser = new Dispenser(1, 2) {
            @Override
            protected Grabable createItem(int x, int y) {
                // Can return any grabable so we mock it
                return mock(Grabable.class);
            }
        };
        chef = mock(Chef.class);
    }

    @Test
    void chefCanPickUpItemWhenNotHoldingSomething() {
        // chef is not holding anything
        when(chef.isHoldingSomething()).thenReturn(false);
        when(chef.getX()).thenReturn(1);
        when(chef.getY()).thenReturn(2);
        dispenser.interact(chef);
        verify(chef).pickUp(any(Grabable.class));
    }

    @Test
    void chefCanPickUpItemWhenHoldingSomething() {
        // chef is holding something
        when(chef.isHoldingSomething()).thenReturn(true);
        when(chef.getX()).thenReturn(1);
        when(chef.getY()).thenReturn(2);
        dispenser.interact(chef);
        verify(chef, never()).pickUp(any(Grabable.class));
    }
}
