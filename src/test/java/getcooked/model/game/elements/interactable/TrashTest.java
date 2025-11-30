package getcooked.model.game.elements.interactable;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TrashTest {
    private Trash trash;
    private Chef chef;
    private Grabable grabable;

    @BeforeEach
    void setUp() {
        this.trash = new Trash(0, 0);
        this.chef = mock(Chef.class);
        this.grabable = mock(Grabable.class);
    }

    @Test
    void testInteract_ChefIsHoldingSomething() {
        when(chef.drop()).thenReturn(grabable);

        trash.interact(chef);

        verify(chef, times(1)).drop();
        verify(grabable, times(1)).setHolder(null);
    }

    @Test
    void testInteract_ChefIsNotHoldingSomething() {
        when(chef.drop()).thenReturn(null);

        trash.interact(chef);

        verify(chef, times(1)).drop();
        // verify that no method of the Grabable mock was invoked
        verifyNoInteractions(grabable);
    }
}
