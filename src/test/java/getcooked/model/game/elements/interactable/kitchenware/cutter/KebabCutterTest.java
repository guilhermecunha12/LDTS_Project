package getcooked.model.game.elements.interactable.kitchenware.cutter;

import getcooked.model.game.dish.Dish;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.viewer.game.KebabCutterViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KebabCutterTest {

    private KebabCutter kebabCutter;
    private Chef chef;
    private Plate plate;

    @BeforeEach
    void setUp() {
        this.kebabCutter = new KebabCutter(0, 0);
        this.chef = mock(Chef.class);
        this.plate = mock(Plate.class);
    }

    @Test
    void testGetViewer() {
        assertInstanceOf(KebabCutterViewer.class, kebabCutter.getViewer());
    }

    @Test
    void testFinish() {
        kebabCutter.finish();
        assertInstanceOf(KebabCutter.KebabDummy.class, kebabCutter.getContent());
    }

    @Test
    void testInteractFinishedChefHolding() {
        kebabCutter.finish();
        when(chef.isHoldingSomething()).thenReturn(true);
        Grabable grabable = mock(Grabable.class);
        when(chef.getItem()).thenReturn(grabable);
        kebabCutter.interact(chef);
        verify(grabable, times(1)).interactWithKitchenware(kebabCutter, chef);
    }

    @Test
    void testInteractFinishedChefNotHoldingEmpty() {
        kebabCutter.finish();
        when(chef.isHoldingSomething()).thenReturn(false);
        kebabCutter.interact(chef);
        Grabable grabable = mock(Grabable.class);
        when(chef.getItem()).thenReturn(grabable);
        verify(grabable, never()).interactWithKitchenware(kebabCutter, chef);
    }

    @Test
    void testInteractNotFinishedEmpty() {
        kebabCutter.interact(chef);
        assertFalse(kebabCutter.isFinished());
        verify(chef, never()).pickUp(any());
    }

    @Test
    void testAcceptPlateInChefPlateHasContent() {
        when(plate.hasContent()).thenReturn(true);
        kebabCutter.acceptPlateInChef(plate, chef);
        verify(plate, never()).setContent(any());
    }

    @Test
    void testAcceptPlateInChefPlateEmpty() {
        when(plate.hasContent()).thenReturn(false);
        kebabCutter.finish();
        Dish dish = new Dish(kebabCutter.getContent().getCookedDishType());
        kebabCutter.acceptPlateInChef(plate, chef);
        verify(plate, times(1)).setContent(dish);
    }
}