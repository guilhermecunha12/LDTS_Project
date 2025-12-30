package getcooked.model.game.elements.interactable.cookware;

import getcooked.model.game.dish.DishType;
import getcooked.model.game.elements.Chef;
import getcooked.model.game.elements.grabable.Plate;
import getcooked.model.game.elements.grabable.food.*;
import getcooked.model.game.elements.interactable.kitchenware.cookware.Pot;
import getcooked.viewer.game.PotViewer;
import net.jqwik.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PotTest {
    private Pot pot;

    @BeforeEach
    void setUp() {
        pot = new Pot(5, 10);
    }

    @Test
    void testAcceptsPotato() {
        Potato potato = new Potato(0, 0);
        potato.setState(FoodState.CHOPPED);

        assertTrue(pot.canAdd(potato));
    }

    @Test
    void testAcceptsCarrotAndIsFull() {
        Carrot carrot = new Carrot(0, 0);
        carrot.setState(FoodState.CHOPPED);

        assertTrue(pot.canAdd(carrot));

        // After adding the first carrot, the pot should be full
        pot.addContent(carrot);

        assertFalse(pot.canAdd(carrot));
    }

    @Test
    void testDoesNotAcceptInvalidFood() {
        Salad salad = new Salad(0,0);
        salad.setState(FoodState.CHOPPED); // this should never happen

        assertFalse(pot.canAdd(salad));
    }

    @Test
    void testDoesNotAcceptRawPotato() {
        Potato potato = new Potato(0, 0);
        potato.setState(FoodState.RAW);

        assertFalse(pot.canAdd(potato));
    }

    @Provide
    Arbitrary<Food> foodsExceptPotatoAndCarrot() {
        return Arbitraries.of(new Beef(0,0), new Salad(0,0), new Soda(0,0));
    }

    @Property
    void testDoesNotAcceptOtherFood(@ForAll("foodsExceptPotatoAndCarrot") Food food) {
        setUp();
        assertFalse(pot.canAdd(food));
    }

    @Test
    void testCreateFinishedDishPotatoSoup() {
        Potato potato = mock(Potato.class);
        when(potato.getState()).thenReturn(FoodState.CHOPPED);
        when(potato.getCookedDishType()).thenReturn(DishType.POTATO_SOUP);
        Plate plate = mock(Plate.class);
        when(plate.hasContent()).thenReturn(false);

        pot.addContent(potato);
        pot.finish();
        pot.acceptPlateInChef(plate, mock(Chef.class));

        verify(plate, times(1)).setContent(argThat(dish -> dish.type() == DishType.POTATO_SOUP));
    }

    @Test
    void testCreateFinishedDishNull() {
        Potato potato = mock(Potato.class);
        when(potato.getState()).thenReturn(FoodState.RAW);
        when(potato.getCookedDishType()).thenReturn(null);
        Plate plate = mock(Plate.class);
        when(plate.hasContent()).thenReturn(false);

        pot.addContent(potato);
        pot.finish();
        pot.acceptPlateInChef(plate, mock(Chef.class));

        verify(plate, times(1)).setContent(null);
    }

    @Test
    void testGetViewer() {
        assertInstanceOf(PotViewer.class, pot.getViewer());
    }
}
