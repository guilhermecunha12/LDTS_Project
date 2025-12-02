package getcooked.model.game.elements.interactable.cookware;

import getcooked.model.game.dish.Dish;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CookwareTest{
    private Chef chef;
    private Cookware cookware;

    @BeforeEach
    void beforeEach() {
        chef = mock(Chef.class);
        cookware = spy(new Cookware(0, 0) {
            @Override
            protected boolean accepts(Food food) { return true; }

            @Override
            protected void tryCook() { /* Do nothing */ }

            @Override
            protected Dish createFinishedDish() { return null; }
        });
    }

    @Test
    void testInteractWhenChefHoldingFood() {
        Food held = mock(Food.class);
        cookware.setContent(null);

        when(chef.isHoldingSomething()).thenReturn(true);
        when(chef.getItem()).thenReturn(held);
        doReturn(true).when(cookware).tryAdd(held);

        cookware.interact(chef);

        verify(chef,times(1)).drop();
    }

    @Test
    void testInteracWhenChefHoldingPlate() {
        Plate plate = mock(Plate.class);
        Dish result = mock(Dish.class);

        when(chef.isHoldingSomething()).thenReturn(true);
        when(chef.getItem()).thenReturn(plate);
        when(plate.hasContent()).thenReturn(false);

        doReturn(result).when(cookware).createFinishedDish();
        doReturn(true).when(cookware).tryAdd(plate);

        cookware.interact(chef);

        verify(plate,times(1)).put(result);
    }
}
