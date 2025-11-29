package getcooked.model.game.elements;

import getcooked.model.game.elements.grabable.Grabable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ChefTest {
    private Grabable grabable;
    private Chef chef;

    @BeforeEach
    void setUp() {
        chef = new Chef(10, 10);
        grabable = mock(Grabable.class);
    }

    @Test
    void pickUp(){
        //try to pick up when not holding anything
        assertTrue(chef.pickUp(grabable));
        assertTrue(chef.isHoldingSomething());
        assertEquals(grabable, chef.getItem());
        verify(grabable).setHolder(chef);
        //try to pick up another item
        Grabable other = mock(Grabable.class);
        assertFalse(chef.pickUp(other));
        assertEquals(grabable, chef.getItem());
        verify(other,times(0)).setHolder(chef);
    }

    @Test
    void drop() {
        //drop when not holding anything
        assertNull(chef.drop());
        assertFalse(chef.isHoldingSomething());
        //drop when holding something
        chef.pickUp(grabable);
        Grabable dropped = chef.drop();
        assertEquals(grabable, dropped);
        assertFalse(chef.isHoldingSomething());
        verify(grabable).setHolder(null);
    }
}
