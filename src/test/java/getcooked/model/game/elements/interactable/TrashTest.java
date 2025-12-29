package getcooked.model.game.elements.interactable;

import getcooked.model.game.elements.Chef;
import getcooked.viewer.game.TrashViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

class TrashTest {
    private Trash trash;
    private Chef chef;

    @BeforeEach
    void setUp() {
        this.trash = new Trash(0, 0);
        this.chef = mock(Chef.class);
    }

    @Test
    void testInteract_ChefIsHoldingSomething() {
        when(chef.isHoldingSomething()).thenReturn(true);
        trash.interact(chef);

        verify(chef, times(1)).drop();
    }

    @Test
    void testInteract_ChefIsNotHoldingSomething() {
        when(chef.isHoldingSomething()).thenReturn(false);

        trash.interact(chef);

        verify(chef, never()).drop();
    }

    @Test
    void testGetViewer() {
        assertInstanceOf(TrashViewer.class, trash.getViewer());
    }
}
