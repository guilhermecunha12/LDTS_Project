package getcooked.model.game.elements.interactable.cookware;

import getcooked.model.game.dish.Dish;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Grabable;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Food;
import getcooked.model.game.elements.interactable.kitchenware.behaviour.ExecutionBehaviour;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Cookware;
import getcooked.viewer.game.ElementViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class CookwareTest{
    private Chef chef;
    private Cookware cookware;
    private Grabable item;
    private ExecutionBehaviour executionBehaviour;

    @BeforeEach
    void setUp() {
        chef = mock(Chef.class);
        item = mock(Grabable.class);
        when(chef.getItem()).thenReturn(item);
        this.executionBehaviour = mock(ExecutionBehaviour.class);
        cookware = spy(new Cookware(0, 0, executionBehaviour) {
            @Override
            public ElementViewer getViewer() {
                return null;
            }

            @Override
            protected boolean accepts(Food food) { return true; }

            @Override
            protected Dish createFinishedDish() {
                return mock(Dish.class);
            }
        });
    }

    @Test
    void testInteract_chefHoldingSomething_isExecuting() {
        when(chef.isHoldingSomething()).thenReturn(true);
        this.cookware.start();

        cookware.interact(chef);
        verify(chef, never()).getItem();
    }

    @Test
    void testInteract_chefHoldingSomething_notExecuting() {
        when(chef.isHoldingSomething()).thenReturn(true);

        cookware.interact(chef);
        verify(chef, times(1)).getItem();
        verify(item, times(1)).interactWithKitchenware(cookware, chef);
    }

    @Test
    void testInteract_chefNotHoldingSomething_isExecuting() {
        when(chef.isHoldingSomething()).thenReturn(false);
        this.cookware.start();

        cookware.interact(chef);
        verify(chef, never()).getItem();
    }

    @Test
    void testInteract_chefNotHoldingSomething_notExecuting() {
        when(chef.isHoldingSomething()).thenReturn(false);

        cookware.interact(chef);
        verify(chef, never()).getItem();
    }

    @Test
    void testAcceptFoodInChef_canAdd() {
        Food food = mock(Food.class);
        doReturn(true).when(cookware).canAdd(food);

        cookware.acceptFoodInChef(food, chef);

        verify(chef, times(1)).drop();
    }

    @Test
    void testAcceptFoodInChef_cannotAdd() {
        Food food = mock(Food.class);
        doReturn(false).when(cookware).canAdd(food);

        cookware.acceptFoodInChef(food, chef);

        verify(chef, never()).drop();
    }


    void testAcceptPlateInChef(boolean finished, boolean hasContent, int timesCalled) {
        Plate plate = mock(Plate.class);
        doReturn(finished).when(cookware).isFinished();
        when(plate.hasContent()).thenReturn(hasContent);

        cookware.acceptPlateInChef(plate, chef);

        verify(plate, times(timesCalled)).setContent(any(Dish.class));
    }

    @Test
    void testAcceptPlateInChef_isFinished_plateHasNoContent() {
        testAcceptPlateInChef(true, false, 1);
    }

    @Test
    void testAcceptPlateInChef_isFinished_plateHasContent() {
        testAcceptPlateInChef(true, true, 0);
    }

    @Test
    void testAcceptPlateInChef_notFinished_plateHasNoContent() {
        testAcceptPlateInChef(false, false, 0);
    }

    @Test
    void testAcceptPlateInChef_notFinished_plateHasContent() {
        testAcceptPlateInChef(false, true, 0);
    }

    @Test
    void testUpdate() {
        doReturn(true).when(cookware).isExecuting();
        cookware.update();
        doReturn(false).when(cookware).isExecuting();
        cookware.update();

        verify(executionBehaviour, times(1)).execute(cookware);
    }

    @Test
    void testCanAdd_emptyCookware_acceptsFood() {
        Food food = mock(Food.class);
        doReturn(true).when(cookware).isEmpty();

        boolean result = cookware.canAdd(food);
        assertTrue(result);
    }

    @Test
    void testCanAdd_nonEmptyCookware_acceptsFood() {
        Food food = mock(Food.class);
        doReturn(false).when(cookware).isEmpty();

        boolean result = cookware.canAdd(food);
        assertFalse(result);
    }

    @Test
    void testCanAdd_emptyCookware_doesNotAcceptFood() {
        Food food = mock(Food.class);

        cookware = spy(new Cookware(0, 0, executionBehaviour) {
            @Override
            public ElementViewer getViewer() {
                return null;
            }

            @Override
            protected boolean accepts(Food food) { return false; } // to not accept food

            @Override
            protected Dish createFinishedDish() {
                return mock(Dish.class);
            }
        });
        doReturn(true).when(cookware).isEmpty();

        boolean result = cookware.canAdd(food);
        assertFalse(result);
    }

    @Test
    void testCanAdd_nonEmptyCookware_doesNotAcceptFood() {
        Food food = mock(Food.class);

        cookware = spy(new Cookware(0, 0, executionBehaviour) {
            @Override
            public ElementViewer getViewer() {
                return null;
            }

            @Override
            protected boolean accepts(Food food) { return false; } // to not accept food

            @Override
            protected Dish createFinishedDish() {
                return mock(Dish.class);
            }
        });
        doReturn(false).when(cookware).isEmpty();

        boolean result = cookware.canAdd(food);
        assertFalse(result);
    }
}
