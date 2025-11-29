package getcooked.model.game.elements.interactable;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CounterTest {
    private Counter counter;
    private Grabable item;
    private Chef chef;


    @BeforeEach
    void setUp() {
        this.counter = new Counter(0, 0);
        this.item = mock(Grabable.class);
        this.chef = mock(Chef.class);
    }

    @Test
    void testInteract_ChefIsHoldingSomething_CounterIsEmpty() {
        this.counter.setItem(null);

        when(chef.isHoldingSomething()).thenReturn(true);
        when(chef.drop()).thenReturn(item);

        counter.interact(chef);
        verify(item, times(1)).placeAt(counter.getPosition());
        assertEquals(item, counter.getItem());
    }

    @Test
    void testInteract_ChefIsHoldingSomething_CounterHasItem() {
        this.counter.setItem(item);

        when(chef.isHoldingSomething()).thenReturn(true);

        counter.interact(chef);
        verify(chef, times(0)).drop();
        assertEquals(item, counter.getItem());
    }

    @Test
    void testInteract_ChefIsNotHoldingSomething_CounterHasItem() {
        this.counter.setItem(item);

        when(chef.isHoldingSomething()).thenReturn(false);
        when(chef.pickUp(item)).thenReturn(true);

        counter.interact(chef);
        verify(chef, times(1)).pickUp(item);
        assertEquals(null, counter.getItem());
    }

    @Test
    void testInteract_ChefIsNotHoldingSomething_CounterIsEmpty() {
        this.counter.setItem(null);

        when(chef.isHoldingSomething()).thenReturn(false);

        counter.interact(chef);
        verify(chef, times(0)).pickUp(item);
        assertEquals(null, counter.getItem());
    }
}