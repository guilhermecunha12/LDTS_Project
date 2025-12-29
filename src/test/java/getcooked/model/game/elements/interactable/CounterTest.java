package getcooked.model.game.elements.interactable;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.viewer.game.CounterViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CounterTest {
    private Counter counter;
    private Grabable item;
    private Chef chef;

    @BeforeEach
    void setUp() {
        this.counter = new Counter(0, 0, 'u');
        this.item = mock(Grabable.class);
        this.chef = mock(Chef.class);
    }

    @Test
    void testInteract_ChefIsHoldingSomething_CounterIsEmpty() {
        this.counter.setContent(null);

        when(chef.isHoldingSomething()).thenReturn(true);
        when(chef.drop()).thenReturn(item);

        counter.interact(chef);
        assertEquals(item, counter.getContent());
    }

    @Test
    void testInteract_ChefIsHoldingSomething_CounterHasItem() {
        this.counter.setContent(item);

        when(chef.isHoldingSomething()).thenReturn(true);

        counter.interact(chef);
        verify(chef, times(0)).drop();
        assertEquals(item, counter.getContent());
    }

    @Test
    void testInteract_ChefIsNotHoldingSomething_CounterHasItem() {
        this.counter.setContent(item);

        when(chef.isHoldingSomething()).thenReturn(false);

        counter.interact(chef);
        verify(chef, times(1)).pickUp(item);
        assertNull(counter.getContent());
    }

    @Test
    void testInteract_ChefIsNotHoldingSomething_CounterIsEmpty() {
        this.counter.setContent(null);

        when(chef.isHoldingSomething()).thenReturn(false);

        counter.interact(chef);
        verify(chef, times(0)).pickUp(item);
        assertNull(counter.getContent());
    }

    @Test
    void testGetType() {
        assertEquals('u', counter.getType());
    }

    @Test
    void testGetViewer() {
        assertInstanceOf(CounterViewer.class, this.counter.getViewer());
    }
}