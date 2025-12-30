package getcooked.model.game.elements.interactable.kitchenware.cutter;

import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.food.*;
import getcooked.viewer.game.CounterCutterViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CounterCutterTest {

    private CounterCutter counterCutter;
    private Chef chef;
    private Soda soda;


    @BeforeEach
    void setUp() {
        this.counterCutter = new CounterCutter(0, 0);
        this.soda = mock(Soda.class);
        this.chef = mock(Chef.class);
    }

    @Test
    void testGetViewer() {
        assertInstanceOf(CounterCutterViewer.class, counterCutter.getViewer());
    }

    @Test
    void testFinish() {
        Potato potato = new Potato(0, 0);
        counterCutter.addContent(potato);
        counterCutter.start();
        counterCutter.finish();
        assertEquals(FoodState.CHOPPED, counterCutter.getContent().getState());
        assertTrue(counterCutter.isFinished());
    }

    @Test
    void testChopValidContent() {
        counterCutter.addContent(new Potato(0, 0));
        counterCutter.chop();
        assertEquals(FoodState.CHOPPED, counterCutter.getContent().getState());
    }

    @Test
    void testChopInvalidContent() {
        counterCutter.addContent(new Potato(0, 0));
        counterCutter.getContent().setState(FoodState.CHOPPED);
        counterCutter.chop();
        assertEquals(FoodState.CHOPPED, counterCutter.getContent().getState());
    }

    @Test
    void testAddContent() {
        Potato potato = new Potato(0, 0);
        counterCutter.addContent(potato);
        assertEquals(potato, counterCutter.getContent());
    }

    @Test
    void testAcceptsALlowedFoodRow() {
        Potato potato = new Potato(0, 0);
        assertTrue(counterCutter.accepts(potato));
    }

    @Test
    void testAcceptsAllowedFoodNotRaw() {
        Potato potato = new Potato(0, 0);
        potato.setState(FoodState.CHOPPED);
        assertFalse(counterCutter.accepts(potato));
    }

    @Test
    void testNotAllowedFood() {
        assertFalse(counterCutter.accepts(soda));
    }

    @Test
    void testCanAddValidFoodEmpty() {
        Potato potato = new Potato(0, 0);
        assertTrue(counterCutter.canAdd(potato));
    }

    @Test
    void testCanAddInvalidFoodEmpty() {
        assertFalse(counterCutter.canAdd(soda));
    }

    @Test
    void testCanAddNotEmpty() {
        Potato potato = new Potato(0, 0);
        counterCutter.addContent(potato);
        Carrot carrot = new Carrot(0, 0);
        assertFalse(counterCutter.canAdd(carrot));
    }


    @Test
    void testInteractChefIsHolding() {
        Potato potato = new Potato(0, 0);
        when(chef.isHoldingSomething()).thenReturn(true);
        when(chef.getItem()).thenReturn(potato);
        counterCutter.interact(chef);
        assertEquals(potato, counterCutter.getContent());
        verify(chef).drop();
        assertTrue(counterCutter.isExecuting());
    }

    @Test
    void testInteractChefIsNotHoldingCutterIsFinished() {
        Potato potato = new Potato(0, 0);
        counterCutter.addContent(potato);
        counterCutter.finish();
        when(chef.isHoldingSomething()).thenReturn(false);
        counterCutter.interact(chef);
        verify(chef).pickUp(potato);
        assertTrue(counterCutter.isEmpty());
    }

    @Test
    void testInteractChefIsNotHoldingCutterIsExecuting() {
        Potato potato = new Potato(0, 0);
        counterCutter.addContent(potato);
        counterCutter.start();
        when(chef.isHoldingSomething()).thenReturn(false);
        counterCutter.interact(chef);
        assertTrue(counterCutter.isExecuting());
        assertFalse(counterCutter.isEmpty());
        verify(chef, never()).pickUp(any());
    }

    @Test
    void testInteractChefIsNotHoldingCutterIsIdle() {
        when(chef.isHoldingSomething()).thenReturn(false);
        counterCutter.interact(chef);
        assertTrue(counterCutter.isEmpty());
        verify(chef, never()).pickUp(any());
    }

    @Test
    void testAcceptFoodInChefCanAdd() {
        Potato potato = new Potato(0, 0);
        when(chef.isHoldingSomething()).thenReturn(true);
        counterCutter.acceptFoodInChef(potato, chef);
        assertEquals(potato, counterCutter.getContent());
        verify(chef).drop();
        assertTrue(counterCutter.isExecuting());
    }

    @Test
    void testAcceptFoodInChefCanNotAdd() {
        Potato potato = new Potato(0, 0);
        counterCutter.addContent(potato);
        when(chef.isHoldingSomething()).thenReturn(true);
        counterCutter.acceptFoodInChef(potato, chef);
        assertEquals(potato, counterCutter.getContent());
        verify(chef, never()).drop();
    }
}